package com.app.barber.models.request;

/**
 * Created by harish on 15/1/19.
 */

public class ValidatePhoneNumberModel {
    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getUserType() {
        return UserType;
    }

    public void setUserType(int userType) {
        UserType = userType;
    }

    String Phone;
    int UserType;
}
