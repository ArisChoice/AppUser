package com.app.barber.models;

import java.util.List;

/**
 * Created by harish on 23/10/18.
 */

public class TimeSlotsModel {

//    public boolean isOpened() {
//        return isOpened;
//    }
//
//    public void setOpened(boolean opened) {
//        isOpened = opened;
//    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getClosingHours() {
        return closingHours;
    }

    public void setClosingHours(String closingHours) {
        this.closingHours = closingHours;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

//    boolean isOpened;
    String openingHours;
    String closingHours;
    String day;

}
