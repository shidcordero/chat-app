package com.application.chat.chatapplication.model;

import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.support.design.widget.TextInputLayout;

import com.application.chat.chatapplication.BR;
import com.application.chat.chatapplication.utils.Constants;

public class Field implements Observable{
    private PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();

    private boolean isError;
    private String errorMessage;
    private String userInput;

    public Field() {
        userInput = Constants.Common.EMPTY_STRING;
    }

    @Bindable
    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        if (isError != error){
            isError = error;
            propertyChangeRegistry.notifyChange(this, BR.error);
        }
    }

    @Bindable
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        if (!errorMessage.equals(this.errorMessage)){
            this.errorMessage = errorMessage;
            propertyChangeRegistry.notifyChange(this, BR.errorMessage);
        }
    }

    @Bindable
    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        if (!userInput.equals(this.userInput)){
            this.userInput = userInput;
            propertyChangeRegistry.notifyChange(this, BR.userInput);
        }
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        propertyChangeRegistry.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        propertyChangeRegistry.remove(callback);
    }

    @BindingAdapter("errorText")
    public static void setErrorMessage(TextInputLayout view, String errorMessage) {
        view.setError(errorMessage);
    }

}
