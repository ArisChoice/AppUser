package com.app.barber.models.chat_model;

public class ChatMessageModel {
    public static final int TYPE_MESSAGE_SENT = 0;
    public static final int TYPE_MESSAGE_RECEIVED = 1;

    public String getChat_dialog() {
        return chat_dialog;
    }

    public void setChat_dialog(String chat_dialog) {
        this.chat_dialog = chat_dialog;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(String receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    String chat_dialog;
    String message;
    String receiver_id;
    String sender_id;
    String created_at;

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    int Type;

    public void setType(String type) {
        this.type = type;
    }

    String type;

}
