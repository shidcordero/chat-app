package com.application.chat.chatapplication.utils;

import org.jetbrains.annotations.NotNull;

public final class Constants {
    public static final class Environment {
        @NotNull
        public static final String BASE_URL = "https://my-json-server.typicode.com/shidcordero12/android-json-server/";
    }

    public static final class Common {
        public static final int REQUEST_READ_CONTACTS = 0;
        public static final int PASSWORD_LENGTH = 5;
        @NotNull
        public static final String EMAIL_REGEX = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
    }
}
