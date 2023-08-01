package dev.hlab.twitterino.bll.service.impl;

import dev.hlab.twitterino.bll.service.TweetService;
import dev.hlab.twitterino.bll.util.Utility;
import dev.hlab.twitterino.bll.util.Validator;
import dev.hlab.twitterino.dal.dto.*;
import dev.hlab.twitterino.dal.exception.FieldNotValidException;
import dev.hlab.twitterino.dal.model.Hashtag;
import dev.hlab.twitterino.dal.model.Tweet;
import dev.hlab.twitterino.dal.model.User;
import dev.hlab.twitterino.dal.repository.HashtagRepository;
import dev.hlab.twitterino.dal.repository.TweetRepository;
import dev.hlab.twitterino.dal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static dev.hlab.twitterino.bll.util.Utility.MAX_TWEET_LENGTH;


@Service
public class TweetServiceImpl implements TweetService {
    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;
    private final HashtagRepository hashtagRepository;

    public TweetServiceImpl(UserRepository userRepository, TweetRepository tweetRepository, HashtagRepository hashtagRepository) {
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
        this.hashtagRepository = hashtagRepository;
    }

    @Override
    public synchronized void saveTweet(SaveTweetDtoRequest saveTweetDtoRequest) throws FieldNotValidException {
        User user = userRepository.findById(saveTweetDtoRequest.getIdUser()).orElse(null);

        if (user == null) throw new NullPointerException();

        Tweet tweet = new Tweet(saveTweetDtoRequest.getText());
        if (Validator.getInstance().validateTweet(tweet)) {
            List<Hashtag> hashtagsFound = Utility.getHashtags(tweet.getText());
            List<Hashtag> hashtagsToAdd = new ArrayList<>();

            for (Hashtag h : hashtagsFound) {
                try {
                    h = hashtagRepository.save(h);
                } catch (Exception e) {
                    h = hashtagRepository.findByTag(h.getTag());
                }
                hashtagsToAdd.add(h);
            }
            tweet.setHashtags(hashtagsToAdd);
            tweet.setUser(user);
            tweetRepository.save(tweet);
        } else {
            throw new FieldNotValidException("Tweet must be within 0 and " + MAX_TWEET_LENGTH);
        }
    }

    @Override
    public List<TweetDto> getTweetsByHashtag(GetTweetsByHashtagDto getTweetsByHashtagDto) {
        System.out.println("Hashtag: " + getTweetsByHashtagDto.getHashtag());
        List<Tweet> tweets = hashtagRepository.findByTag(getTweetsByHashtagDto.getHashtag()).getTweets();

        System.out.println("Tweets: " + tweets);

        tweets = tweets.parallelStream()
                .peek(t -> t.getUser().setPassword(null))
                .sorted(Comparator.comparing(Tweet::getDateTime).reversed())
                .toList();

        System.out.println("Tweets 2nd: " + tweets);

        List<TweetDto> toReturn = new ArrayList<>();
        for (Tweet t : tweets) {
            List<GetUserResponseDto> likes = t.getLikes()
                    .parallelStream()
                    .map(GetUserResponseDto::new)
                    .toList();
            toReturn.add(new TweetDto(t.getId(),
                    t.getText(),
                    t.getUser().getFirstName(),
                    t.getUser().getLastName(),
                    t.getUser().getHandle(),
                    t.getDateTime().toString(),
                    likes));
        }
        System.out.println("Ready to return " + toReturn.size() + " items");
        return toReturn;
    }

    @Override
    public List<TweetDto> getTweets() {
        return tweetRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Tweet::getDateTime).reversed())
                .map(t -> new TweetDto(t.getId(),
                        t.getText(),
                        t.getUser().getFirstName(),
                        t.getUser().getLastName(),
                        t.getUser().getHandle(),
                        t.getDateTime().toString(),
                        t.getLikes()
                                .parallelStream()
                                .map(GetUserResponseDto::new)
                                .toList()))
                .toList();
    }

    @Override
    public synchronized void deleteTweet(DeleteTweetDTO dto) {
        User user = userRepository.findById(dto.getIdUser()).orElse(null);
        Tweet tweet = tweetRepository.findById(dto.getIdTweet()).orElse(null);
        if (user == null || tweet == null) {
            throw new NullPointerException();
        } else if (user.getId() != tweet.getUser().getId()) {
            throw new IllegalCallerException();
        } else {
            tweetRepository.delete(tweet);
        }
    }

    @Override
    public synchronized void like(DeleteTweetDTO dto) {
        User user = userRepository.findById(dto.getIdUser()).orElse(null);
        Tweet tweet = tweetRepository.findById(dto.getIdTweet()).orElse(null);
        if (user == null || tweet == null) {
            throw new NullPointerException();
        }

        if (tweet.getLikes().contains(user)) {
            tweet.getLikes().remove(user);
            user.getLike().remove(tweet);
        } else {
            tweet.getLikes().add(user);
            user.getLike().add(tweet);
        }

        userRepository.save(user);
        tweetRepository.save(tweet);
    }
}
