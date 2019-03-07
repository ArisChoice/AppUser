package com.app.barber.models;

/**
 * Created by harish on 14/12/18.
 */

public class ChangePasswordRequest {
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getUserType() {
        return UserType;
    }

    public void setUserType(int userType) {
        UserType = userType;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    String Password;
    int UserType;
    String PhoneNumber;
}
