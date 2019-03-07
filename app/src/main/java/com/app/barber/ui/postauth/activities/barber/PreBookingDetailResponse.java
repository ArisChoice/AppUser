package com.app.barber.ui.postauth.activities.barber;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by harish on 17/12/18.
 */

public class PreBookingDetailResponse implements Serializable {

    /**
     * Message : Success
     * Status : 201
     * List : {"ProfileImage":"/Content/images/team-2.jpg","BannerImage":"/Uploads/BannerImages/a1368fbd-fba2-43a0-9381-1c5961af98a4.jpg","FullName":"Demo Barber","UserName":"@demoBarber","TimeSlot":"08:00 AM-08:20 AM","Date":"Monday,December 17","Address":{"Id":3,"AddressLine1":"Rajiv Gandhi IT Park (DLF Building) Tower A  Ground Floor","AddressLine2":"Chandigarh ","City":"Chandigarh ","Zip":"160101","Lat":"30.7266607","Long":null},"PhoneNumber":"7814817584","PaymentType":"2","Invitationcode":null,"Services":[{"m_Item1":"Shave ","m_Item2":"$20"},{"m_Item1":"Total","m_Item2":"$20"}]}
     */

    @SerializedName("Message")
    private String Message;
    @SerializedName("Status")
    private int Status;
    @SerializedName("List")
    private ListBean List;

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

    public ListBean getList() {
        return List;
    }

    public void setList(ListBean List) {
        this.List = List;
    }

    public static class ListBean implements Serializable {
        /**
         * ProfileImage : /Content/images/team-2.jpg
         * BannerImage : /Uploads/BannerImages/a1368fbd-fba2-43a0-9381-1c5961af98a4.jpg
         * FullName : Demo Barber
         * UserName : @demoBarber
         * TimeSlot : 08:00 AM-08:20 AM
         * Date : Monday,December 17
         * Address : {"Id":3,"AddressLine1":"Rajiv Gandhi IT Park (DLF Building) Tower A  Ground Floor","AddressLine2":"Chandigarh ","City":"Chandigarh ","Zip":"160101","Lat":"30.7266607","Long":null}
         * PhoneNumber : 7814817584
         * PaymentType : 2
         * Invitationcode : null
         * Services : [{"m_Item1":"Shave ","m_Item2":"$20"},{"m_Item1":"Total","m_Item2":"$20"}]
         */

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


        public String getStripeAccountId() {
            return StripeAccountId;
        }

        public void setStripeAccountId(String stripeAccountId) {
            StripeAccountId = stripeAccountId;
        }

        @SerializedName("StripeAccountId")
        private String StripeAccountId;

        public boolean isSuperBarber() {
            return IsSuperBarber;
        }

        public void setSuperBarber(boolean superBarber) {
            IsSuperBarber = superBarber;
        }

        @SerializedName("IsSuperBarber")
        private boolean IsSuperBarber;

        public String getBarberType() {
            return BarberType;
        }

        public void setBarberType(String barberType) {
            BarberType = barberType;
        }

        @SerializedName("BarberType")
        private String BarberType;

        @SerializedName("Services")
        private java.util.List<ServicesBean> Services;

        public String getTotalDuration() {
            return TotalDuration;
        }

        public void setTotalDuration(String totalDuration) {
            TotalDuration = totalDuration;
        }

        @SerializedName("TotalDuration")
        private String TotalDuration;

        public String getAmountWithOutTax() {
            return AmountWithOutTax;
        }

        public void setAmountWithOutTax(String amountWithOutTax) {
            AmountWithOutTax = amountWithOutTax;
        }

        @SerializedName("AmountWithOutTax")
        private String AmountWithOutTax;

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

        public List<ServicesBean> getServices() {
            return Services;
        }

        public void setServices(List<ServicesBean> Services) {
            this.Services = Services;
        }

        public static class AddressBean implements Serializable {
            /**
             * Id : 3
             * AddressLine1 : Rajiv Gandhi IT Park (DLF Building) Tower A  Ground Floor
             * AddressLine2 : Chandigarh
             * City : Chandigarh
             * Zip : 160101
             * Lat : 30.7266607
             * Long : null
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
            private Object Long;

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

            public Object getLong() {
                return Long;
            }

            public void setLong(Object Long) {
                this.Long = Long;
            }
        }

        public static class ServicesBean implements Serializable {
            /**
             * m_Item1 : Shave
             * m_Item2 : $20
             */

            @SerializedName("m_Item1")
            private String mItem1;
            @SerializedName("m_Item2")
            private String mItem2;

            public String getmItem3() {
                return mItem3;
            }

            public void setmItem3(String mItem3) {
                this.mItem3 = mItem3;
            }

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
        }
    }
}
