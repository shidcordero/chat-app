package com.application.chat.chatapplication.ui.home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.application.chat.chatapplication.R;
import com.application.chat.chatapplication.base.BaseActivity;
import com.application.chat.chatapplication.databinding.ActivityHomeBinding;
import com.application.chat.chatapplication.ui.chat.ChatActivity;
import com.application.chat.chatapplication.ui.login.LoginActivity;
import com.application.chat.chatapplication.ui.signup.SignUpActivity;
import com.application.chat.chatapplication.utils.AndroidUtils;
import com.backendless.Backendless;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class HomeActivity extends BaseActivity implements HomeView, View.OnClickListener {
    private ActivityHomeBinding binding;

    @NotNull
    @Override
    protected HomePresenter instantiatePresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void showInternetStatus(boolean isConnected) {
        AndroidUtils.showInternetError(findViewById(android.R.id.content), isConnected);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Backendless.initApp( this, getString(R.string.backendless_AppId), getString(R.string.backendless_ApiKey));
        Backendless.setUrl(getString(R.string.backendless_ApiHost));

        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        this.binding.setListener(this);

        this.getPresenter().onViewCreated();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.getPresenter().onViewDestroyed();
    }

    @Override
    public void intentChat() {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        this.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                this.startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.sign_up:
                this.startActivity(new Intent(this, SignUpActivity.class));
                break;
        }
    }

    @Override
    public void showError(@NotNull String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }
}

