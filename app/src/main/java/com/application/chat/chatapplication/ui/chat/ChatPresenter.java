package com.application.chat.chatapplication.ui.chat;

import android.support.v4.content.res.ResourcesCompat;
import android.view.View;

import com.application.chat.chatapplication.base.BasePresenter;
import com.application.chat.chatapplication.model.Field;
import com.application.chat.chatapplication.utils.Constants;
import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import org.jetbrains.annotations.NotNull;


import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChatPresenter extends BasePresenter{
    private Disposable subscription;
    private Field chatField;

    ChatPresenter(@NotNull ChatView chatView) {
        super(chatView);
    }

    public void onViewCreated() {
        chatField = new Field();

        loadChat();
    }

    private void loadChat() {

    }

    public void onViewDestroyed() {
        Disposable disposable = this.subscription;
        if (this.subscription != null) {
            disposable.dispose();
        }

    }

    public void sendMessage(){
        // TODO: 8/12/2018 Logic for send

    }

    public void logOut(){
        Backendless.UserService.logout(new AsyncCallback<Void>() {
            @Override
            public void handleResponse(Void response) {
                successfulLogout();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                showError();
            }
        });
    }

    private void showError() {
        ((ChatView)this.getView()).showError(Constants.Message.PROCESSING_ERROR);
    }

    private void successfulLogout() {
        ((ChatView)this.getView()).intentLogOut();
    }

    public Field getChatField() {
        return chatField;
    }
}
