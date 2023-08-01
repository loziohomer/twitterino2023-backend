package dev.hlab.twitterino.dal.dto;

public class SaveTweetDtoRequest {
    private long idUser;
    private String text;

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
