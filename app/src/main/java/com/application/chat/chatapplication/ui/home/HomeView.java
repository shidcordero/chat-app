package com.application.chat.chatapplication.ui.home;

import com.application.chat.chatapplication.base.BaseView;

import org.jetbrains.annotations.NotNull;

public interface HomeView extends BaseView {
    void showError(@NotNull String errorMessage);

}