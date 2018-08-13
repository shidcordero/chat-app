package com.application.chat.chatapplication.ui.home;

import com.application.chat.chatapplication.base.BaseView;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import org.jetbrains.annotations.NotNull;

public interface HomeView extends BaseView {
    void showError(@NotNull String errorMessage);

    void intentChat(BackendlessUser user);
}