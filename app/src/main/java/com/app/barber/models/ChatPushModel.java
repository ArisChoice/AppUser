package com.app.barber.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by harish on 14/1/19.
 */

public class ChatPushModel {

    /**
     * message : Han I you can get a taste apk
     */

    @SerializedName("message")
    private String message;

    public String getSenderName() {
        return SenderName;
    }

    public void setSenderName(String senderName) {
        SenderName = senderName;
    }

    @SerializedName("SenderName")
    private String SenderName;

    public String getDialogId() {
        return dialogId;
    }

    public void setDialogId(String dialogId) {
        this.dialogId = dialogId;
    }

    @SerializedName("dialogId")
    private String dialogId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
