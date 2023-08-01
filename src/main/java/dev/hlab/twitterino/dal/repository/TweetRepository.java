package dev.hlab.twitterino.dal.repository;

import dev.hlab.twitterino.dal.model.Tweet;
import dev.hlab.twitterino.dal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
    @Query(value = "select count (t) from Tweet t where t.user = :user")
    public long getCountByUser(@Param("user") User user);
    public List<Tweet> getTweetsByUser(User user);
}
