package com.app.barber.models;

import java.io.Serializable;

/**
 * Created by harish on 14/12/18.
 */

public class BookingData implements Serializable {
    int totalServices;

    public int getTotalServices() {
        return totalServices;
    }

    public void setTotalServices(int totalServices) {
        this.totalServices = totalServices;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getBookedServicesId() {
        return bookedServicesId;
    }

    public void setBookedServicesId(String bookedServicesId) {
        this.bookedServicesId = bookedServicesId;
    }

    int totalAmount;
    String bookedServicesId;

    public int getBarberId() {
        return barberId;
    }

    public void setBarberId(int barberId) {
        this.barberId = barberId;
    }

    int barberId;

    public String getSelectedSlot() {
        return selectedSlot;
    }

    public void setSelectedSlot(String selectedSlot) {
        this.selectedSlot = selectedSlot;
    }

    String selectedSlot;

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    String selectedDate;

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    String bookingType;

}
