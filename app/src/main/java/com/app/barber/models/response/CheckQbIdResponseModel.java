package com.app.barber.models.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by harish on 15/1/19.
 */

public class CheckQbIdResponseModel {

    /**
     * Message : Success
     * Status : 201
     * Id : 76190911
     */

    @SerializedName("Message")
    private String Message;
    @SerializedName("Status")
    private int Status;
    @SerializedName("Id")
    private int Id;

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

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
}
