package com.application.chat.chatapplication.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.application.chat.chatapplication.ui.chat.ChatAdapter;

import org.jetbrains.annotations.NotNull;

public final class DataBinder {
    @BindingAdapter({"adapter"})
    public static void setAdapter(@NotNull RecyclerView view, @NotNull ChatAdapter adapter) {
        view.setAdapter(adapter);
    }

    @BindingAdapter({"layoutManager"})
    public static void setLayoutManager(@NotNull RecyclerView view, @NotNull RecyclerView.LayoutManager layoutManager) {
        view.setLayoutManager(layoutManager);
    }

    @BindingAdapter({"dividerItemDecoration"})
    public static void setDividerItemDecoration(@NotNull RecyclerView view, @NotNull DividerItemDecoration dividerItemDecoration) {
        view.addItemDecoration(dividerItemDecoration);
    }
}
