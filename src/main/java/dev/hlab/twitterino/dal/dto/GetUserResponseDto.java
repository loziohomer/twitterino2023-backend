package dev.hlab.twitterino.dal.dto;

import dev.hlab.twitterino.dal.model.User;

import java.util.List;

public class GetUserResponseDto {
    private String firstName;
    private String lastName;
    private String handle;
    private List<TweetDto> tweets;

    public GetUserResponseDto() {
    }

    public GetUserResponseDto(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.handle = user.getHandle();
    }

    public GetUserResponseDto(User user, List<TweetDto> tweets) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.handle = user.getHandle();
        this.tweets = tweets;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public List<TweetDto> getTweets() {
        return tweets;
    }

    public void setTweets(List<TweetDto> tweets) {
        this.tweets = tweets;
    }
}
