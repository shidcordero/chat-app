package com.application.chat.chatapplication.ui.chat;

import com.application.chat.chatapplication.base.BasePresenter;
import com.application.chat.chatapplication.model.Chat;
import com.application.chat.chatapplication.model.Field;
import com.application.chat.chatapplication.utils.Constants;
import com.application.chat.chatapplication.utils.DefaultCallback;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.backendless.rt.data.EventHandler;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChatPresenter extends BasePresenter{
    private Disposable subscription;
    private Field chatField;
    private static DataQueryBuilder queryBuilder;

    ChatPresenter(@NotNull ChatView chatView) {
        super(chatView);
    }

    public void onViewCreated() {
        chatField = new Field();

        initChat();
    }

    public void onViewDestroyed() {
        disposeSubscription();
    }

    private void initChat() {
        queryBuilder = DataQueryBuilder.create().setRelated();
        queryBuilder.setSortBy(Constants.Common.CHAT_SORT);
        queryBuilder.setPageSize(Constants.Common.DEFAULT_PAGE_SIZE);

        EventHandler<Map> chatEventHandler = Backendless.Data.of(Constants.Common.CHAT_TABLE).rt();

        // listener for incoming message
        chatEventHandler.addCreateListener(new DefaultCallback<Map>(this.getView().getContext(), false)
        {
            @Override
            public void handleResponse( Map map )
            {
                successfulRealTimeCreate(new Chat(map));
            }

            @Override
            public void handleFault( BackendlessFault fault )
            {
                super.handleFault(fault);
            }
        } );

        findChat();
    }

    /**
     * Shows the next batch of data
     */
    public void nextPageChat(){
        queryBuilder.prepareNextPage();

        Chat.findAsync( queryBuilder, new DefaultCallback<List<Chat>>(this.getView().getContext(), false)
        {
            @Override
            public void handleResponse( List<Chat> response )
            {
                super.handleResponse( response );
                Collections.reverse(response);
                successfulNextPage(response);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                super.handleFault(fault);
            }
        });
    }

    /**
     * Returns the next page data to View for display
     */
    private void successfulNextPage(List<Chat> response) {
        ((ChatView)this.getView()).updateChats(response, false);
    }

    /**
     * Shows the initial chat data
     */
    private void findChat(){
        Chat.findAsync( queryBuilder, new DefaultCallback<List<Chat>>(this.getView().getContext(), false)
        {
            @Override
            public void handleResponse( List<Chat> response )
            {
                super.handleResponse( response );
                Collections.reverse(response);
                successfulGet(response);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                loadChat();
            }
        });
    }

    /**
     * Loads the chat data if there's internet connection
     */
    private void loadChat(){
        subscription = ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isConnected -> {
                    if (isConnected){
                        findChat();
                        disposeSubscription();
                    }
                });
    }

    /**
     * Returns the initial page data to View for display
     */
    private void successfulGet(List<Chat> chats) {
        ((ChatView)this.getView()).updateChats(chats, true);
    }

    /**
     * Used to display new data received from listener
     */
    private void successfulRealTimeCreate(Chat createdChat) {
        ((ChatView)this.getView()).updateChat(createdChat);
    }

    /**
     * Dispose the object
     */
    private void disposeSubscription() {
        Disposable disposable = this.subscription;
        if (this.subscription != null) {
            disposable.dispose();
        }
    }

    /**
     * Used to send the message
     */
    public void sendMessage(){
        BackendlessUser user = Backendless.UserService.CurrentUser();

        Chat chat = new Chat();
        chat.setMessage(chatField.getUserInput());
        chat.setOwnerId(user.getUserId());
        chat.setUsername((String) user.getProperty(Constants.Common.USERNAME));

        chat.saveAsync(new DefaultCallback<Chat>(this.getView().getContext(), false)
        {
            @Override
            public void handleResponse(Chat response) {
                super.handleResponse(response);
                successfulSend();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                showSendError();
            }
        });
    }

    /**
     * Clear text input
     */
    private void successfulSend() {
        chatField.setUserInput(Constants.Common.EMPTY_STRING);
        ((ChatView)this.getView()).toggleInput(true);
    }

    /**
     * Used to logout the user
     */
    public void logOut(){
        Backendless.UserService.logout(new AsyncCallback<Void>() {
            @Override
            public void handleResponse(Void response) {
                successfulLogout();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                showError();
            }
        });
    }

    /**
     * Intent logout - redirect to Sign Up
     */
    private void successfulLogout() {
        ((ChatView)this.getView()).intentLogOut();
    }

    private void showSendError() {
        ((ChatView)this.getView()).showError(Constants.Message.SEND_ERROR);
        ((ChatView)this.getView()).toggleInput(true);
    }

    private void showError() {
        ((ChatView)this.getView()).showError(Constants.Message.PROCESSING_ERROR);
    }

    public Field getChatField() {
        return chatField;
    }
}
