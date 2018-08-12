package com.application.chat.chatapplication.utils;

import android.support.design.widget.Snackbar;
import android.text.InputFilter;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.application.chat.chatapplication.R;

public final class AndroidUtils {
    private static Snackbar snackbar;

    public static InputFilter getNoSpaceEditText(){
        return (source, start, end, dest, dstart, dend) -> {
            if (source == null) return null;
            return source.toString().replace(Constants.Common.SPACE, Constants.Common.EMPTY_STRING);
        };
    }

    public static boolean isLengthError(String text){
        return text.length() < Constants.Common.MIN_LENGTH || text.length() > Constants.Common.MAX_LENGTH;
    }

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
