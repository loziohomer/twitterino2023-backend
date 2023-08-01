package dev.hlab.twitterino.bll.service.impl;

import dev.hlab.twitterino.bll.service.UserService;
import dev.hlab.twitterino.bll.util.Validator;
import dev.hlab.twitterino.dal.dto.*;
import dev.hlab.twitterino.dal.exception.FieldNotValidException;
import dev.hlab.twitterino.dal.mapper.UserMapper;
import dev.hlab.twitterino.dal.model.Tweet;
import dev.hlab.twitterino.dal.model.User;
import dev.hlab.twitterino.dal.repository.TweetRepository;
import dev.hlab.twitterino.dal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TweetRepository tweetRepository;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper,
                           TweetRepository tweetRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.tweetRepository = tweetRepository;
    }

    @Override
    public void signin(UserSigninDto userSigninDto) throws FieldNotValidException {
        if (Validator.getInstance().validateUser(userSigninDto)) {
            User u = userMapper.signInToUser(userSigninDto);
            u.setHandle(userSigninDto.getHandle());
            synchronized (this) {
                userRepository.save(u);
            }
        }
    }

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto) {
        User user = userRepository.findUserByEmailAndPassword(userLoginRequestDto.getEmail(), userLoginRequestDto.getPassword());
        long count = tweetRepository.getCountByUser(user);
        user.setPassword(null);
        System.out.println(user);
        return new UserLoginResponseDto(user, count);
    }

    @Override
    public GetUserResponseDto getByHandle(GetUserByHanldeDto getUserByHanldeDto) {
        User user = userRepository.findByHandle(getUserByHanldeDto.getHandle().trim().toLowerCase()).orElse(null);
        if (user == null) {
            throw new NullPointerException();
        } else {
            List<Tweet> tweets = tweetRepository.getTweetsByUser(user);

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
            return new GetUserResponseDto(user, toReturn);
        }
    }

    @Override
    public void changePassword(EditPasswordDTO editPasswordDTO) {
        User user = userRepository.findById(editPasswordDTO.getId()).orElse(null);
        if (user == null) {
            throw new NullPointerException();
        } else {
            if (editPasswordDTO.getOldPassword().equals(user.getPassword()) &&
            Validator.getInstance().validatePassword(editPasswordDTO.getNewPassword())) {
                user.setPassword(editPasswordDTO.getNewPassword());
                userRepository.save(user);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }
}
