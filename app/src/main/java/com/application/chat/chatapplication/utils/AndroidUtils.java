package com.application.chat.chatapplication.utils;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.chat.chatapplication.R;

public final class AndroidUtils {
    private static Snackbar snackbar;

    public static void showInternetError(View view, boolean isConnected){
        if (isConnected) {
            hideSnackBar();
        } else {
            showSnackBar(view, Constants.Message.OFFLINE_ERROR);
        }
    }

    private static void showSnackBar(View view, String snackTitle){
        snackbar = Snackbar.make(view, snackTitle, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.button_dismiss, v -> hideSnackBar())
                .setActionTextColor(view.getResources().getColor(android.R.color.holo_blue_light, null));

        TextView textView = snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, view.getResources().getDimension(R.dimen.snackbar_text_size));
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        snackbar.show();
    }


    private static void hideSnackBar(){
        if (snackbar != null && snackbar.isShown()){
            snackbar.dismiss();
        }
    }
}
