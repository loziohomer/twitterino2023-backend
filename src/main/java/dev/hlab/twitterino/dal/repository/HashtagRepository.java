package dev.hlab.twitterino.dal.repository;

import dev.hlab.twitterino.dal.model.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    public Hashtag findByTag(String tag);
}
