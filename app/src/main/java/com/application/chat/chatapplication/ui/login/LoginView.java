package com.application.chat.chatapplication.ui.login;

import com.application.chat.chatapplication.base.BaseView;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import org.jetbrains.annotations.NotNull;

public interface LoginView extends BaseView {
    void showError(@NotNull String errorMessage);

    void intentChat(BackendlessUser user);
}