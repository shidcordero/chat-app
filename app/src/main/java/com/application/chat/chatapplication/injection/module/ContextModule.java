package com.application.chat.chatapplication.injection.module;

import android.app.Application;
import android.content.Context;

import com.application.chat.chatapplication.base.BaseView;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class ContextModule {
    public static final ContextModule INSTANCE;

    @Provides
    @NotNull
    public static Context provideContext(@NotNull BaseView baseView) {
        return baseView.getContext();
    }

    @Provides
    @NotNull
    public static Application provideApplication(@NotNull Context context) {
        return (Application) context.getApplicationContext();
    }

    static {
        INSTANCE = new ContextModule();
    }
}