package com.application.chat.chatapplication.injection.module;

import dagger.Module;

@Module
public final class NetworkModule {
    public static final NetworkModule INSTANCE;

    /**
     * Uncomment if you want to use Retrofit for api calls
     **/
    /*@Provides
    @Reusable
    public static Retrofit provideRetrofitInterface() {
        return (new Retrofit.Builder())
                .baseUrl(Constants.Environment.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    @Provides
    @Reusable
    public static ChatApi provideChatApi(Retrofit retrofit) {
        return retrofit.create(ChatApi.class);
    }*/

    static {
        INSTANCE = new NetworkModule();
    }
}