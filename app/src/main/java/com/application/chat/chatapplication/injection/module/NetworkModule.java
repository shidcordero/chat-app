package com.application.chat.chatapplication.injection.module;

import com.application.chat.chatapplication.network.ChatApi;
import com.application.chat.chatapplication.utils.Constants;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public final class NetworkModule {
    public static final NetworkModule INSTANCE;

    @Provides
    @Reusable
    @NotNull
    public static Retrofit provideRetrofitInterface() {
        return (new Retrofit.Builder())
                .baseUrl(Constants.Environment.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    @Provides
    @Reusable
    @NotNull
    public static ChatApi provideChatApi(@NotNull Retrofit retrofit) {
        Object object = retrofit.create(ChatApi.class);
        return (ChatApi)object;
    }

    static {
        INSTANCE = new NetworkModule();
    }
}