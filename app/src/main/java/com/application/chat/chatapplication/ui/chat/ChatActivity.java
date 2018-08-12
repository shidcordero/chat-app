package com.application.chat.chatapplication.ui.chat;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.application.chat.chatapplication.R;
import com.application.chat.chatapplication.base.BaseActivity;
import com.application.chat.chatapplication.databinding.ActivityChatBinding;
import com.application.chat.chatapplication.model.Chat;
import com.application.chat.chatapplication.ui.login.LoginPresenter;
import com.application.chat.chatapplication.ui.signup.SignUpActivity;
import com.application.chat.chatapplication.utils.AndroidUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public final class ChatActivity extends BaseActivity implements ChatView, View.OnClickListener {
    private ActivityChatBinding binding;
    private final ChatAdapter chatAdapter = new ChatAdapter();

    @NotNull
    protected ChatPresenter instantiatePresenter() {
        return new ChatPresenter(this);
    }

    @Override
    protected void showInternetStatus(boolean isConnected) {
        AndroidUtils.showInternetError(findViewById(android.R.id.content), isConnected);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_chat);
        this.binding.setAdapter(this.chatAdapter);
        this.binding.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(Objects.requireNonNull(getDrawable(R.drawable.custom_divider)));
        this.binding.setDividerItemDecoration(itemDecorator);

        this.getPresenter().onViewCreated();
        this.binding.setChatField(((ChatPresenter)this.getPresenter()).getChatField());
    }

    @Override
    protected void onResume() {
        super.onResume();

        showLogout();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.getPresenter().onViewDestroyed();
    }

    @Override
    public void updateChats(@NotNull List chats) {
        this.chatAdapter.updateChats(chats);
    }

    @Override
    public void intentLogOut() {
        Intent intent = new Intent(this, SignUpActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void toggleLoading(boolean isShow) {
        this.binding.setProgressVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    public void showError(@NotNull String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send:
                ((ChatPresenter)this.getPresenter()).sendMessage();
                break;
        }
    }
}