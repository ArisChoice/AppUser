package com.app.barber.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by harish on 29/1/19.
 */

public class RecentStatusResponseModel {

    /**
     * Message : Success
     * Status : 201
     * Response : {"IsNotificationPending":true,"BookingList":[{"BookingId":4,"BarberId":9,"BarberName":"Trendy Stop","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/82d47ba0-eb1d-4043-b209-162ebb19a28f.jpg","BookingType":1},{"BookingId":5,"BarberId":9,"BarberName":"Trendy Stop","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/82d47ba0-eb1d-4043-b209-162ebb19a28f.jpg","BookingType":1},{"BookingId":7,"BarberId":13,"BarberName":"Best Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/f4bb11f8-8706-4dd7-bf04-d6d81305d13e.jpg","BookingType":1},{"BookingId":8,"BarberId":13,"BarberName":"Best Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/f4bb11f8-8706-4dd7-bf04-d6d81305d13e.jpg","BookingType":1},{"BookingId":11,"BarberId":4,"BarberName":"Demo Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/f1ce6f2c-e92d-479b-b0a7-a0d58d7e27f7.jpg","BookingType":1},{"BookingId":12,"BarberId":4,"BarberName":"Demo Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/f1ce6f2c-e92d-479b-b0a7-a0d58d7e27f7.jpg","BookingType":1},{"BookingId":13,"BarberId":4,"BarberName":"Demo Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/f1ce6f2c-e92d-479b-b0a7-a0d58d7e27f7.jpg","BookingType":1},{"BookingId":17,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":1},{"BookingId":28,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":1},{"BookingId":30,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":1},{"BookingId":31,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":1},{"BookingId":32,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":1},{"BookingId":33,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":1},{"BookingId":34,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":1},{"BookingId":35,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":1},{"BookingId":42,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":1},{"BookingId":54,"BarberId":38,"BarberName":"test","BarbarProfilePic":"http://barber.xicom.info/Content/images/team-2.jpg","BookingType":1},{"BookingId":56,"BarberId":15,"BarberName":"Greg Bane","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/cffac9e4-8eef-4e22-9873-01936b37e5ee.jpg","BookingType":1},{"BookingId":58,"BarberId":4,"BarberName":"Demo Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/f1ce6f2c-e92d-479b-b0a7-a0d58d7e27f7.jpg","BookingType":1},{"BookingId":59,"BarberId":15,"BarberName":"Greg Bane","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/cffac9e4-8eef-4e22-9873-01936b37e5ee.jpg","BookingType":1},{"BookingId":60,"BarberId":15,"BarberName":"Greg Bane","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/cffac9e4-8eef-4e22-9873-01936b37e5ee.jpg","BookingType":1},{"BookingId":4,"BarberId":9,"BarberName":"Trendy Stop","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/82d47ba0-eb1d- 01-29 15:58:12.713 12322-15950/com.app.trimcheck.customer D/OkHttp: 4043-b209-162ebb19a28f.jpg","BookingType":2},{"BookingId":5,"BarberId":9,"BarberName":"Trendy Stop","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/82d47ba0-eb1d-4043-b209-162ebb19a28f.jpg","BookingType":2},{"BookingId":6,"BarberId":9,"BarberName":"Trendy Stop","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/82d47ba0-eb1d-4043-b209-162ebb19a28f.jpg","BookingType":2},{"BookingId":9,"BarberId":9,"BarberName":"Trendy Stop","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/82d47ba0-eb1d-4043-b209-162ebb19a28f.jpg","BookingType":2},{"BookingId":12,"BarberId":13,"BarberName":"Best Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/f4bb11f8-8706-4dd7-bf04-d6d81305d13e.jpg","BookingType":2},{"BookingId":13,"BarberId":13,"BarberName":"Best Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/f4bb11f8-8706-4dd7-bf04-d6d81305d13e.jpg","BookingType":2},{"BookingId":20,"BarberId":4,"BarberName":"Demo Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/f1ce6f2c-e92d-479b-b0a7-a0d58d7e27f7.jpg","BookingType":2},{"BookingId":21,"BarberId":4,"BarberName":"Demo Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/f1ce6f2c-e92d-479b-b0a7-a0d58d7e27f7.jpg","BookingType":2},{"BookingId":22,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":2},{"BookingId":34,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":2},{"BookingId":35,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":2},{"BookingId":38,"BarberId":13,"BarberName":"Best Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/f4bb11f8-8706-4dd7-bf04-d6d81305d13e.jpg","BookingType":2},{"BookingId":39,"BarberId":32,"BarberName":"new test","BarbarProfilePic":"http://barber.xicom.info/Content/images/team-2.jpg","BookingType":2},{"BookingId":41,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":2},{"BookingId":42,"BarberId":4,"BarberName":"Demo Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/f1ce6f2c-e92d-479b-b0a7-a0d58d7e27f7.jpg","BookingType":2}],"Validations":{"IsAddressAdded":false,"IsCallOutAdded":false,"IsOpeningHoursAdded":false,"IsDistrictAdded":false,"IsBarberTypeAdded":false,"IsPaymentTypeAdded":false,"IsStripeConnected":false,"IsServiceAdded":false,"IsZeroAmountServiceValidation":false}}
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
         * IsNotificationPending : true
         * BookingList : [{"BookingId":4,"BarberId":9,"BarberName":"Trendy Stop","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/82d47ba0-eb1d-4043-b209-162ebb19a28f.jpg","BookingType":1},{"BookingId":5,"BarberId":9,"BarberName":"Trendy Stop","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/82d47ba0-eb1d-4043-b209-162ebb19a28f.jpg","BookingType":1},{"BookingId":7,"BarberId":13,"BarberName":"Best Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/f4bb11f8-8706-4dd7-bf04-d6d81305d13e.jpg","BookingType":1},{"BookingId":8,"BarberId":13,"BarberName":"Best Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/f4bb11f8-8706-4dd7-bf04-d6d81305d13e.jpg","BookingType":1},{"BookingId":11,"BarberId":4,"BarberName":"Demo Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/f1ce6f2c-e92d-479b-b0a7-a0d58d7e27f7.jpg","BookingType":1},{"BookingId":12,"BarberId":4,"BarberName":"Demo Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/f1ce6f2c-e92d-479b-b0a7-a0d58d7e27f7.jpg","BookingType":1},{"BookingId":13,"BarberId":4,"BarberName":"Demo Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/f1ce6f2c-e92d-479b-b0a7-a0d58d7e27f7.jpg","BookingType":1},{"BookingId":17,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":1},{"BookingId":28,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":1},{"BookingId":30,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":1},{"BookingId":31,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":1},{"BookingId":32,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":1},{"BookingId":33,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":1},{"BookingId":34,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":1},{"BookingId":35,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":1},{"BookingId":42,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":1},{"BookingId":54,"BarberId":38,"BarberName":"test","BarbarProfilePic":"http://barber.xicom.info/Content/images/team-2.jpg","BookingType":1},{"BookingId":56,"BarberId":15,"BarberName":"Greg Bane","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/cffac9e4-8eef-4e22-9873-01936b37e5ee.jpg","BookingType":1},{"BookingId":58,"BarberId":4,"BarberName":"Demo Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/f1ce6f2c-e92d-479b-b0a7-a0d58d7e27f7.jpg","BookingType":1},{"BookingId":59,"BarberId":15,"BarberName":"Greg Bane","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/cffac9e4-8eef-4e22-9873-01936b37e5ee.jpg","BookingType":1},{"BookingId":60,"BarberId":15,"BarberName":"Greg Bane","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/cffac9e4-8eef-4e22-9873-01936b37e5ee.jpg","BookingType":1},{"BookingId":4,"BarberId":9,"BarberName":"Trendy Stop","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/82d47ba0-eb1d- 01-29 15:58:12.713 12322-15950/com.app.trimcheck.customer D/OkHttp: 4043-b209-162ebb19a28f.jpg","BookingType":2},{"BookingId":5,"BarberId":9,"BarberName":"Trendy Stop","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/82d47ba0-eb1d-4043-b209-162ebb19a28f.jpg","BookingType":2},{"BookingId":6,"BarberId":9,"BarberName":"Trendy Stop","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/82d47ba0-eb1d-4043-b209-162ebb19a28f.jpg","BookingType":2},{"BookingId":9,"BarberId":9,"BarberName":"Trendy Stop","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/82d47ba0-eb1d-4043-b209-162ebb19a28f.jpg","BookingType":2},{"BookingId":12,"BarberId":13,"BarberName":"Best Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/f4bb11f8-8706-4dd7-bf04-d6d81305d13e.jpg","BookingType":2},{"BookingId":13,"BarberId":13,"BarberName":"Best Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/f4bb11f8-8706-4dd7-bf04-d6d81305d13e.jpg","BookingType":2},{"BookingId":20,"BarberId":4,"BarberName":"Demo Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/f1ce6f2c-e92d-479b-b0a7-a0d58d7e27f7.jpg","BookingType":2},{"BookingId":21,"BarberId":4,"BarberName":"Demo Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/f1ce6f2c-e92d-479b-b0a7-a0d58d7e27f7.jpg","BookingType":2},{"BookingId":22,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":2},{"BookingId":34,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":2},{"BookingId":35,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":2},{"BookingId":38,"BarberId":13,"BarberName":"Best Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/f4bb11f8-8706-4dd7-bf04-d6d81305d13e.jpg","BookingType":2},{"BookingId":39,"BarberId":32,"BarberName":"new test","BarbarProfilePic":"http://barber.xicom.info/Content/images/team-2.jpg","BookingType":2},{"BookingId":41,"BarberId":17,"BarberName":"Rex Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/7372b338-6c8c-4116-a474-f4b6421411f7.png","BookingType":2},{"BookingId":42,"BarberId":4,"BarberName":"Demo Barber","BarbarProfilePic":"http://barber.xicom.info/Uploads/ProfileImages/f1ce6f2c-e92d-479b-b0a7-a0d58d7e27f7.jpg","BookingType":2}]
         * Validations : {"IsAddressAdded":false,"IsCallOutAdded":false,"IsOpeningHoursAdded":false,"IsDistrictAdded":false,"IsBarberTypeAdded":false,"IsPaymentTypeAdded":false,"IsStripeConnected":false,"IsServiceAdded":false,"IsZeroAmountServiceValidation":false}
         */

