package dev.hlab.twitterino.bll.service.impl;

import dev.hlab.twitterino.bll.service.HashtagService;
import dev.hlab.twitterino.dal.model.Hashtag;
import dev.hlab.twitterino.dal.repository.HashtagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HashtagServiceImpl implements HashtagService {
    private final HashtagRepository hashtagRepository;

    public HashtagServiceImpl(HashtagRepository hashtagRepository) {
        this.hashtagRepository = hashtagRepository;
    }

    @Override
    public List<String> getTrends() {
        return hashtagRepository.findAll().parallelStream()
                .sorted((h1, h2) -> h2.getTweets().size() - h1.getTweets().size())
                .limit(10)
                .map(Hashtag::getTag)
                .toList();
    }
}
