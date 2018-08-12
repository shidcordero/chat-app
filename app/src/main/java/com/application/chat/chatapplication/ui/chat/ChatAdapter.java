package com.application.chat.chatapplication.ui.chat;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.application.chat.chatapplication.R;
import com.application.chat.chatapplication.model.Chat;
import com.application.chat.chatapplication.BR;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private List chats;

    ChatAdapter() {

    }

    @Override
    public int getItemViewType(int position) {
        Chat item = (Chat)chats.get(position);
        if (position % 2 == 1){
            return R.layout.item_left_chat;
        } else {
            return R.layout.item_right_chat;
        }
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ChatViewHolder.create(LayoutInflater.from(parent.getContext()), parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        holder.bindTo(this.chats.get(position));
    }

    @Override
    public int getItemCount() {
        if (this.chats == null){
            return 0;
        }
        return this.chats.size();
    }

    public void updateChats(List chats) {
        this.chats = chats;
        notifyDataSetChanged();
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {

        static ChatViewHolder create(LayoutInflater inflater, ViewGroup parent, int viewType) {
            ViewDataBinding binding = DataBindingUtil.inflate(inflater, viewType, parent, false);
            return new ChatViewHolder(binding);
        }

        private ViewDataBinding binding;

        ChatViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindTo(Object data) {
            binding.setVariable(BR.chat, data);
            binding.executePendingBindings();
        }
    }
}

