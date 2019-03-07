package com.app.barber.models.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by harish on 4/1/19.
 */

public class BookingRatingResponseModel {

    /**
     * Message : Success
     * Status : 201
     * Response : {"PuchRating":"0","ValueRating":"0","HygieneRating":"0","ExpertiseRating":"0","Review":null,"BarberName":"Yo Barber","ProfileImage":"/Uploads/ProfileImages/0c242f13-151e-4cd3-a59e-4c5b8dd571e6.jpg","BarberType":"1,2,3"}
     */

    @SerializedName("Message")
    private String Message;
    @SerializedName("Status")
    private int Status;
    @SerializedName("Response")
    private ResponseBean Response;

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

    public ResponseBean getResponse() {
        return Response;
    }

    public void setResponse(ResponseBean Response) {
        this.Response = Response;
    }

    public static class ResponseBean {
        /**
         * PuchRating : 0
         * ValueRating : 0
         * HygieneRating : 0
         * ExpertiseRating : 0
         * Review : null
         * BarberName : Yo Barber
         * ProfileImage : /Uploads/ProfileImages/0c242f13-151e-4cd3-a59e-4c5b8dd571e6.jpg
         * BarberType : 1,2,3
         */

        @SerializedName("PuchRating")
        private String PuchRating;
        @SerializedName("ValueRating")
        private String ValueRating;
        @SerializedName("HygieneRating")
        private String HygieneRating;
        @SerializedName("ExpertiseRating")
        private String ExpertiseRating;
        @SerializedName("Review")
        private String Review;
        @SerializedName("BarberName")
        private String BarberName;
        @SerializedName("ProfileImage")
        private String ProfileImage;
        @SerializedName("BarberType")
        private String BarberType;

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String userName) {
            UserName = userName;
        }

        @SerializedName("UserName")
        private String UserName;

        public String getPaymentType() {
            return PaymentType;
        }

        public void setPaymentType(String paymentType) {
            PaymentType = paymentType;
        }

        @SerializedName("PaymentType")
        private String PaymentType;


        public String getPuchRating() {
            return PuchRating;
        }

        public void setPuchRating(String PuchRating) {
            this.PuchRating = PuchRating;
        }

        public String getValueRating() {
            return ValueRating;
        }

        public void setValueRating(String ValueRating) {
            this.ValueRating = ValueRating;
        }

        public String getHygieneRating() {
            return HygieneRating;
        }

        public void setHygieneRating(String HygieneRating) {
            this.HygieneRating = HygieneRating;
        }

        public String getExpertiseRating() {
            return ExpertiseRating;
        }

        public void setExpertiseRating(String ExpertiseRating) {
            this.ExpertiseRating = ExpertiseRating;
        }

        public String getReview() {
            return Review;
        }

        public void setReview(String Review) {
            this.Review = Review;
        }

        public String getBarberName() {
            return BarberName;
        }

        public void setBarberName(String BarberName) {
            this.BarberName = BarberName;
        }

        public String getProfileImage() {
            return ProfileImage;
        }

        public void setProfileImage(String ProfileImage) {
            this.ProfileImage = ProfileImage;
        }

        public String getBarberType() {
            return BarberType;
        }

        public void setBarberType(String BarberType) {
            this.BarberType = BarberType;
        }
    }
}
