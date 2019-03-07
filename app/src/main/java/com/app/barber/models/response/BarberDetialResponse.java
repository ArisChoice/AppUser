package com.app.barber.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by harish on 26/11/18.
 */

public class BarberDetialResponse {

    /**
     * Message : Barber Info
     * Status : 201
     * Info : {"UserID":1021,"FirstName":"Test","LastName":"Barber","Email":"Testbarber@gmail.com","UserType":1,"IsDeleted":false,"UserName":"Test Barber","PhoneNumber":"9466049703","Invitationcode":null,"BarberType":"1","Specializaions":"1,2","PaymentType":null,"SessionId":null,"ProfileImage":"/Content/images/team-2.jpg","Description":null,"CallOutHours":[{"Id":36,"Day":"Monday","OpeningHours":"01:25 AM","ClosingHours":"02:45 AM"},{"Id":37,"Day":"Tuesday","OpeningHours":null,"ClosingHours":null},{"Id":38,"Day":"Wednesday","OpeningHours":"01:50 AM","ClosingHours":"08:30 AM"},{"Id":39,"Day":"Thursday","OpeningHours":null,"ClosingHours":null},{"Id":40,"Day":"Friday","OpeningHours":null,"ClosingHours":null},{"Id":41,"Day":"Saturday","OpeningHours":null,"ClosingHours":null},{"Id":42,"Day":"Sunday","OpeningHours":null,"ClosingHours":null},{"Id":43,"Day":"Monday","OpeningHours":"01:25 AM","ClosingHours":"02:45 AM"},{"Id":44,"Day":"Tuesday","OpeningHours":"01:30 AM","ClosingHours":"03:35 AM"},{"Id":45,"Day":"Wednesday","OpeningHours":"01:50 AM","ClosingHours":"08:30 AM"},{"Id":46,"Day":"Thursday","OpeningHours":null,"ClosingHours":null},{"Id":47,"Day":"Friday","OpeningHours":null,"ClosingHours":null},{"Id":48,"Day":"Saturday","OpeningHours":null,"ClosingHours":null},{"Id":49,"Day":"Sunday","OpeningHours":null,"ClosingHours":null}],"OpeningHours":[{"Id":17,"Day":"Monday","OpeningHours":"01:00 AM","ClosingHours":"05:05 AM"},{"Id":18,"Day":"Tuesday","OpeningHours":"02:35 AM","ClosingHours":"03:00 AM"},{"Id":19,"Day":"Wednesday","OpeningHours":null,"ClosingHours":null},{"Id":20,"Day":"Thursday","OpeningHours":null,"ClosingHours":null},{"Id":21,"Day":"Friday","OpeningHours":"03:10 AM","ClosingHours":"02:45 PM"},{"Id":22,"Day":"Saturday","OpeningHours":null,"ClosingHours":null},{"Id":23,"Day":"Sunday","OpeningHours":null,"ClosingHours":null}],"BreakHours":[],"Services":[{"Id":3,"ServiceName":"yydfuu","Duration":68,"Price":45,"PriceType":"cgch"},{"Id":4,"ServiceName":"jivx","Duration":14,"Price":25,"PriceType":"knn"},{"Id":21,"ServiceName":"Trimming ","Duration":15,"Price":30,"PriceType":"Fixed"}],"UserAddresses":{"Id":5,"AddressLine1":"rururu","AddressLine2":"fufufufugi","City":"chd","Zip":"160087"}}
     */

    @SerializedName("Message")
    private String Message;
    @SerializedName("Status")
    private int Status;
    @SerializedName("Info")
    private InfoBean Info;

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

    public InfoBean getInfo() {
        return Info;
    }

    public void setInfo(InfoBean Info) {
        this.Info = Info;
    }

