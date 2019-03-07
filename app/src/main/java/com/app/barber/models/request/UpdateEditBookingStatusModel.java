package com.app.barber.models.request;

/**
 * Created by harish on 5/2/19.
 */

public class UpdateEditBookingStatusModel {
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

    public int getBookingId() {
        return BookingId;
    }

    public void setBookingId(int bookingId) {
        BookingId = bookingId;
    }

    public int getBookingType() {
        return BookingType;
    }

    public void setBookingType(int bookingType) {
        BookingType = bookingType;
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

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    String Date;
    String TimingSlot;
    int BookingId;
    int BookingType;
    int BarberId;
    int UserId;
    int EditedBy;
    boolean Status;

}
