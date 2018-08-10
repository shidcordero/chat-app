package com.application.chat.chatapplication.ui.chat;

import android.support.annotation.StringRes;

import com.application.chat.chatapplication.base.BaseView;
import com.application.chat.chatapplication.ui.home.HomeView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ChatView extends BaseView{
    void showError(@NotNull String errorMessage);

    void showError(@StringRes int errorResId);

    void updateChats(@NotNull List chats);

    final class Implementation {
        public static void showError(ChatView chatView, @StringRes int errorResId) {
            String errorMessage = chatView.getContext().getString(errorResId);
            chatView.showError(errorMessage);
        }
    }
}