    public static class InfoBean implements Serializable {
        /**
         * UserID : 1021
         * FirstName : Test
         * LastName : Barber
         * Email : Testbarber@gmail.com
         * UserType : 1
         * IsDeleted : false
         * UserName : Test Barber
         * PhoneNumber : 9466049703
         * Invitationcode : null
         * BarberType : 1
         * Specializaions : 1,2
         * PaymentType : null
         * SessionId : null
         * ProfileImage : /Content/images/team-2.jpg
         * Description : null
         * CallOutHours : [{"Id":36,"Day":"Monday","OpeningHours":"01:25 AM","ClosingHours":"02:45 AM"},{"Id":37,"Day":"Tuesday","OpeningHours":null,"ClosingHours":null},{"Id":38,"Day":"Wednesday","OpeningHours":"01:50 AM","ClosingHours":"08:30 AM"},{"Id":39,"Day":"Thursday","OpeningHours":null,"ClosingHours":null},{"Id":40,"Day":"Friday","OpeningHours":null,"ClosingHours":null},{"Id":41,"Day":"Saturday","OpeningHours":null,"ClosingHours":null},{"Id":42,"Day":"Sunday","OpeningHours":null,"ClosingHours":null},{"Id":43,"Day":"Monday","OpeningHours":"01:25 AM","ClosingHours":"02:45 AM"},{"Id":44,"Day":"Tuesday","OpeningHours":"01:30 AM","ClosingHours":"03:35 AM"},{"Id":45,"Day":"Wednesday","OpeningHours":"01:50 AM","ClosingHours":"08:30 AM"},{"Id":46,"Day":"Thursday","OpeningHours":null,"ClosingHours":null},{"Id":47,"Day":"Friday","OpeningHours":null,"ClosingHours":null},{"Id":48,"Day":"Saturday","OpeningHours":null,"ClosingHours":null},{"Id":49,"Day":"Sunday","OpeningHours":null,"ClosingHours":null}]
         * OpeningHours : [{"Id":17,"Day":"Monday","OpeningHours":"01:00 AM","ClosingHours":"05:05 AM"},{"Id":18,"Day":"Tuesday","OpeningHours":"02:35 AM","ClosingHours":"03:00 AM"},{"Id":19,"Day":"Wednesday","OpeningHours":null,"ClosingHours":null},{"Id":20,"Day":"Thursday","OpeningHours":null,"ClosingHours":null},{"Id":21,"Day":"Friday","OpeningHours":"03:10 AM","ClosingHours":"02:45 PM"},{"Id":22,"Day":"Saturday","OpeningHours":null,"ClosingHours":null},{"Id":23,"Day":"Sunday","OpeningHours":null,"ClosingHours":null}]
         * BreakHours : []
         * Services : [{"Id":3,"ServiceName":"yydfuu","Duration":68,"Price":45,"PriceType":"cgch"},{"Id":4,"ServiceName":"jivx","Duration":14,"Price":25,"PriceType":"knn"},{"Id":21,"ServiceName":"Trimming ","Duration":15,"Price":30,"PriceType":"Fixed"}]
         * UserAddresses : {"Id":5,"AddressLine1":"rururu","AddressLine2":"fufufufugi","City":"chd","Zip":"160087"}
         */

        @SerializedName("UserID")
        private int UserID;
        @SerializedName("FirstName")
        private String FirstName;
        @SerializedName("LastName")
        private String LastName;
        @SerializedName("Email")
        private String Email;
        @SerializedName("UserType")
        private int UserType;
        @SerializedName("IsDeleted")
        private boolean IsDeleted;
        @SerializedName("UserName")
        private String UserName;
        @SerializedName("PhoneNumber")
        private String PhoneNumber;
        @SerializedName("Invitationcode")
        private Object Invitationcode;
        @SerializedName("BarberType")
        private String BarberType;
        @SerializedName("Specializaions")
        private String Specializaions;
        @SerializedName("PaymentType")
        private Object PaymentType;
        @SerializedName("SessionId")
        private Object SessionId;
        @SerializedName("ProfileImage")
        private String ProfileImage;

        public boolean isSuperBarber() {
            return IsSuperBarber;
        }

