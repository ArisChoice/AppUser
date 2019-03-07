package com.app.barber.models.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by harish on 30/10/18.
 */

public class BaseResponse {

    /**
     * Message : Message
     * Status : 401
     */

    @SerializedName("Message")
    private String Message;
    @SerializedName("Status")
    private int Status;

    public String getCardId() {
        return CardId;
    }

    public void setCardId(String cardId) {
        CardId = cardId;
    }

    @SerializedName("CardId")//optional
    private String CardId;//optional


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    private String Id;

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
}
