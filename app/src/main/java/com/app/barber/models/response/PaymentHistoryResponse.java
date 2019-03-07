package com.app.barber.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by harish on 1/2/19.
 */

public class PaymentHistoryResponse {


    /**
     * Message : List
     * Status : 201
     * Response : [{"Services":"Hairstyle ","Timings":"12:38 PM-12:58 PM","Date":"01 Feb","Amount":"20","PaymentType":"Card"}]
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
         * Services : Hairstyle
         * Timings : 12:38 PM-12:58 PM
         * Date : 01 Feb
         * Amount : 20
         * PaymentType : Card
         */

        @SerializedName("Services")
        private String Services;
        @SerializedName("Timings")
        private String Timings;
        @SerializedName("Date")
        private String Date;
        @SerializedName("Amount")
        private String Amount;
        @SerializedName("PaymentType")
        private String PaymentType;

        public boolean isTip() {
            return IsTip;
        }

        public void setTip(boolean tip) {
            IsTip = tip;
        }

        boolean IsTip;

        public String getServices() {
            return Services;
        }

        public void setServices(String Services) {
            this.Services = Services;
        }

        public String getTimings() {
            return Timings;
        }

        public void setTimings(String Timings) {
            this.Timings = Timings;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String Date) {
            this.Date = Date;
        }

        public String getAmount() {
            return Amount;
        }

        public void setAmount(String Amount) {
            this.Amount = Amount;
        }

        public String getPaymentType() {
            return PaymentType;
        }

        public void setPaymentType(String PaymentType) {
            this.PaymentType = PaymentType;
        }
    }
}
