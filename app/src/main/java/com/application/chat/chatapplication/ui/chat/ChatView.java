package com.application.chat.chatapplication.ui.chat;

import com.application.chat.chatapplication.base.BaseView;
import com.application.chat.chatapplication.model.Chat;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ChatView extends BaseView{
    void showError(@NotNull String errorMessage);

    void updateChats(@NotNull List<Chat> chats, boolean isScroll);

    void updateChat(@NotNull Chat chat);

    void intentLogOut();

    void toggleLoading(boolean isShow);

    void toggleInput(boolean isEnable);
}
