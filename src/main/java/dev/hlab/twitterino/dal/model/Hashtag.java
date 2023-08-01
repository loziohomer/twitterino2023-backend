package dev.hlab.twitterino.dal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "hashtag")
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String tag;
    @JsonIgnore
    @ManyToMany(mappedBy = "hashtags", fetch = FetchType.LAZY)
    private List<Tweet> tweets;

    public Hashtag() {
    }

    public Hashtag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Hashtag{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }
}
