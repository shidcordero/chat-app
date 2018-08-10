package com.application.chat.chatapplication.ui.chat;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.application.chat.chatapplication.R;
import com.application.chat.chatapplication.model.Chat;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import com.application.chat.chatapplication.databinding.ItemChatBinding;
import java.util.List;

public final class ChatAdapter extends RecyclerView.Adapter {
    private List chats;
    private final Context context;

    @NotNull
    public ChatAdapter.ChatViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        ItemChatBinding itemChatBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_chat, parent, false);
        return new ChatAdapter.ChatViewHolder(itemChatBinding);
    }

    public int getItemCount() {
        return this.chats.size();
    }

    private void onBindViewHolder(@NotNull ChatAdapter.ChatViewHolder holder, int position) {
        holder.bind((Chat)this.chats.get(position));
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        this.onBindViewHolder((ChatAdapter.ChatViewHolder)viewHolder, position);
    }

    public final void updateChats(@NotNull List chats) {
        this.chats = chats;
        this.notifyDataSetChanged();
    }

    ChatAdapter(@NotNull Context context) {
        super();
        this.context = context;
        this.chats = Collections.emptyList();
    }

    public static final class ChatViewHolder extends RecyclerView.ViewHolder {
        private final ItemChatBinding binding;

        final void bind(@NotNull Chat chat) {
            this.binding.setChat(chat);
            this.binding.executePendingBindings();
        }

        ChatViewHolder(@NotNull ItemChatBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

