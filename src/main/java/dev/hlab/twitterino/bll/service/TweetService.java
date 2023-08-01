package dev.hlab.twitterino.bll.service;

import dev.hlab.twitterino.dal.dto.DeleteTweetDTO;
import dev.hlab.twitterino.dal.dto.GetTweetsByHashtagDto;
import dev.hlab.twitterino.dal.dto.SaveTweetDtoRequest;
import dev.hlab.twitterino.dal.dto.TweetDto;
import dev.hlab.twitterino.dal.exception.FieldNotValidException;

import java.util.List;

public interface TweetService {
    public void saveTweet(SaveTweetDtoRequest saveTweetDtoRequest) throws FieldNotValidException;
    public List<TweetDto> getTweetsByHashtag(GetTweetsByHashtagDto getTweetsByHashtagDto);
    public List<TweetDto> getTweets();
    public void deleteTweet(DeleteTweetDTO dto);
    public void like(DeleteTweetDTO dto);
}
