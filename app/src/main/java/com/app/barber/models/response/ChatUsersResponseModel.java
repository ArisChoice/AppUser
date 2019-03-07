package com.app.barber.models.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by harish on 11/1/19.
 */

public class ChatUsersResponseModel {
    /**
     * Message : Participant List
     * Status : 201
     * Response : [{"Id":10,"DialogId":"5c384632a28f9a6d98f7713c","Image":"/Uploads/ProfileImages/9f682873-7711-4476-949f-ee5c57943c59.jpg","Name":"Test User"},{"Id":10,"DialogId":"5c384632a28f9a6d98f7713c","Image":"/Uploads/ProfileImages/9f682873-7711-4476-949f-ee5c57943c59.jpg","Name":"Test User"}]
     */

    @SerializedName("Message")
    private String Message;
    @SerializedName("Status")
    private int Status;
    @SerializedName("Response")
    private List<ResponseBean> Response;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public List<ResponseBean> getResponse() {
        return Response;
    }

    public void setResponse(List<ResponseBean> Response) {
        this.Response = Response;
    }

    public static class ResponseBean implements Serializable {
        /**
         * Id : 10
         * DialogId : 5c384632a28f9a6d98f7713c
         * Image : /Uploads/ProfileImages/9f682873-7711-4476-949f-ee5c57943c59.jpg
         * Name : Test User
         */

        @SerializedName("Id")
        private int Id;
        @SerializedName("DialogId")
        private String DialogId;
        @SerializedName("Image")
        private String Image;
        @SerializedName("Name")
        private String Name;

        public String getLastMessage() {
            return LastMessage;
        }

        public void setLastMessage(String lastMessage) {
            LastMessage = lastMessage;
        }

        public String getLastUpdateTime() {
            return LastUpdateTime;
        }

        public void setLastUpdateTime(String lastUpdateTime) {
            LastUpdateTime = lastUpdateTime;
        }

        @SerializedName("LastMessage")
        private String LastMessage;

        @SerializedName("LastUpdateTime")
        private String LastUpdateTime;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getDialogId() {
            return DialogId;
        }

        public void setDialogId(String DialogId) {
            this.DialogId = DialogId;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String Image) {
            this.Image = Image;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }
    }
}
