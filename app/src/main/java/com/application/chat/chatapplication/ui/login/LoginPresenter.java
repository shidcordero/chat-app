package com.application.chat.chatapplication.ui.login;

import android.text.TextUtils;

import com.application.chat.chatapplication.base.BasePresenter;
import com.application.chat.chatapplication.model.Field;
import com.application.chat.chatapplication.utils.AndroidUtils;
import com.application.chat.chatapplication.utils.Constants;
import com.application.chat.chatapplication.utils.DefaultCallback;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.exceptions.BackendlessFault;

import org.jetbrains.annotations.NotNull;

public class LoginPresenter extends BasePresenter{
    private Field usernameInfo;
    private Field passwordInfo;

    LoginPresenter(@NotNull LoginView loginView) {
        super(loginView);
    }

    public void onViewCreated() {
        usernameInfo = new Field();
        passwordInfo = new Field();
    }

    public void onViewDestroyed() {

    }

    public void verifyData() {
        boolean hasNoError = true;

        // Verify username info
        if (TextUtils.isEmpty(usernameInfo.getUserInput()) | AndroidUtils.isLengthError(usernameInfo.getUserInput())) {
            hasNoError = false;
            usernameInfo.setError(true);
            usernameInfo.setErrorMessage(Constants.Message.INCORRECT_VALUE);
        } else {
            usernameInfo.setError(false);
            usernameInfo.setErrorMessage(Constants.Common.EMPTY_STRING);
        }

        // Verify password info
        if (TextUtils.isEmpty(passwordInfo.getUserInput()) | AndroidUtils.isLengthError(passwordInfo.getUserInput())) {
            hasNoError = false;
            passwordInfo.setError(true);
            passwordInfo.setErrorMessage(Constants.Message.INCORRECT_VALUE);
        } else {
            passwordInfo.setError(false);
            passwordInfo.setErrorMessage(Constants.Common.EMPTY_STRING);
        }

        if (hasNoError) {
            login();
        }
    }

    private void login(){
        Backendless.UserService.login( usernameInfo.getUserInput(), passwordInfo.getUserInput(), new DefaultCallback<BackendlessUser>( this.getView().getContext() )
        {
            public void handleResponse( BackendlessUser backendlessUser )
            {
                super.handleResponse( backendlessUser );
                successfulLogin();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                super.handleFault(fault);
            }
        }, true );
    }

    private void successfulLogin(){
        ((LoginView) this.getView()).intentChat();
    }

    public Field getUsernameInfo() {
        return usernameInfo;
    }

    public Field getPasswordInfo() {
        return passwordInfo;
    }

}
