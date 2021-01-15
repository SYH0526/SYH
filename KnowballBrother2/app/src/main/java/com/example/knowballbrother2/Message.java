package com.example.knowballbrother2;

public class Message {

    private Integer picture;
    private String title;
    private String content;

    public Message(Integer picture, String title,String content) {
        this.picture = picture;
        this.title = title;
        this.content = content;
    }

    public Integer getPicture() {
        return picture;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

}
