package com.app.barber.models.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by harish on 26/12/18.
 */

public class NotificationResponseModel {

    /**
     * Message : Success
     * Status : 201
     * Response : [{"Id":2,"UserId":2059,"BarberId":1054,"Message":"Harry user Request Appointment","Services":"6","Amount":45,"BookingDate":"12/26/2018","NotificationType":1,"TimeSlot":"06:03 PM-06:28 PM","Status":3,"DateProcessed":"01:23:06","MessageToShow":"Harry user send you Appointment","StartingSlot":"Request for the day at06:03 PM"},{"Id":1,"UserId":2059,"BarberId":1054,"Message":"Harry user Request Appointment","Services":"5","Amount":50,"BookingDate":"12/26/2018","NotificationType":1,"TimeSlot":"06:53 PM-07:23 PM","Status":3,"DateProcessed":"01:33:06","MessageToShow":"Harry user send you Appointment","StartingSlot":"Request for the day at06:53 PM"}]
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

    public static class ResponseBean implements Serializable{
        /**
         * Id : 2
         * UserId : 2059
         * BarberId : 1054
         * Message : Harry user Request Appointment
         * Services : 6
         * Amount : 45
         * BookingDate : 12/26/2018
         * NotificationType : 1
         * TimeSlot : 06:03 PM-06:28 PM
         * Status : 3
         * DateProcessed : 01:23:06
         * MessageToShow : Harry user send you Appointment
         * StartingSlot : Request for the day at06:03 PM
         */

        @SerializedName("Id")
        private int Id;
        @SerializedName("UserId")
        private int UserId;
        @SerializedName("BarberId")
        private int BarberId;
        @SerializedName("Message")
        private String Message;
        @SerializedName("Services")
        private String Services;
        @SerializedName("Amount")
        private int Amount;
        @SerializedName("BookingDate")
        private String BookingDate;
        @SerializedName("NotificationType")
        private int NotificationType;
        @SerializedName("TimeSlot")
        private String TimeSlot;
        @SerializedName("Status")
        private int Status;
        @SerializedName("DateProcessed")
        private String DateProcessed;
        @SerializedName("MessageToShow")
        private String MessageToShow;
        @SerializedName("StartingSlot")
        private String StartingSlot;

        public int getBookingType() {
            return BookingType;
        }

        public void setBookingType(int bookingType) {
            BookingType = bookingType;
        }

        @SerializedName("BookingType")
        private int BookingType;

        public String getBookingId() {
            return BookingId;
        }

        public void setBookingId(String bookingId) {
            BookingId = bookingId;
        }

        @SerializedName("BookingId")
        private String BookingId;

        public boolean isToday() {
            return IsToday;
        }

        public void setToday(boolean today) {
            IsToday = today;
        }

        @SerializedName("IsToday")
        private boolean IsToday;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public int getBarberId() {
            return BarberId;
        }

        public void setBarberId(int BarberId) {
            this.BarberId = BarberId;
        }

        public String getMessage() {
            return Message;
        }

        public void setMessage(String Message) {
            this.Message = Message;
        }

        public String getServices() {
            return Services;
        }

        public void setServices(String Services) {
            this.Services = Services;
        }

        public int getAmount() {
            return Amount;
        }

        public void setAmount(int Amount) {
            this.Amount = Amount;
        }

        public String getBookingDate() {
            return BookingDate;
        }

        public void setBookingDate(String BookingDate) {
            this.BookingDate = BookingDate;
        }

        public int getNotificationType() {
            return NotificationType;
        }

        public void setNotificationType(int NotificationType) {
            this.NotificationType = NotificationType;
        }

        public String getTimeSlot() {
            return TimeSlot;
        }

        public void setTimeSlot(String TimeSlot) {
            this.TimeSlot = TimeSlot;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public String getDateProcessed() {
            return DateProcessed;
        }

        public void setDateProcessed(String DateProcessed) {
            this.DateProcessed = DateProcessed;
        }

        public String getMessageToShow() {
            return MessageToShow;
        }

        public void setMessageToShow(String MessageToShow) {
            this.MessageToShow = MessageToShow;
        }

        public String getStartingSlot() {
            return StartingSlot;
        }

        public void setStartingSlot(String StartingSlot) {
            this.StartingSlot = StartingSlot;
        }
    }
}
