package com.application.chat.chatapplication.ui.customview;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;

public class LinkTextView extends AppCompatTextView {
    public LinkTextView(Context context) {
        this(context, null);
    }

    public LinkTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        SpannableString content = new SpannableString(text);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);

        super.setText(content, type);
    }
}