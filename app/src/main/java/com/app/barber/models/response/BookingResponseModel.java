package com.app.barber.models.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by harish on 18/12/18.
 */

public class BookingResponseModel implements Serializable {

    /**
     * Message : Booking done successfully
     * Status : 201
     * Response : {"BookingUniqueId":"97813608","ProfileImage":"/Content/images/team-2.jpg","BannerImage":"/Uploads/BannerImages/2d9faf47-a02f-49c5-9e40-3aa81dd9b985.jpg","FullName":"Testing  Man","UserName":"@testingman","TimeSlot":"04:32 PM-04:52 PM","Date":"Monday,December 24","Address":{"Id":4,"AddressLine1":"Plot Number 22-23  IT Park","AddressLine2":"bsndn","City":"bxbbdnf","Zip":"161101","Lat":"30.7288669","Long":"76.8454426"},"PhoneNumber":"9999999999","PaymentType":"3","Invitationcode":null,"BarberType":"1","Services":[{"m_Item1":"System.Data.Entity.DynamicProxies.Service_30F4C105839A8443BF648AFC1DEA76D95149BE32C598A9FD060B2004593871F3","m_Item2":"25","m_Item3":"$"}]}
     */

    @SerializedName("Message")
    private String Message;
    @SerializedName("Status")
    private int Status;
    @SerializedName("Response")
    private ResponseBean Response;

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

    public ResponseBean getResponse() {
        return Response;
    }

    public void setResponse(ResponseBean Response) {
        this.Response = Response;
    }

    public static class ResponseBean implements Serializable {
        /**
         * BookingUniqueId : 97813608
         * ProfileImage : /Content/images/team-2.jpg
         * BannerImage : /Uploads/BannerImages/2d9faf47-a02f-49c5-9e40-3aa81dd9b985.jpg
         * FullName : Testing  Man
         * UserName : @testingman
         * TimeSlot : 04:32 PM-04:52 PM
         * Date : Monday,December 24
         * Address : {"Id":4,"AddressLine1":"Plot Number 22-23  IT Park","AddressLine2":"bsndn","City":"bxbbdnf","Zip":"161101","Lat":"30.7288669","Long":"76.8454426"}
         * PhoneNumber : 9999999999
         * PaymentType : 3
         * Invitationcode : null
         * BarberType : 1
         * Services : [{"m_Item1":"System.Data.Entity.DynamicProxies.Service_30F4C105839A8443BF648AFC1DEA76D95149BE32C598A9FD060B2004593871F3","m_Item2":"25","m_Item3":"$"}]
         */

        @SerializedName("BookingUniqueId")
        private String BookingUniqueId;
        @SerializedName("ProfileImage")
        private String ProfileImage;
        @SerializedName("BannerImage")
        private String BannerImage;
        @SerializedName("FullName")
        private String FullName;
        @SerializedName("UserName")
        private String UserName;
        @SerializedName("TimeSlot")
        private String TimeSlot;
        @SerializedName("Date")
        private String Date;
        @SerializedName("Address")
        private AddressBean Address;
        @SerializedName("PhoneNumber")
        private String PhoneNumber;
        @SerializedName("PaymentType")
        private String PaymentType;
        @SerializedName("Invitationcode")
        private Object Invitationcode;
        @SerializedName("BarberType")
        private String BarberType;

        public boolean isSuperBarber() {
            return IsSuperBarber;
        }

        public void setSuperBarber(boolean superBarber) {
            IsSuperBarber = superBarber;
        }

        @SerializedName("IsSuperBarber")
        private boolean IsSuperBarber;

        public String getAmountWithOutTax() {
            return AmountWithOutTax;
        }

        public void setAmountWithOutTax(String amountWithOutTax) {
            AmountWithOutTax = amountWithOutTax;
        }

        @SerializedName("AmountWithOutTax")
        private String AmountWithOutTax;

        public String getTotalDuration() {
            return TotalDuration;
        }

        public void setTotalDuration(String totalDuration) {
            TotalDuration = totalDuration;
        }

        @SerializedName("TotalDuration")
        private String TotalDuration;
        @SerializedName("Services")
        private List<ServicesBean> Services;

