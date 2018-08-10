package com.application.chat.chatapplication.ui.chat;

import com.application.chat.chatapplication.base.BasePresenter;

import org.jetbrains.annotations.NotNull;

import io.reactivex.disposables.Disposable;

public class ChatPresenter extends BasePresenter{
    private Disposable subscription;

    public void onViewCreated() {

    }

    public void onViewDestroyed() {
        Disposable disposable = this.subscription;
        if (this.subscription != null) {
            disposable.dispose();
        }

    }

    ChatPresenter(@NotNull ChatView chatView) {
        super(chatView);
    }

    @NotNull
    public static ChatView getView(ChatPresenter presenter) {
        return (ChatView) presenter.getView();
    }
}
