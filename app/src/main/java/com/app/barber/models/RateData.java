package com.app.barber.models;

/**
 * Created by harish on 31/12/18.
 */

public class RateData {
    public int getRatingNum() {
        return ratingNum;
    }

    public void setRatingNum(int ratingNum) {
        this.ratingNum = ratingNum;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    int ratingNum;
    boolean isSelected;
}
