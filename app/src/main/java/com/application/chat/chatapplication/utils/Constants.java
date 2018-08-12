package com.application.chat.chatapplication.utils;

public final class Constants {
    public static final class Environment {
        public static final String BASE_URL = "https://my-json-server.typicode.com/shidcordero12/android-json-server-chat/";
    }

    public static final class Common {
        public static final String EMPTY_STRING = "";
        public static final String SPACE = " ";
        public static final int MIN_LENGTH = 8;
        public static final int MAX_LENGTH = 16;
        public static final String INTERNET_FAULT_CODE = "Internal client exception";
        public static final String USERNAME = "username";
    }

    public static final class Message {
        public static final String OFFLINE_ERROR = "No internet connection.";
        public static final String INCORRECT_VALUE= "Value is incorrect";
        public static final String LOGGING_IN= "Logging in...";
        public static final String PROCESSING_ERROR = "An error occurred while processing your actions. Please try again.";
    }
}
