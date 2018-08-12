package com.application.chat.chatapplication.model;

import java.util.Objects;

public class Chat {
    private int id;
    private String message;
    private String timestamp;
    private String userName;

    public Chat(int id, String message, String timestamp, String userName) {
        this.id = id;
        this.message = message;
        this.timestamp = timestamp;
        this.userName = userName;
    }

    public Chat() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMessage(String message) {
        if (!this.message.equals(message)){
            this.message = message;
        }
    }

    public void setTimestamp(String timestamp) {
        if (!Objects.equals(this.timestamp, timestamp)){
            this.timestamp = timestamp;
        }
    }

    public void setUserName(String userName) {
        if (!this.userName.equals(userName)){
            this.userName = userName;
        }
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getUserName() {
        return userName;
    }
}
