package com.app.barber.models.request;

/**
 * Created by harish on 11/1/19.
 */

public class SaveQbDialogRequestModel {
    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getBarberId() {
        return BarberId;
    }

    public void setBarberId(int barberId) {
        BarberId = barberId;
    }

    public String getDialogId() {
        return DialogId;
    }

    public void setDialogId(String dialogId) {
        DialogId = dialogId;
    }

    int UserId;
    int BarberId;
    String DialogId;
//    int Id;

}