        @SerializedName("IsNotificationPending")
        private boolean IsNotificationPending;
        @SerializedName("Validations")
        private ValidationsBean Validations;
        @SerializedName("BookingList")
        private List<BookingListBean> BookingList;

        public boolean isIsNotificationPending() {
            return IsNotificationPending;
        }

        public void setIsNotificationPending(boolean IsNotificationPending) {
            this.IsNotificationPending = IsNotificationPending;
        }
        public List<MyBookingsResponseMOdel.ListBean> getBarberEditList() {
            return barberEditList;
        }

        public void setBarberEditList(List<MyBookingsResponseMOdel.ListBean> barberEditList) {
            this.barberEditList = barberEditList;
        }

        @SerializedName("UserEditList")
        @Expose
        private List<MyBookingsResponseMOdel.ListBean> barberEditList = null;
        public ValidationsBean getValidations() {
            return Validations;
        }

        public void setValidations(ValidationsBean Validations) {
            this.Validations = Validations;
        }

        public List<BookingListBean> getBookingList() {
            return BookingList;
        }

        public void setBookingList(List<BookingListBean> BookingList) {
            this.BookingList = BookingList;
        }

        public static class ValidationsBean {
            /**
             * IsAddressAdded : false
             * IsCallOutAdded : false
             * IsOpeningHoursAdded : false
             * IsDistrictAdded : false
             * IsBarberTypeAdded : false
             * IsPaymentTypeAdded : false
             * IsStripeConnected : false
             * IsServiceAdded : false
             * IsZeroAmountServiceValidation : false
             */

