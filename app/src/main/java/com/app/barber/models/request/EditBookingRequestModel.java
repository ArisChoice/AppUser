package com.app.barber.models.request;

import java.io.Serializable;

/**
 * Created by harish on 4/2/19.
 */

public class EditBookingRequestModel implements Serializable {
    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTimingSlot() {
        return TimingSlot;
    }

    public void setTimingSlot(String timingSlot) {
        TimingSlot = timingSlot;
    }

    public String getBookingType() {
        return BookingType;
    }

    public void setBookingType(String bookingType) {
        BookingType = bookingType;
    }

    public int getBookingId() {
        return BookingId;
    }

    public void setBookingId(int bookingId) {
        BookingId = bookingId;
    }

    public int getBarberId() {
        return BarberId;
    }

    public void setBarberId(int barberId) {
        BarberId = barberId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getEditedBy() {
        return EditedBy;
    }

    public void setEditedBy(int editedBy) {
        EditedBy = editedBy;
    }

    String Date;
    String TimingSlot;
    String BookingType;
    int BookingId;
    int BarberId;
    int UserId;
    int EditedBy;

    public String getServiceIds() {
        return ServiceIds;
    }

    public void setServiceIds(String serviceIds) {
        ServiceIds = serviceIds;
    }

    String ServiceIds;

}
