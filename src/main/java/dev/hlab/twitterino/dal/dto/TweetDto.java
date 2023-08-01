package dev.hlab.twitterino.dal.dto;

import dev.hlab.twitterino.dal.model.User;

import java.util.List;

public class TweetDto {
    private long id;
    private String text;
    private String firstName;
    String lastName;
    String handle;
    private String dateTime;
    private List<GetUserResponseDto> likes;

    public TweetDto() {
    }

    public TweetDto(long id, String text, String firstName, String lastName, String handle, String dateTime, List<GetUserResponseDto> likes) {
        this.id = id;
        this.text = text;
        this.firstName = firstName;
        this.lastName = lastName;
        this.handle = handle;
        this.dateTime = dateTime;
        this.likes = likes;
    }

    public List<GetUserResponseDto> getLikes() {
        return likes;
    }

    public void setLikes(List<GetUserResponseDto> likes) {
        this.likes = likes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
