package dev.hlab.twitterino.dal.dto;

import dev.hlab.twitterino.dal.model.User;

public class UserLoginResponseDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String handle;
    private long tweets;

    public UserLoginResponseDto(User user, long tweets) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.handle = user.getHandle();
        this.tweets = tweets;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public long getTweets() {
        return tweets;
    }

    public void setTweets(long tweets) {
        this.tweets = tweets;
    }
}
