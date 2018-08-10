package com.application.chat.chatapplication.model;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class Chat {
    private final int id;
    @NotNull
    private final String message;
    @NotNull
    private final Date timestamp;
    @NotNull
    private final String userName;

    public Chat(int id, @NotNull String message, @NotNull Date timestamp, @NotNull String userName) {
        this.id = id;
        this.message = message;
        this.timestamp = timestamp;
        this.userName = userName;
    }

    @NotNull
    public String getMessage() {
        return message;
    }

    public int getId() {
        return id;
    }

    @NotNull
    public String getTimestamp() {
        return timestamp.toString();
    }

    @NotNull
    public String getUserName() {
        return userName;
    }
}
