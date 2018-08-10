package com.application.chat.chatapplication.ui.home;

import com.application.chat.chatapplication.base.BasePresenter;
import com.application.chat.chatapplication.base.BaseView;

import org.jetbrains.annotations.NotNull;

import io.reactivex.disposables.Disposable;


public final class HomePresenter extends BasePresenter {
    private Disposable subscription;

    public void onViewCreated() {

    }

    public void onViewDestroyed() {
        Disposable disposable = this.subscription;
        if (this.subscription != null) {
            disposable.dispose();
        }
    }

    HomePresenter(@NotNull HomeView homeView) {
        super(homeView);
    }

    @NotNull
    public static HomeView getView(HomePresenter presenter) {
        return (HomeView) presenter.getView();
    }
}