        public String getBookingUniqueId() {
            return BookingUniqueId;
        }

        public void setBookingUniqueId(String BookingUniqueId) {
            this.BookingUniqueId = BookingUniqueId;
        }

        public String getProfileImage() {
            return ProfileImage;
        }

        public void setProfileImage(String ProfileImage) {
            this.ProfileImage = ProfileImage;
        }

        public String getBannerImage() {
            return BannerImage;
        }

        public void setBannerImage(String BannerImage) {
            this.BannerImage = BannerImage;
        }

        public String getFullName() {
            return FullName;
        }

        public void setFullName(String FullName) {
            this.FullName = FullName;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getTimeSlot() {
            return TimeSlot;
        }

        public void setTimeSlot(String TimeSlot) {
            this.TimeSlot = TimeSlot;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String Date) {
            this.Date = Date;
        }

        public AddressBean getAddress() {
            return Address;
        }

        public void setAddress(AddressBean Address) {
            this.Address = Address;
        }

        public String getPhoneNumber() {
            return PhoneNumber;
        }

        public void setPhoneNumber(String PhoneNumber) {
            this.PhoneNumber = PhoneNumber;
        }

        public String getPaymentType() {
            return PaymentType;
        }

        public void setPaymentType(String PaymentType) {
            this.PaymentType = PaymentType;
        }

        public Object getInvitationcode() {
            return Invitationcode;
        }

        public void setInvitationcode(Object Invitationcode) {
            this.Invitationcode = Invitationcode;
        }

        public String getBarberType() {
            return BarberType;
        }

        public void setBarberType(String BarberType) {
            this.BarberType = BarberType;
        }

        public List<ServicesBean> getServices() {
            return Services;
        }

        public void setServices(List<ServicesBean> Services) {
            this.Services = Services;
        }

        public static class AddressBean implements Serializable  {
            /**
             * Id : 4
             * AddressLine1 : Plot Number 22-23  IT Park
             * AddressLine2 : bsndn
             * City : bxbbdnf
             * Zip : 161101
             * Lat : 30.7288669
             * Long : 76.8454426
             */

            @SerializedName("Id")
            private int Id;
            @SerializedName("AddressLine1")
            private String AddressLine1;
            @SerializedName("AddressLine2")
            private String AddressLine2;
            @SerializedName("City")
            private String City;
            @SerializedName("Zip")
            private String Zip;
            @SerializedName("Lat")
            private String Lat;
            @SerializedName("Long")
            private String Long;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getAddressLine1() {
                return AddressLine1;
            }

            public void setAddressLine1(String AddressLine1) {
                this.AddressLine1 = AddressLine1;
            }

            public String getAddressLine2() {
                return AddressLine2;
            }

            public void setAddressLine2(String AddressLine2) {
                this.AddressLine2 = AddressLine2;
            }

            public String getCity() {
                return City;
            }

            public void setCity(String City) {
                this.City = City;
            }

            public String getZip() {
                return Zip;
            }

            public void setZip(String Zip) {
                this.Zip = Zip;
            }

            public String getLat() {
                return Lat;
            }

            public void setLat(String Lat) {
                this.Lat = Lat;
            }

            public String getLong() {
                return Long;
            }

            public void setLong(String Long) {
                this.Long = Long;
            }
        }

        public static class ServicesBean implements Serializable  {
            /**
             * m_Item1 : System.Data.Entity.DynamicProxies.Service_30F4C105839A8443BF648AFC1DEA76D95149BE32C598A9FD060B2004593871F3
             * m_Item2 : 25
             * m_Item3 : $
             */

            @SerializedName("m_Item1")
            private String mItem1;
            @SerializedName("m_Item2")
            private String mItem2;
            @SerializedName("m_Item3")
            private String mItem3;

            public String getMItem1() {
                return mItem1;
            }

            public void setMItem1(String mItem1) {
                this.mItem1 = mItem1;
            }

            public String getMItem2() {
                return mItem2;
            }

            public void setMItem2(String mItem2) {
                this.mItem2 = mItem2;
            }

            public String getMItem3() {
                return mItem3;
            }

            public void setMItem3(String mItem3) {
                this.mItem3 = mItem3;
            }
        }
    }
}
