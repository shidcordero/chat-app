package com.application.chat.chatapplication.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    @NotNull
    protected BasePresenter presenter;

    @NotNull
    protected final BasePresenter getPresenter() {
        return this.presenter;
    }

    protected final void setPresenter(@NotNull BasePresenter basePresenter) {
        this.presenter = basePresenter;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = this.instantiatePresenter();
    }

    @NotNull
    protected abstract BasePresenter instantiatePresenter();

    @NotNull
    public Context getContext() {
        return this;
    }
}