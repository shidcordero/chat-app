package com.application.chat.chatapplication.ui.home;

import android.support.annotation.StringRes;

import com.application.chat.chatapplication.base.BaseView;

import org.jetbrains.annotations.NotNull;

public interface HomeView extends BaseView {
    void showError(@NotNull String errorMessage);

    void showError(@StringRes int errorResId);

    void intentLogin();

    void intentSignUp();

    final class Implementation {
        public static void showError(HomeView homeView, @StringRes int errorResId) {
            String errorMessage = homeView.getContext().getString(errorResId);
            homeView.showError(errorMessage);
        }
    }
}