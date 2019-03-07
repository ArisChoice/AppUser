package com.app.barber.models.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by harish on 2/11/18.
 */

public class ServiceListResponseModel {

    /**
     * Message : Success
     * Status : 201
     * Response : [{"Id":2,"ServiceName":"test","Duration":"25","Price":40,"PriceType":"typojj"},{"Id":3,"ServiceName":"save","Duration":"20","Price":38,"PriceType":"djfj k"}]
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
         * Id : 2
         * ServiceName : test
         * Duration : 25
         * Price : 40
         * PriceType : typojj
         */

        @SerializedName("Id")
        private int Id;
        @SerializedName("ServiceName")
        private String ServiceName;
        @SerializedName("Duration")
        private String Duration;
        @SerializedName("Price")
        private int Price;
        @SerializedName("PriceType")
        private String PriceType;

        public int getType() {
            return Type;
        }

        public void setType(int type) {
            Type = type;
        }

        @SerializedName("Type")
        private int Type;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        private boolean isSelected;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getServiceName() {
            return ServiceName;
        }

        public void setServiceName(String ServiceName) {
            this.ServiceName = ServiceName;
        }

        public String getDuration() {
            return Duration;
        }

        public void setDuration(String Duration) {
            this.Duration = Duration;
        }

        public int getPrice() {
            return Price;
        }

        public void setPrice(int Price) {
            this.Price = Price;
        }

        public String getPriceType() {
            return PriceType;
        }

        public void setPriceType(String PriceType) {
            this.PriceType = PriceType;
        }
    }
}
