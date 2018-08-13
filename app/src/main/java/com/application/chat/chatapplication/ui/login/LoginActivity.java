package com.application.chat.chatapplication.ui.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.application.chat.chatapplication.R;
import com.application.chat.chatapplication.base.BaseActivity;
import com.application.chat.chatapplication.databinding.ActivityLoginBinding;
import com.application.chat.chatapplication.ui.chat.ChatActivity;
import com.application.chat.chatapplication.ui.signup.SignUpActivity;
import com.application.chat.chatapplication.utils.AndroidUtils;
import com.application.chat.chatapplication.utils.Constants;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener{

    private ActivityLoginBinding binding;

    @NotNull
    @Override
    protected LoginPresenter instantiatePresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void showInternetStatus(boolean isConnected) {
        AndroidUtils.showInternetError(findViewById(android.R.id.content), isConnected);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        this.getPresenter().onViewCreated();

        this.binding.setListener(this);
        this.binding.setUsernameInfo(((LoginPresenter)this.getPresenter()).getUsernameInfo());
        this.binding.setPasswordInfo(((LoginPresenter)this.getPresenter()).getPasswordInfo());
        setInputFilter();
    }

    private void setInputFilter() {
        InputFilter inputFilter = AndroidUtils.getNoSpaceEditText();
        this.binding.password.setFilters(new InputFilter[]{inputFilter});
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
    public void intentChat(BackendlessUser user) {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(Constants.Tag.USER_ID, user.getUserId());
        this.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_up:
                this.startActivity(new Intent(this, SignUpActivity.class));
                break;
            case R.id.login:
                ((LoginPresenter)this.getPresenter()).verifyData();
                break;
        }
    }
}
