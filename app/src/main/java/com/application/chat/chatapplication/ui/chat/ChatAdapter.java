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
import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private ArrayList<Chat> chats;
    private BackendlessUser user;

    ChatAdapter() {
        user = Backendless.UserService.CurrentUser();
        chats = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        Chat item = chats.get(position);
        if (item.getOwnerId().equals(user.getUserId())){
            return R.layout.item_right_chat;
        } else {
            return R.layout.item_left_chat;
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

    public void updateChats(List<Chat> chats) {
        int chatSize = chats.size();
        this.chats.addAll(0, chats);
        notifyItemRangeInserted(0, chatSize);
    }

    public void updateChat(Chat chat) {
        this.chats.add(chat);
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

