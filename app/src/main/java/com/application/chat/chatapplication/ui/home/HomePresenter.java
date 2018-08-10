package com.application.chat.chatapplication.ui.home;

import com.application.chat.chatapplication.base.BasePresenter;
import com.application.chat.chatapplication.base.BaseView;

import org.jetbrains.annotations.NotNull;

import io.reactivex.disposables.Disposable;


public final class HomePresenter extends BasePresenter {
    private Disposable subscription;

    HomePresenter(@NotNull HomeView homeView) {
        super(homeView);
    }

    public void onViewCreated() {

    }

    public void onViewDestroyed() {
        Disposable disposable = this.subscription;
        if (this.subscription != null) {
            disposable.dispose();
        }
    }
}

