package dev.hlab.twitterino.bll.util;

import dev.hlab.twitterino.dal.model.Hashtag;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Utility {
    private static final String HASHTAG_REGEX = "(#\\w+)";
    public static final int MAX_TWEET_LENGTH = 140;

    public static List<Hashtag> getHashtags(String testo) {
        List<Hashtag> hashtags = new ArrayList<>();

        Pattern pattern = Pattern.compile(HASHTAG_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(testo);

        while (matcher.find()) {
            hashtags.add(new Hashtag(matcher.group().replace("#", "").toLowerCase()));
        }
        return hashtags;
    }
}
