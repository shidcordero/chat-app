package com.application.chat.chatapplication.ui.login;

import android.support.annotation.NonNull;

import com.application.chat.chatapplication.base.BaseActivity;
import com.application.chat.chatapplication.base.BasePresenter;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends BaseActivity {
    @NotNull
    @Override
    protected BasePresenter instantiatePresenter() {
        return null;
    }

    @NonNull
    @NotNull
    @Override
    protected void showInternetStatus(boolean isConnected) {

    }
}
