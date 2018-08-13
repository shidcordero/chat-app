package com.application.chat.chatapplication.ui.chat;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.application.chat.chatapplication.R;
import com.application.chat.chatapplication.base.BaseActivity;
import com.application.chat.chatapplication.databinding.ActivityChatBinding;
import com.application.chat.chatapplication.model.Chat;
import com.application.chat.chatapplication.ui.signup.SignUpActivity;
import com.application.chat.chatapplication.utils.AndroidUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public final class ChatActivity extends BaseActivity implements ChatView, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
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

        initBinding();
    }

    private void initBinding() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);

        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_chat);
        this.binding.recyclerView.setHasFixedSize(true);
        this.binding.setAdapter(this.chatAdapter);
        this.binding.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(Objects.requireNonNull(getDrawable(R.drawable.custom_divider)));
        this.binding.setDividerItemDecoration(itemDecorator);
        this.binding.setListener(this);
        this.binding.swipeRefreshLayout.setOnRefreshListener(this);
        this.binding.swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent,null));

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
    public void updateChats(@NotNull List<Chat> chats, boolean isScroll) {
        this.chatAdapter.updateChats(chats);

        if (isScroll) {
            toggleLoading(false);
            this.binding.recyclerView.scrollToPosition(this.chatAdapter.getItemCount() - 1);
        } else {
            this.binding.swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void updateChat(@NotNull Chat chat) {
        this.chatAdapter.updateChat(chat);

        this.binding.recyclerView.smoothScrollToPosition(this.chatAdapter.getItemCount() - 1);
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

    @Override
    public void toggleInput(boolean isEnable) {
        this.binding.send.setEnabled(isEnable);
        this.binding.message.setEnabled(isEnable);
        if (isEnable){
            this.binding.send.setAlpha(1f);
            this.binding.message.setAlpha(1f);
        } else {
            this.binding.send.setAlpha(0.5f);
            this.binding.message.setAlpha(0.5f);
        }
    }

    public void showError(@NotNull String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send:
                toggleInput(false);
                ((ChatPresenter)this.getPresenter()).sendMessage();
                break;
        }
    }

    @Override
    public void onRefresh() {
        ((ChatPresenter)this.getPresenter()).nextPageChat();
    }
}