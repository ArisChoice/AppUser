package com.app.barber.ui.postauth.activities.socket_work.chat;

import com.google.gson.annotations.SerializedName;

public class MessageData {

    /**
     * nameValuePairs : {"sender_id":"17","receiver_id":"169","message":"gdfgfgdfgdf","created_at":"2019-03-05T09:27:02.946Z","chat_dialog":"16917"}
     */

    @SerializedName("nameValuePairs")
    private NameValuePairsBean nameValuePairs;

    public NameValuePairsBean getNameValuePairs() {
        return nameValuePairs;
    }

    public void setNameValuePairs(NameValuePairsBean nameValuePairs) {
        this.nameValuePairs = nameValuePairs;
    }

    public static class NameValuePairsBean {
        /**
         * sender_id : 17
         * receiver_id : 169
         * message : gdfgfgdfgdf
         * created_at : 2019-03-05T09:27:02.946Z
         * chat_dialog : 16917
         */

        @SerializedName("sender_id")
        private String senderId;
        @SerializedName("receiver_id")
        private String receiverId;
        @SerializedName("message")
        private String message;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("chat_dialog")
        private String chatDialog;

        public String getSenderId() {
            return senderId;
        }

        public void setSenderId(String senderId) {
            this.senderId = senderId;
        }

        public String getReceiverId() {
            return receiverId;
        }

        public void setReceiverId(String receiverId) {
            this.receiverId = receiverId;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getChatDialog() {
            return chatDialog;
        }

        public void setChatDialog(String chatDialog) {
            this.chatDialog = chatDialog;
        }
    }
}
