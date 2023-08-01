package dev.hlab.twitterino.dal.dto;

public class DeleteTweetDTO {
    private Long idUser;
    private Long idTweet;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdTweet() {
        return idTweet;
    }

    public void setIdTweet(Long idTweet) {
        this.idTweet = idTweet;
    }
}
