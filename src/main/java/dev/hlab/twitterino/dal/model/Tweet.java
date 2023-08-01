package dev.hlab.twitterino.dal.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tweet")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 140)
    private String text;
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tweet_hashtag",
            joinColumns = @JoinColumn(name = "id_tweet"),
            inverseJoinColumns = @JoinColumn(name = "id_hashtag"))
    private List<Hashtag> hashtags;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tweet_user",
            joinColumns = @JoinColumn(name = "id_tweet"),
            inverseJoinColumns = @JoinColumn(name = "id_user"))
    private List<User> likes;

    public Tweet() {
    }

    public Tweet(String text) {
        this.text = text;
        this.dateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", dateTime=" + dateTime +
                ", user=" + user +
                '}';
    }

    public List<User> getLikes() {
        return likes;
    }

    public void setLikes(List<User> likes) {
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }
}
