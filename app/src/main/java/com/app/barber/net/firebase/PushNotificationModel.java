package com.app.barber.net.firebase;

import com.google.gson.annotations.SerializedName;

/**
 * Created by harish on 20/9/17.
 */

/*{
  "TimeSlot": "06:53 PM-07:23 PM",
          "UserId": "2059",
          "BarberId": "1054",
          "Message": "Harry user Request Appointment",
          "DeviceType": "1",
          "NotificationId": "1",
          "alert": "Harry user Request Appointment",
          "badge": "0",
          "sound": "sound.caf",
          "DeviceToken": "dOpvDe1s0dE:APA91bFP3-9zAYqnLepD6K4mVQ0Dhwv3sUTitSYKBIa0bcpmj5l3d6iZk5bDMWvi1lNyjV9V1w8eLEo5dNNsotVayuSO04UVXfcXh_6hEZ7wuAJj1gJVs6eJ6rh1Qi2seLWQgdBKe5vM",
          "NotificationType": "1",
          "Services": "5",
          "Amount": "50",
          "BookingDate": "2018-12-26T00:00:00"
          }*/
public class PushNotificationModel {

    /**
     * TimeSlot : 06:53 PM-07:23 PM
     * UserId : 2059
     * BarberId : 1054
     * Message : Harry user Request Appointment
     * DeviceType : 1
     * NotificationId : 1
     * alert : Harry user Request Appointment
     * badge : 0
     * sound : sound.caf
     * DeviceToken : dOpvDe1s0dE:APA91bFP3-9zAYqnLepD6K4mVQ0Dhwv3sUTitSYKBIa0bcpmj5l3d6iZk5bDMWvi1lNyjV9V1w8eLEo5dNNsotVayuSO04UVXfcXh_6hEZ7wuAJj1gJVs6eJ6rh1Qi2seLWQgdBKe5vM
     * NotificationType : 1
     * Services : 5
     * Amount : 50
     * BookingDate : 2018-12-26T00:00:00
     */

    @SerializedName("TimeSlot")
    private String TimeSlot;
    @SerializedName("UserId")
    private String UserId;
    @SerializedName("BarberId")
    private String BarberId;
    @SerializedName("Message")
    private String Message;
    @SerializedName("DeviceType")
    private String DeviceType;
    @SerializedName("NotificationId")
    private String NotificationId;
    @SerializedName("alert")
    private String alert;
    @SerializedName("badge")
    private String badge;
    @SerializedName("sound")
    private String sound;
    @SerializedName("DeviceToken")
    private String DeviceToken;
    @SerializedName("NotificationType")
    private String NotificationType;
    @SerializedName("Services")
    private String Services;
    @SerializedName("Amount")
    private String Amount;
    @SerializedName("BookingDate")
    private String BookingDate;

    public String getQbdialog() {
        return qbdialog;
    }

    public void setQbdialog(String qbdialog) {
        this.qbdialog = qbdialog;
    }

    @SerializedName("qbdialog")
    private String qbdialog;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("name")
    private String name;

    public String getTimeSlot() {
        return TimeSlot;
    }

    public void setTimeSlot(String TimeSlot) {
        this.TimeSlot = TimeSlot;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getBarberId() {
        return BarberId;
    }

    public void setBarberId(String BarberId) {
        this.BarberId = BarberId;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(String DeviceType) {
        this.DeviceType = DeviceType;
    }

    public String getNotificationId() {
        return NotificationId;
    }

    public void setNotificationId(String NotificationId) {
        this.NotificationId = NotificationId;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getDeviceToken() {
        return DeviceToken;
    }

    public void setDeviceToken(String DeviceToken) {
        this.DeviceToken = DeviceToken;
    }

    public String getNotificationType() {
        return NotificationType;
    }

    public void setNotificationType(String NotificationType) {
        this.NotificationType = NotificationType;
    }

    public String getServices() {
        return Services;
    }

    public void setServices(String Services) {
        this.Services = Services;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    public String getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(String BookingDate) {
        this.BookingDate = BookingDate;
    }
}