        public void setSuperBarber(boolean superBarber) {
            IsSuperBarber = superBarber;
        }

        @SerializedName("IsSuperBarber")
        private boolean IsSuperBarber;

        public boolean isDistrictAdded() {
            return IsDistrictAdded;
        }

        public void setDistrictAdded(boolean districtAdded) {
            IsDistrictAdded = districtAdded;
        }

        @SerializedName("IsDistrictAdded")
        private boolean IsDistrictAdded;

        public int getPortfolioImageCount() {
            return PortfolioImageCount;
        }

        public void setPortfolioImageCount(int portfolioImageCount) {
            PortfolioImageCount = portfolioImageCount;
        }

        @SerializedName("PortfolioImageCount")
        private int PortfolioImageCount;

        public String getQBDialogId() {
            return QBDialogId;
        }

        public void setQBDialogId(String QBDialogId) {
            this.QBDialogId = QBDialogId;
        }

        @SerializedName("QBDialogId")
        private String QBDialogId;

        public String getQBId() {
            return QBId;
        }

        public void setQBId(String QBId) {
            this.QBId = QBId;
        }

        @SerializedName("QBId")
        private String QBId;
        public String getChatDialog() {
            return ChatDialog;
        }

        public void setChatDialog(String chatDialog) {
            ChatDialog = chatDialog;
        }

        @SerializedName("ChatDialog")
        private String ChatDialog;
        public boolean isFavourite() {
            return IsFavourite;
        }

        public void setFavourite(boolean favourite) {
            IsFavourite = favourite;
        }

        @SerializedName("IsFavourite")
        private boolean IsFavourite;

        public String getFullName() {
            return FullName;
        }

        public void setFullName(String fullName) {
            FullName = fullName;
        }

        @SerializedName("FullName")
        private String FullName;

        public String getBannerImage() {
            return BannerImage;
        }

        public void setBannerImage(String bannerImage) {
            BannerImage = bannerImage;
        }

        @SerializedName("BannerImage")
        private String BannerImage;
        @SerializedName("Description")
        private String Description;
        @SerializedName("UserAddresses")
        private UserAddressesBean UserAddresses;
        @SerializedName("CallOutHours")
        private List<CallOutHoursBean> CallOutHours;
        @SerializedName("OpeningHours")
        private List<OpeningHoursBean> OpeningHours;
        @SerializedName("BreakHours")
        private List<?> BreakHours;
        @SerializedName("Services")
        private List<ServiceListResponseModel.ResponseBean> Services;
        @SerializedName("BarberTypes")
        @Expose
        private List<BarberType> barberTypes = null;

        public List<BarberType> getBarberTypes() {
            return barberTypes;
        }

        public void setBarberTypes(List<BarberType> barberTypes) {
            this.barberTypes = barberTypes;
        }

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

        public String getSpecializaions() {
            return Specializaions;
        }

        public void setSpecializaions(String Specializaions) {
            this.Specializaions = Specializaions;
        }

        public Object getPaymentType() {
            return PaymentType;
        }

        public void setPaymentType(Object PaymentType) {
            this.PaymentType = PaymentType;
        }

        public Object getSessionId() {
            return SessionId;
        }

        public void setSessionId(Object SessionId) {
            this.SessionId = SessionId;
        }

        public String getProfileImage() {
            return ProfileImage;
        }

