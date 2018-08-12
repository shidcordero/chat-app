package com.application.chat.chatapplication.ui.home;

import android.content.Context;

import com.application.chat.chatapplication.base.BasePresenter;
import com.application.chat.chatapplication.utils.Constants;
import com.application.chat.chatapplication.utils.DefaultCallback;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import org.jetbrains.annotations.NotNull;


public final class HomePresenter extends BasePresenter {

    HomePresenter(@NotNull HomeView homeView) {
        super(homeView);
    }

    public void onViewCreated() {
        verifyUser();
    }

    public void onViewDestroyed() {

    }

    private void verifyUser(){
        Context context = this.getView().getContext();
        Backendless.UserService.isValidLogin(new DefaultCallback<Boolean>(context) {
            @Override
            public void handleResponse(Boolean isValidLogin) {
                if (isValidLogin && Backendless.UserService.CurrentUser() != null) {
                    String currentUserId = Backendless.UserService.loggedInUser();

                    if (!currentUserId.equals(Constants.Common.EMPTY_STRING)) {
                        Backendless.UserService.findById(currentUserId, new DefaultCallback<BackendlessUser>(context) {
                            @Override
                            public void handleResponse(BackendlessUser currentUser) {
                                Backendless.UserService.setCurrentUser(currentUser);
                                super.handleResponse(currentUser);
                                successfulVerify();
                            }
                        });
                    }
                }
                super.handleResponse(isValidLogin);
            }
        });
    }

    private void successfulVerify() {
        ((HomeView)this.getView()).intentChat();
    }
}

