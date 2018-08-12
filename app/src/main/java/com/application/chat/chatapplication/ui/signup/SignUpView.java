package com.application.chat.chatapplication.ui.signup;

import com.application.chat.chatapplication.base.BaseView;

import org.jetbrains.annotations.NotNull;

public interface SignUpView extends BaseView {
    void showError(@NotNull String errorMessage);

    void intentChat();
}