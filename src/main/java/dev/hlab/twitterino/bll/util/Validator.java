package dev.hlab.twitterino.bll.util;

import dev.hlab.twitterino.dal.dto.UserSigninDto;
import dev.hlab.twitterino.dal.exception.FieldNotValidException;
import dev.hlab.twitterino.dal.model.Tweet;

import static dev.hlab.twitterino.bll.util.Utility.MAX_TWEET_LENGTH;

public class Validator {
    private static final String EMAIL_REGEX = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final String NAME_REGEX = "^[a-zA-Z\\s]{2,}$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
    private static final String HANDLE_REGEX = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,12}[a-zA-Z0-9]$";

    private static Validator instance;

    private Validator() {
    }

    public static Validator getInstance() {
        if (instance == null) {
            instance = new Validator();
        }
        return instance;
    }

    public boolean validateUser(UserSigninDto user) throws FieldNotValidException {
        user.setEmail(user.getEmail().toLowerCase().trim());
        user.setHandle(user.getHandle().toLowerCase().trim());
        if (!user.getFirstName().matches(NAME_REGEX)) {
            throw new FieldNotValidException("First name too short");
        } else if (!user.getLastName().matches(NAME_REGEX)) {
            throw new FieldNotValidException("Last name too short");
        } else if (!user.getEmail().matches(EMAIL_REGEX)) {
            throw new FieldNotValidException("Email not valid");
        } else if (!validatePassword(user.getPassword())) {
            throw new FieldNotValidException("Password not valid");
        } else if (!user.getHandle().matches(HANDLE_REGEX)) {
            throw new FieldNotValidException("Handle not valid");
        }
        return true;
    }

    public boolean validatePassword(String password) {
        System.out.println(password);
        System.out.println(password != null && password.matches(PASSWORD_REGEX));
        return password != null && password.matches(PASSWORD_REGEX);
    }

    public boolean validateTweet(Tweet tweet) {
        tweet.setText(tweet.getText().trim());
        return !tweet.getText().isEmpty() && tweet.getText().length() <= MAX_TWEET_LENGTH;
    }
}
