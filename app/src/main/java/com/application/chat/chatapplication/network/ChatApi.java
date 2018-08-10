package com.application.chat.chatapplication.network;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ChatApi {
    @GET("users")
    @NotNull
    Observable getUsers();
}

