package com.application.chat.chatapplication.injection.component;

import com.application.chat.chatapplication.base.BaseView;
import com.application.chat.chatapplication.injection.module.ContextModule;
import com.application.chat.chatapplication.injection.module.NetworkModule;
import com.application.chat.chatapplication.ui.chat.ChatPresenter;
import com.application.chat.chatapplication.ui.home.HomePresenter;
import com.application.chat.chatapplication.ui.login.LoginPresenter;
import com.application.chat.chatapplication.ui.signup.SignUpPresenter;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = {ContextModule.class, NetworkModule.class})
public interface PresenterInjector {
    void inject(HomePresenter presenter);
    void inject(ChatPresenter presenter);
    void inject(SignUpPresenter presenter);
    void inject(LoginPresenter presenter);

    @Component.Builder
    interface Builder {
        PresenterInjector build();

        PresenterInjector.Builder networkModule(NetworkModule networkModule);

        PresenterInjector.Builder contextModule(ContextModule contextModule);

        @BindsInstance
        PresenterInjector.Builder baseView(BaseView baseView);
    }
}
