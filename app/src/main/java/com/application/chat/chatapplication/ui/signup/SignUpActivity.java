package com.application.chat.chatapplication.ui.signup;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.application.chat.chatapplication.R;
import com.application.chat.chatapplication.base.BaseActivity;
import com.application.chat.chatapplication.databinding.ActivitySignUpBinding;
import com.application.chat.chatapplication.ui.chat.ChatActivity;
import com.application.chat.chatapplication.ui.login.LoginActivity;
import com.application.chat.chatapplication.utils.AndroidUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SignUpActivity extends BaseActivity implements SignUpView, View.OnClickListener{

    private ActivitySignUpBinding binding;

    @NotNull
    @Override
    protected SignUpPresenter instantiatePresenter() {
        return new SignUpPresenter(this);
    }

    @Override
    protected void showInternetStatus(boolean isConnected) {
        AndroidUtils.showInternetError(findViewById(android.R.id.content), isConnected);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);

        this.getPresenter().onViewCreated();

        this.binding.setListener(this);
        this.binding.setUsernameInfo(((SignUpPresenter)this.getPresenter()).getUsernameInfo());
        this.binding.setPasswordInfo(((SignUpPresenter)this.getPresenter()).getPasswordInfo());
        setInputFilter();
    }

    private void setInputFilter() {
        EditText login = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        InputFilter inputFilter = AndroidUtils.getNoSpaceEditText();
        login.setFilters(new InputFilter[]{inputFilter});
        password.setFilters(new InputFilter[]{inputFilter});
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.getPresenter().onViewDestroyed();
    }

    @Override
    public void showError(@NotNull String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void intentChat() {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        this.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_up:
                ((SignUpPresenter)this.getPresenter()).verifyData();
                break;
            case R.id.login:
                this.startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}

