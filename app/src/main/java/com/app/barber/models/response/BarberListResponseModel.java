package com.app.barber.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by harish on 26/11/18.
 */

public class BarberListResponseModel implements Serializable {

    /**
     * Message : Success
     * Status : 201
     * List : [{"BarberId":1054,"FullName":"Demo Barber","LastName":null,"UserName":"@demoBarber","Email":"demobarber@mailinator.com","PhoneNumber":"7814817584","ProfileImage":"http://barber.xicom.info/Content/images/team-2.jpg","Description":null,"City":"Chandigarh "},{"BarberId":1052,"FullName":"Test Barbet","LastName":null,"UserName":"@testbarber","Email":"Testbarber@mailinator.com","PhoneNumber":"9939393939","ProfileImage":"http://barber.xicom.info/Content/images/team-2.jpg","Description":null,"City":"Chandigarh "},{"BarberId":1057,"FullName":"Testing  Man","LastName":null,"UserName":"@testingman","Email":"testingman@mailinator.com","PhoneNumber":"9999999999","ProfileImage":"http://barber.xicom.info/Content/images/team-2.jpg","Description":null,"City":"bxbbdnf"},{"BarberId":1060,"FullName":"Test Nai","LastName":null,"UserName":"@testnai","Email":"testnai@gmail.com","PhoneNumber":"8888888880","ProfileImage":"http://barber.xicom.info/Content/images/team-2.jpg","Description":"This is all about me ","City":"Chandigarh "},{"BarberId":1053,"FullName":"user bar","LastName":null,"UserName":"barber1","Email":"Testbarber1@gmail.com","PhoneNumber":"993939393","ProfileImage":"http://barber.xicom.info/Uploads/ProfileImages/5e8a1e34-c657-4b44-8912-2ef583250a1c.jpg","Description":"Han I have a good time look good time ","City":"Chandigarh "}]
     */

    @SerializedName("Message")
    private String Message;
    @SerializedName("Status")
    private int Status;

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    @SerializedName("Count")
    private int Count;
    @SerializedName("List")
    private java.util.List<ListBean> List;

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

    public List<ListBean> getList() {
        return List;
    }

    public void setList(List<ListBean> List) {
        this.List = List;
    }
    public class Address {

        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("AddressLine1")
        @Expose
        private String addressLine1;
        @SerializedName("AddressLine2")
        @Expose
        private String addressLine2;
        @SerializedName("City")
        @Expose
        private String city;
        @SerializedName("Zip")
        @Expose
        private String zip;
        @SerializedName("Lat")
        @Expose
        private String lat;
        @SerializedName("Long")
        @Expose
        private String _long;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getAddressLine1() {
            return addressLine1;
        }

        public void setAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
        }

        public String getAddressLine2() {
            return addressLine2;
        }

        public void setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLong() {
            return _long;
        }

        public void setLong(String _long) {
            this._long = _long;
        }

    }
    public static class ListBean implements Serializable {
        /**
         * BarberId : 1054
         * FullName : Demo Barber
         * LastName : null
         * UserName : @demoBarber
         * Email : demobarber@mailinator.com
         * PhoneNumber : 7814817584
         * ProfileImage : http://barber.xicom.info/Content/images/team-2.jpg
         * Description : null
         * City : Chandigarh
         */

        @SerializedName("BarberId")
        private int BarberId;
        @SerializedName("FullName")
        private String FullName;
        @SerializedName("LastName")
        private Object LastName;
        @SerializedName("UserName")
        private String UserName;
        @SerializedName("Email")
        private String Email;
        @SerializedName("PhoneNumber")
        private String PhoneNumber;
        @SerializedName("ProfileImage")
        private String ProfileImage;
        @SerializedName("Description")
        private Object Description;
        @SerializedName("City")
        private String City;

        public String getDistance() {
            return Distance;
        }

        public void setDistance(String distance) {
            Distance = distance;
        }

        @SerializedName("Distance")
        private String Distance;

        public String getLat() {
            return Lat;
        }

        public void setLat(String lat) {
            Lat = lat;
        }

        public String getLong() {
            return Long;
        }

        public void setLong(String aLong) {
            Long = aLong;
        }

        @SerializedName("Lat")
        private String Lat;
        @SerializedName("Long")
        private String Long;
        @SerializedName("UserAddresses")
        private Address Address;

        public String getAvgRating() {
            return AvgRating;
        }

        public void setAvgRating(String avgRating) {
            AvgRating = avgRating;
        }

        public String getPuchRating() {
            return PuchRating;
        }

        public void setPuchRating(String puchRating) {
            PuchRating = puchRating;
        }

        @SerializedName("AvgRating")
        private String AvgRating;

        @SerializedName("PuchRating")
        private String PuchRating;

        public String getBarberType() {
            return barberType;
        }

        public void setBarberType(String barberType) {
            this.barberType = barberType;
        }

        @SerializedName("BarberType")
        private String barberType;

        public boolean isFavourite() {
            return IsFavourite;
        }

        public void setFavourite(boolean favourite) {
            IsFavourite = favourite;
        }

        @SerializedName("IsFavourite")
        private boolean IsFavourite;

        public boolean isSuperBarber() {
            return IsSuperBarber;
        }

        public void setSuperBarber(boolean superBarber) {
            IsSuperBarber = superBarber;
        }

        @SerializedName("IsSuperBarber")
        private boolean IsSuperBarber;

        public int getBarberId() {
            return BarberId;
        }

        public void setBarberId(int BarberId) {
            this.BarberId = BarberId;
        }

        public String getFullName() {
            return FullName;
        }

        public void setFullName(String FullName) {
            this.FullName = FullName;
        }

        public Object getLastName() {
            return LastName;
        }

        public void setLastName(Object LastName) {
            this.LastName = LastName;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public String getPhoneNumber() {
            return PhoneNumber;
        }

        public void setPhoneNumber(String PhoneNumber) {
            this.PhoneNumber = PhoneNumber;
        }

        public String getProfileImage() {
            return ProfileImage;
        }

        public void setProfileImage(String ProfileImage) {
            this.ProfileImage = ProfileImage;
        }

        public Object getDescription() {
            return Description;
        }

        public void setDescription(Object Description) {
            this.Description = Description;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }
    }
}