            @SerializedName("IsAddressAdded")
            private boolean IsAddressAdded;
            @SerializedName("IsCallOutAdded")
            private boolean IsCallOutAdded;
            @SerializedName("IsOpeningHoursAdded")
            private boolean IsOpeningHoursAdded;
            @SerializedName("IsDistrictAdded")
            private boolean IsDistrictAdded;
            @SerializedName("IsBarberTypeAdded")
            private boolean IsBarberTypeAdded;
            @SerializedName("IsPaymentTypeAdded")
            private boolean IsPaymentTypeAdded;
            @SerializedName("IsStripeConnected")
            private boolean IsStripeConnected;
            @SerializedName("IsServiceAdded")
            private boolean IsServiceAdded;
            @SerializedName("IsZeroAmountServiceValidation")
            private boolean IsZeroAmountServiceValidation;

            public boolean isIsAddressAdded() {
                return IsAddressAdded;
            }

            public void setIsAddressAdded(boolean IsAddressAdded) {
                this.IsAddressAdded = IsAddressAdded;
            }

            public boolean isIsCallOutAdded() {
                return IsCallOutAdded;
            }

            public void setIsCallOutAdded(boolean IsCallOutAdded) {
                this.IsCallOutAdded = IsCallOutAdded;
            }

            public boolean isIsOpeningHoursAdded() {
                return IsOpeningHoursAdded;
            }

