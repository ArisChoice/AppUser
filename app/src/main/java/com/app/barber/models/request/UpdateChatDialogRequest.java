package com.app.barber.models.request;

/**
 * Created by harish on 16/1/19.
 */

public class UpdateChatDialogRequest {
    public String getDialogId() {
        return DialogId;
    }

    public void setDialogId(String dialogId) {
        DialogId = dialogId;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getMesssage() {
        return Messsage;
    }

    public void setMesssage(String messsage) {
        Messsage = messsage;
    }

    String DialogId;
    String Time;
    String Messsage;

}
