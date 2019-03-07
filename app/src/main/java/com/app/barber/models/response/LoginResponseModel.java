package com.app.barber.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by harish on 31/10/18.
 */

public class LoginResponseModel implements Serializable {

    /**
     * Message : Success
     * Status : 201
     * User : {"UserID":3,"FirstName":"Harish","LastName":"Test","Email":"harishtest@gmail.com","UserType":1,"IsDeleted":false,"UserName":"Harish Test","PhoneNumber":"9638527410","Invitationcode":null,"BarberType":null,"Specializaions":null,"PaymentType":null,"SessionId":"e9f5a2fa-1d8d-4ddf-824f-7c85d6623e82","CallOutHours":[],"OpeningHours":[],"Services":[],"UserAddresses":[]}
     */

    @SerializedName("Message")
    private String Message;
    @SerializedName("Status")
    private int Status;
    @SerializedName("User")
    private UserBean User;

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

    public UserBean getUser() {
        return User;
    }

    public void setUser(UserBean User) {
        this.User = User;
    }

    public static class UserBean {
        /**
         * UserID : 3
         * FirstName : Harish
         * LastName : Test
         * Email : harishtest@gmail.com
         * UserType : 1
         * IsDeleted : false
         * UserName : Harish Test
         * PhoneNumber : 9638527410
         * Invitationcode : null
         * BarberType : null
         * Specializaions : null
         * PaymentType : null
         * SessionId : e9f5a2fa-1d8d-4ddf-824f-7c85d6623e82
         * CallOutHours : []
         * OpeningHours : []
         * Services : []
         * UserAddresses : []
         */

        @SerializedName("UserID")
        private int UserID;

        public String getFullName() {
            return FullName;
        }

        public void setFullName(String fullName) {
            FullName = fullName;
        }

        @SerializedName("FullName")
        private String FullName;
        @SerializedName("FirstName")
        private String FirstName;
        @SerializedName("LastName")
        private String LastName;
        @SerializedName("Email")
        private String Email;

        public String getProfileImage() {
            return ProfileImage;
        }

        public void setProfileImage(String profileImage) {
            ProfileImage = profileImage;
        }

        @SerializedName("ProfileImage")
        private String ProfileImage;
        @SerializedName("UserType")
        private int UserType;
        @SerializedName("IsDeleted")
        private boolean IsDeleted;
        @SerializedName("UserName")
        private String UserName;
        @SerializedName("PhoneNumber")
        private String PhoneNumber;
        @SerializedName("Invitationcode")
        private String Invitationcode;
        @SerializedName("BarberType")
        private String BarberType;
        @SerializedName("Specializaions")
        private String Specializaions;
        @SerializedName("PaymentType")
        private String PaymentType;
        @SerializedName("SessionId")
        private String SessionId;

        public boolean isAddressAdded() {
            return IsAddressAdded;
        }

        public void setAddressAdded(boolean addressAdded) {
            IsAddressAdded = addressAdded;
        }

        @SerializedName("IsAddressAdded")
        private boolean IsAddressAdded;

        @SerializedName("CallOutHours")
        private List<?> CallOutHours;
        @SerializedName("OpeningHours")
        @Expose
        private List<UpdateDataResponse.OpeningHoursBean> openingHours = null;
        @SerializedName("Services")
        @Expose
        private List<UpdateDataResponse.Service> services = null;
        @SerializedName("UserAddresses")
        private Object UserAddresses;

        public String getQBId() {
            return QBId;
        }

        public void setQBId(String QBId) {
            this.QBId = QBId;
        }

        @SerializedName("QBId")
        private String QBId;

        public int getUserID() {
            return UserID;
        }

        public void setUserID(int UserID) {
            this.UserID = UserID;
        }

        public String getFirstName() {
            return FirstName;
        }

        public void setFirstName(String FirstName) {
            this.FirstName = FirstName;
        }

        public String getLastName() {
            return LastName;
        }

        public void setLastName(String LastName) {
            this.LastName = LastName;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public int getUserType() {
            return UserType;
        }

        public void setUserType(int UserType) {
            this.UserType = UserType;
        }

        public boolean isIsDeleted() {
            return IsDeleted;
        }

        public void setIsDeleted(boolean IsDeleted) {
            this.IsDeleted = IsDeleted;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getPhoneNumber() {
            return PhoneNumber;
        }

        public void setPhoneNumber(String PhoneNumber) {
            this.PhoneNumber = PhoneNumber;
        }

        public String getInvitationcode() {
            return Invitationcode;
        }

        public void setInvitationcode(String Invitationcode) {
            this.Invitationcode = Invitationcode;
        }

        public String getBarberType() {
            return BarberType;
        }

        public void setBarberType(String BarberType) {
            this.BarberType = BarberType;
        }

        public String getSpecializaions() {
            return Specializaions;
        }

        public void setSpecializaions(String Specializaions) {
            this.Specializaions = Specializaions;
        }

        public String getPaymentType() {
            return PaymentType;
        }

        public void setPaymentType(String PaymentType) {
            this.PaymentType = PaymentType;
        }

        public String getSessionId() {
            return SessionId;
        }

        public void setSessionId(String SessionId) {
            this.SessionId = SessionId;
        }

        public List<?> getCallOutHours() {
            return CallOutHours;
        }

        public void setCallOutHours(List<?> CallOutHours) {
            this.CallOutHours = CallOutHours;
        }

        public List<UpdateDataResponse.OpeningHoursBean> getOpeningHours() {
            return openingHours;
        }

        public void setOpeningHours(List<UpdateDataResponse.OpeningHoursBean> openingHours) {
            this.openingHours = openingHours;
        }

        public List<UpdateDataResponse.Service> getServices() {
            return services;
        }

        public void setServices(List<UpdateDataResponse.Service> services) {
            this.services = services;
        }

        public Object getUserAddresses() {
            return UserAddresses;
        }

        public void setUserAddresses(Object UserAddresses) {
            this.UserAddresses = UserAddresses;
        }
    }

   /* public class OpeningHour implements Serializable {

        @SerializedName("Day")
        @Expose
        private String day;
        @SerializedName("OpeningHours")
        @Expose
        private String openingHours;
        @SerializedName("ClosingHours")
        @Expose
        private String closingHours;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

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

    }*/
}
