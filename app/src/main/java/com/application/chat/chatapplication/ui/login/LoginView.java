package com.application.chat.chatapplication.ui.login;

import com.application.chat.chatapplication.base.BaseView;

import org.jetbrains.annotations.NotNull;

public interface LoginView extends BaseView {
    void showError(@NotNull String errorMessage);

    void intentChat();
}