package com.application.chat.chatapplication.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.application.chat.chatapplication.R;
import com.application.chat.chatapplication.ui.chat.ChatPresenter;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    protected BasePresenter presenter;

    private Disposable networkDisposable;
    private Disposable internetDisposable;
    private ActionBar actionBar;

    @NotNull
    protected final BasePresenter getPresenter() {
        return this.presenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = this.instantiatePresenter();

        setupActionBar();
    }

    @Override
    protected void onResume(){
        super.onResume();

        setupNetwork();
    }

    private void setupActionBar() {
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setElevation(0);

            actionBar.setCustomView(R.layout.custom_action_bar);

            //removing parent margin
            Toolbar parent =(Toolbar) actionBar.getCustomView().getParent();
            parent.setContentInsetsAbsolute(0,0);
        }
    }

    private void setupNetwork() {
        //check if connected to wifi or data
//        networkDisposable = ReactiveNetwork.observeNetworkConnectivity(getApplicationContext())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(connectivity -> {
//
//                });

        //check if there's an internet connection
        internetDisposable = ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showInternetStatus);
    }

    public void showLogout(){
        View customView = actionBar.getCustomView();
        Button button = customView.findViewById(R.id.logout);
        button.setVisibility(View.VISIBLE);
        button.setOnClickListener(view -> ((ChatPresenter)this.presenter).logOut());
    }

    @Override
    protected void onPause() {
        super.onPause();
        safelyDispose(networkDisposable, internetDisposable);
    }

    @NotNull
    protected abstract BasePresenter instantiatePresenter();

    protected abstract void showInternetStatus(boolean isConnected);

    @NotNull
    public Context getContext() {
        return this;
    }

    private void safelyDispose(Disposable... disposables) {
        for (Disposable subscription : disposables) {
            if (subscription != null && !subscription.isDisposed()) {
                subscription.dispose();
            }
        }
    }
}