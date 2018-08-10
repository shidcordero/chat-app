package com.application.chat.chatapplication.ui.chat;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.application.chat.chatapplication.R;
import com.application.chat.chatapplication.base.BaseActivity;
import com.application.chat.chatapplication.databinding.ActivityChatBinding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class ChatActivity extends BaseActivity implements ChatView {
    private ActivityChatBinding binding;
    private final ChatAdapter chatAdapter = new ChatAdapter((Context)this);

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_chat);
        this.binding.setAdapter(this.chatAdapter);
        this.binding.setLayoutManager(new LinearLayoutManager(this));
        this.binding.setDividerItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        this.getPresenter().onViewCreated();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.getPresenter().onViewDestroyed();
    }

    @Override
    public void updateChats(@NotNull List chats) {
        this.chatAdapter.updateChats(chats);
    }

    public void showError(@NotNull String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(@StringRes int errorResId) {

    }

    public void showLoading() {
        this.binding.setProgressVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        this.binding.setProgressVisibility(View.GONE);
    }

    @NotNull
    protected ChatPresenter instantiatePresenter() {
        return new ChatPresenter(this);
    }

    @NonNull
    @NotNull
    @Override
    protected void showInternetStatus(boolean isConnected) {

    }


}