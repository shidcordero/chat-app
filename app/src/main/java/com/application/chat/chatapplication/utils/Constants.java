package com.application.chat.chatapplication.utils;

public final class Constants {
    public static final class Environment {
        public static final String APPLICATION_ID = "A3FE2080-08C1-5620-FF67-1F9763C51B00";
        public static final String API_KEY = "A352D544-AEC1-B016-FFDA-C284FAA11000";
        public static final String BASE_URL = "https://api.backendless.com";
    }

    public static final class Common {
        public static final int DEFAULT_PAGE_SIZE = 25;
        public static final String EMPTY_STRING = "";
        public static final String SPACE = " ";
        public static final int MIN_LENGTH = 8;
        public static final int MAX_LENGTH = 16;
        public static final String INTERNET_FAULT_CODE = "Internal client exception";
        public static final String USERNAME = "username";
        public static final String CHAT_SORT = "created DESC";
        public static final String CHAT_TABLE= "Chat";
    }

    public static final class Message {
        public static final String OFFLINE_ERROR = "No internet connection.";
        public static final String INCORRECT_VALUE= "Value is incorrect";
        public static final String PROCESSING_ERROR = "An error occurred while processing your actions. Please try again.";
        public static final String SEND_ERROR = "Message was not send.";
        public static final String VERIFYING_USER = "Verifying user...";
        public static final String LOOGING_IN = "Logging in...";
    }

    public static final class Tag {
        public static final String USER_ID = "userId";
    }

}
