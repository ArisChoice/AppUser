package com.app.barber.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by harish on 5/12/18.
 */

public class SpecialisationResponseModel {

    /**
     * Message : Success
     * Status : 201
     * Response : [{"Id":1,"Type":"AfroCaribean hair"},{"Id":2,"Type":"Asian hair"},{"Id":3,"Type":"Caucasian hair"}]
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

    public static class ResponseBean {
        /**
         * Id : 1
         * Type : AfroCaribean hair
         */

        @SerializedName("Id")
        private int Id;
        @SerializedName("Type")
        private String Type;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        boolean isSelected;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }
    }
}
