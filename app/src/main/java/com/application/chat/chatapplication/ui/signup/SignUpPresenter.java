package com.application.chat.chatapplication.ui.signup;

import android.content.Context;
import android.text.TextUtils;

import com.application.chat.chatapplication.base.BasePresenter;
import com.application.chat.chatapplication.model.Field;
import com.application.chat.chatapplication.utils.AndroidUtils;
import com.application.chat.chatapplication.utils.Constants;
import com.application.chat.chatapplication.utils.DefaultAsyncCallback;
import com.application.chat.chatapplication.utils.DefaultCallback;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.exceptions.BackendlessFault;

import org.jetbrains.annotations.NotNull;

public class SignUpPresenter extends BasePresenter{
    private Field usernameInfo;
    private Field passwordInfo;

    SignUpPresenter(@NotNull SignUpView signUpView) {
        super(signUpView);
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
        if (TextUtils.isEmpty(usernameInfo.getUserInput()) || AndroidUtils.isLengthError(usernameInfo.getUserInput())) {
            hasNoError = false;
            usernameInfo.setError(true);
            usernameInfo.setErrorMessage(Constants.Message.INCORRECT_VALUE);
        } else {
            usernameInfo.setError(false);
            usernameInfo.setErrorMessage(Constants.Common.EMPTY_STRING);
        }

        // Verify password info
        if (TextUtils.isEmpty(passwordInfo.getUserInput()) || AndroidUtils.isLengthError(passwordInfo.getUserInput())) {
            hasNoError = false;
            passwordInfo.setError(true);
            passwordInfo.setErrorMessage(Constants.Message.INCORRECT_VALUE);
        } else {
            passwordInfo.setError(false);
            passwordInfo.setErrorMessage(Constants.Common.EMPTY_STRING);
        }

        if (hasNoError) {
            signUp();
        }
    }


    private void signUp() {
        BackendlessUser user = new BackendlessUser();
        user.setProperty(Constants.Common.USERNAME, usernameInfo.getUserInput());
        user.setPassword(passwordInfo.getUserInput());

        Context context = this.getView().getContext();

        Backendless.UserService.register(user, new DefaultAsyncCallback<String>(context) {
            @Override
            public void handleResponse(BackendlessUser response) {
                super.handleResponse(null);
                Backendless.UserService.login( usernameInfo.getUserInput(), passwordInfo.getUserInput(), new DefaultCallback<BackendlessUser>(context, Constants.Message.LOOGING_IN)
                {
                    @Override
                    public void handleResponse( BackendlessUser backendlessUser )
                    {
                        Backendless.UserService.setCurrentUser(user);
                        super.handleResponse( backendlessUser );
                        successfulSignUp(user);
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        super.handleFault(fault);
                    }
                }, true );
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                super.handleFault(fault);
            }
        });
    }

    private void successfulSignUp(BackendlessUser user) {
        ((SignUpView)this.getView()).intentChat(user);
    }

    public Field getUsernameInfo() {
        return usernameInfo;
    }

    public Field getPasswordInfo() {
        return passwordInfo;
    }
}