            public void setIsOpeningHoursAdded(boolean IsOpeningHoursAdded) {
                this.IsOpeningHoursAdded = IsOpeningHoursAdded;
            }

            public boolean isIsDistrictAdded() {
                return IsDistrictAdded;
            }

            public void setIsDistrictAdded(boolean IsDistrictAdded) {
                this.IsDistrictAdded = IsDistrictAdded;
            }

            public boolean isIsBarberTypeAdded() {
                return IsBarberTypeAdded;
            }

            public void setIsBarberTypeAdded(boolean IsBarberTypeAdded) {
                this.IsBarberTypeAdded = IsBarberTypeAdded;
            }

            public boolean isIsPaymentTypeAdded() {
                return IsPaymentTypeAdded;
            }

            public void setIsPaymentTypeAdded(boolean IsPaymentTypeAdded) {
                this.IsPaymentTypeAdded = IsPaymentTypeAdded;
            }

            public boolean isIsStripeConnected() {
                return IsStripeConnected;
            }

            public void setIsStripeConnected(boolean IsStripeConnected) {
                this.IsStripeConnected = IsStripeConnected;
            }

            public boolean isIsServiceAdded() {
                return IsServiceAdded;
            }

            public void setIsServiceAdded(boolean IsServiceAdded) {
                this.IsServiceAdded = IsServiceAdded;
            }

            public boolean isIsZeroAmountServiceValidation() {
                return IsZeroAmountServiceValidation;
            }

            public void setIsZeroAmountServiceValidation(boolean IsZeroAmountServiceValidation) {
                this.IsZeroAmountServiceValidation = IsZeroAmountServiceValidation;
            }
        }

        public static class BookingListBean {
            /**
             * BookingId : 4
             * BarberId : 9
             * BarberName : Trendy Stop
             * BarbarProfilePic : http://barber.xicom.info/Uploads/ProfileImages/82d47ba0-eb1d-4043-b209-162ebb19a28f.jpg
             * BookingType : 1
             */

            @SerializedName("BookingId")
            private int BookingId;
            @SerializedName("BarberId")
            private int BarberId;
            @SerializedName("BarberName")
            private String BarberName;
            @SerializedName("BarbarProfilePic")
            private String BarbarProfilePic;
            @SerializedName("BookingType")
            private int BookingType;

            public int getBookingId() {
                return BookingId;
            }

            public void setBookingId(int BookingId) {
                this.BookingId = BookingId;
            }

            public int getBarberId() {
                return BarberId;
            }

            public void setBarberId(int BarberId) {
                this.BarberId = BarberId;
            }

            public String getBarberName() {
                return BarberName;
            }

            public void setBarberName(String BarberName) {
                this.BarberName = BarberName;
            }

            public String getBarbarProfilePic() {
                return BarbarProfilePic;
            }

            public void setBarbarProfilePic(String BarbarProfilePic) {
                this.BarbarProfilePic = BarbarProfilePic;
            }

            public int getBookingType() {
                return BookingType;
            }

            public void setBookingType(int BookingType) {
                this.BookingType = BookingType;
            }
        }
    }
}
