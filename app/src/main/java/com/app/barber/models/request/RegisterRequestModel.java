package com.app.barber.models.request;

import java.io.Serializable;

/**
 * Created by harish on 29/10/18.
 */

public class RegisterRequestModel implements Serializable {
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getInvitationCode() {
        return InvitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        InvitationCode = invitationCode;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public int getUserType() {
        return UserType;
    }

    public void setUserType(int userType) {
        UserType = userType;
    }

    String FirstName;

    public String getFullname() {
        return FullName;
    }

    public void setFullname(String fullname) {
        FullName = fullname;
    }

    String FullName;
    String LastName;
    String PhoneNumber;
    String InvitationCode;

    String UserName;
    String Email;
    String Password;

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    String ConfirmPassword;
    String DeviceId;
    int UserType;

    public String getBarberType() {
        return BarberType;
    }

    public void setBarberType(String barberType) {
        BarberType = barberType;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        PaymentType = paymentType;
    }

    public String getSpecializaions() {
        return Specializaions;
    }

    public void setSpecializaions(String specializaions) {
        Specializaions = specializaions;
    }

    String BarberType;
    String PaymentType;
    String Specializaions;

}
