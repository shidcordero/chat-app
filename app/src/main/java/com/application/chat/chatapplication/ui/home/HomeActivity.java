package com.application.chat.chatapplication.ui.home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Toast;

import com.application.chat.chatapplication.R;
import com.application.chat.chatapplication.base.BaseActivity;
import com.application.chat.chatapplication.ui.login.LoginActivity;
import com.application.chat.chatapplication.ui.signup.SignUpActivity;
import com.application.chat.chatapplication.databinding.ActivityHomeBinding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class HomeActivity extends BaseActivity implements HomeView, View.OnClickListener {
    private ActivityHomeBinding binding;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        this.binding.setListener(this);

        this.getPresenter().onViewCreated();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.getPresenter().onViewDestroyed();
    }

    public void showError(@NotNull String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    public void showError(@StringRes int errorResId) {
        Implementation.showError(this, errorResId);
    }


    public void intentLogin() {
        this.startActivity(new Intent(this, LoginActivity.class));
    }

    public void intentSignUp() {
        this.startActivity(new Intent(this, SignUpActivity.class));
    }

    @NotNull
    protected HomePresenter instantiatePresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                intentLogin();
                break;
            case R.id.sign_up:
                intentSignUp();
                break;
        }
    }
}

