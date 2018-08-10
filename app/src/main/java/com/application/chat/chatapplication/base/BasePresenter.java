package com.application.chat.chatapplication.base;

import com.application.chat.chatapplication.injection.component.DaggerPresenterInjector;
import com.application.chat.chatapplication.injection.component.PresenterInjector;
import com.application.chat.chatapplication.injection.module.ContextModule;
import com.application.chat.chatapplication.injection.module.NetworkModule;
import com.application.chat.chatapplication.ui.chat.ChatPresenter;
import com.application.chat.chatapplication.ui.home.HomePresenter;

import org.jetbrains.annotations.NotNull;

public abstract class BasePresenter {
    private final PresenterInjector injector;
    @NotNull
    private final BaseView view;

    public void onViewCreated() {

    }

    public void onViewDestroyed() {

    }

    private void inject() {
        if (this instanceof HomePresenter) {
            this.injector.inject((HomePresenter)this);
        } else if (this instanceof ChatPresenter){
            this.injector.inject((ChatPresenter)this);
        }

    }

    @NotNull
    protected final BaseView getView() {
        return this.view;
    }

    public BasePresenter(@NotNull BaseView view) {
        super();
        this.view = view;
        this.injector = DaggerPresenterInjector.builder()
                .baseView(this.view)
                .contextModule(ContextModule.INSTANCE)
                .networkModule(NetworkModule.INSTANCE)
                .build();
        this.inject();
    }
}
