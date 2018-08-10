package com.application.chat.chatapplication.injection.component;

import com.application.chat.chatapplication.base.BaseView;
import com.application.chat.chatapplication.injection.module.ContextModule;
import com.application.chat.chatapplication.injection.module.NetworkModule;
import com.application.chat.chatapplication.ui.chat.ChatPresenter;
import com.application.chat.chatapplication.ui.home.HomePresenter;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = {ContextModule.class, NetworkModule.class})
public interface PresenterInjector {
    void inject(@NotNull HomePresenter presenter);
    void inject(@NotNull ChatPresenter presenter);

    @Component.Builder
    interface Builder {
        @NotNull
        PresenterInjector build();

        @NotNull
        PresenterInjector.Builder networkModule(@NotNull NetworkModule networkModule);

        @NotNull
        PresenterInjector.Builder contextModule(@NotNull ContextModule contextModule);

        @BindsInstance
        @NotNull
        PresenterInjector.Builder baseView(@NotNull BaseView baseView);
    }
}