        public void setProfileImage(String ProfileImage) {
            this.ProfileImage = ProfileImage;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public UserAddressesBean getUserAddresses() {
            return UserAddresses;
        }

        public void setUserAddresses(UserAddressesBean UserAddresses) {
            this.UserAddresses = UserAddresses;
        }

        public List<CallOutHoursBean> getCallOutHours() {
            return CallOutHours;
        }

        public void setCallOutHours(List<CallOutHoursBean> CallOutHours) {
            this.CallOutHours = CallOutHours;
        }

        public List<OpeningHoursBean> getOpeningHours() {
            return OpeningHours;
        }

        public void setOpeningHours(List<OpeningHoursBean> OpeningHours) {
            this.OpeningHours = OpeningHours;
        }

        public List<?> getBreakHours() {
            return BreakHours;
        }

        public void setBreakHours(List<?> BreakHours) {
            this.BreakHours = BreakHours;
        }

        public List<ServiceListResponseModel.ResponseBean> getServices() {
            return Services;
        }

        public void setServices(List<ServiceListResponseModel.ResponseBean> Services) {
            this.Services = Services;
        }

        public class BarberType implements Serializable {

            @SerializedName("m_Item1")
            @Expose
            private String mItem1;
            @SerializedName("m_Item2")
            @Expose
            private String mItem2;

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

        public static class UserAddressesBean implements Serializable {
            /**
             * Id : 5
             * AddressLine1 : rururu
             * AddressLine2 : fufufufugi
             * City : chd
             * Zip : 160087
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

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String get_long() {
                return _long;
            }

            public void set_long(String _long) {
                this._long = _long;
            }

            @SerializedName("Lat")
            @Expose
            private String lat;
            @SerializedName("Long")
            @Expose
            private String _long;

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
        }

        public static class CallOutHoursBean implements Serializable {
            /**
             * Id : 36
             * Day : Monday
             * OpeningHours : 01:25 AM
             * ClosingHours : 02:45 AM
             */

            @SerializedName("Id")
            private int Id;
            @SerializedName("Day")
            private String Day;
            @SerializedName("OpeningHours")
            private String OpeningHours;
            @SerializedName("ClosingHours")
            private String ClosingHours;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getDay() {
                return Day;
            }

            public void setDay(String Day) {
                this.Day = Day;
            }

            public String getOpeningHours() {
                return OpeningHours;
            }

            public void setOpeningHours(String OpeningHours) {
                this.OpeningHours = OpeningHours;
            }

            public String getClosingHours() {
                return ClosingHours;
            }

            public void setClosingHours(String ClosingHours) {
                this.ClosingHours = ClosingHours;
            }
        }

        public static class OpeningHoursBean implements Serializable {
            /**
             * Id : 17
             * Day : Monday
             * OpeningHours : 01:00 AM
             * ClosingHours : 05:05 AM
             */

            @SerializedName("Id")
            private int Id;
            @SerializedName("Day")
            private String Day;
            @SerializedName("OpeningHours")
            private String OpeningHours;
            @SerializedName("ClosingHours")
            private String ClosingHours;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getDay() {
                return Day;
            }

            public void setDay(String Day) {
                this.Day = Day;
            }

            public String getOpeningHours() {
                return OpeningHours;
            }

            public void setOpeningHours(String OpeningHours) {
                this.OpeningHours = OpeningHours;
            }

            public String getClosingHours() {
                return ClosingHours;
            }

            public void setClosingHours(String ClosingHours) {
                this.ClosingHours = ClosingHours;
            }
        }

      /*  public static class ServicesBean {
            *//**
         * Id : 3
         * ServiceName : yydfuu
         * Duration : 68
         * Price : 45
         * PriceType : cgch
         *//*

            @SerializedName("Id")
            private int Id;
            @SerializedName("ServiceName")
            private String ServiceName;
            @SerializedName("Duration")
            private int Duration;
            @SerializedName("Price")
            private int Price;
            @SerializedName("PriceType")
            private String PriceType;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getServiceName() {
                return ServiceName;
            }

            public void setServiceName(String ServiceName) {
                this.ServiceName = ServiceName;
            }

            public int getDuration() {
                return Duration;
            }

            public void setDuration(int Duration) {
                this.Duration = Duration;
            }

            public int getPrice() {
                return Price;
            }

            public void setPrice(int Price) {
                this.Price = Price;
            }

            public String getPriceType() {
                return PriceType;
            }

            public void setPriceType(String PriceType) {
                this.PriceType = PriceType;
            }
        }*/
    }
}
