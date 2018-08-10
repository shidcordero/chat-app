package com.application.chat.chatapplication.base;

import android.content.Context;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.chat.chatapplication.R;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    @NotNull
    protected BasePresenter presenter;

    private Disposable networkDisposable;
    private Disposable internetDisposable;

    @NotNull
    protected final BasePresenter getPresenter() {
        return this.presenter;
    }

    protected final void setPresenter(@NotNull BasePresenter basePresenter) {
        this.presenter = basePresenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = this.instantiatePresenter();
    }

    @Override
    protected void onResume(){
        super.onResume();

        networkDisposable = ReactiveNetwork.observeNetworkConnectivity(getApplicationContext())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(connectivity -> {

                });

        internetDisposable = ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isConnected ->
                        showInternetStatus(isConnected)
                );
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