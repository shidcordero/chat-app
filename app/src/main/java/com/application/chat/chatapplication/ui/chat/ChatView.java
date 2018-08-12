package com.application.chat.chatapplication.ui.chat;

import com.application.chat.chatapplication.base.BaseView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ChatView extends BaseView{
    void showError(@NotNull String errorMessage);

    void updateChats(@NotNull List chats);

    void intentLogOut();

    void toggleLoading(boolean isShow);
}
