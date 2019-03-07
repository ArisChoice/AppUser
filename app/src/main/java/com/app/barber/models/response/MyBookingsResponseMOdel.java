package com.app.barber.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by harish on 26/12/18.
 */

public class MyBookingsResponseMOdel implements Serializable {

    /**
     * Message : Success
     * Status : 201
     * List : [{"ProfileImage":"/Uploads/ProfileImages/99729c35-834c-46f7-8d0d-b63e0d9bd4e2.jpg","Name":"Harry Barber","Id":31,"ServiceId":"Hair Cutting","BarberId":2061,"Date":"2018-12-26T00:00:00","DateString":"12/26/2018","TimingSlot":"11:25 AM-11:50 AM","Amount":50,"Note":null,"BookingType":1},{"ProfileImage":"/Content/images/team-2.jpg","Name":"Demo Barber","Id":30,"ServiceId":"Shave ","BarberId":1054,"Date":"2018-12-25T00:00:00","DateString":"12/25/2018","TimingSlot":"09:10 AM-09:30 AM","Amount":20,"Note":null,"BookingType":1},{"ProfileImage":"/Uploads/ProfileImages/99729c35-834c-46f7-8d0d-b63e0d9bd4e2.jpg","Name":"Harry Barber","Id":23,"ServiceId":"Hair Dryer ","BarberId":2061,"Date":"2018-12-25T00:00:00","DateString":"12/25/2018","TimingSlot":"04:00 PM-04:40 PM","Amount":30,"Note":null,"BookingType":1},{"ProfileImage":"/Uploads/ProfileImages/99729c35-834c-46f7-8d0d-b63e0d9bd4e2.jpg","Name":"Harry Barber","Id":20,"ServiceId":"Trimming ","BarberId":2061,"Date":"2018-12-24T00:00:00","DateString":"12/24/2018","TimingSlot":"08:40 AM-09:00 AM","Amount":20,"Note":null,"BookingType":1},{"ProfileImage":"/Uploads/ProfileImages/99729c35-834c-46f7-8d0d-b63e0d9bd4e2.jpg","Name":"Harry Barber","Id":21,"ServiceId":"Hair Colouring ","BarberId":2061,"Date":"2018-12-24T00:00:00","DateString":"12/24/2018","TimingSlot":"09:40 AM-10:05 AM","Amount":45,"Note":null,"BookingType":1},{"ProfileImage":"/Uploads/ProfileImages/99729c35-834c-46f7-8d0d-b63e0d9bd4e2.jpg","Name":"Harry Barber","Id":22,"ServiceId":"Trimming ","BarberId":2061,"Date":"2018-12-24T00:00:00","DateString":"12/24/2018","TimingSlot":"10:40 AM-11:00 AM","Amount":20,"Note":null,"BookingType":1},{"ProfileImage":"/Uploads/ProfileImages/99729c35-834c-46f7-8d0d-b63e0d9bd4e2.jpg","Name":"Harry Barber","Id":1,"ServiceId":"Hair Cutting","BarberId":2061,"Date":"2018-12-24T00:00:00","DateString":"12/24/2018","TimingSlot":"01:03 PM-01:28 PM","Amount":50,"Note":null,"BookingType":2},{"ProfileImage":"/Uploads/ProfileImages/99729c35-834c-46f7-8d0d-b63e0d9bd4e2.jpg","Name":"Harry Barber","Id":24,"ServiceId":"Hair Cutting","BarberId":2061,"Date":"2018-12-24T00:00:00","DateString":"12/24/2018","TimingSlot":"01:31 PM-01:56 PM","Amount":50,"Note":null,"BookingType":1},{"ProfileImage":"/Uploads/ProfileImages/99729c35-834c-46f7-8d0d-b63e0d9bd4e2.jpg","Name":"Harry Barber","Id":2,"ServiceId":"Trimming ","BarberId":2061,"Date":"2018-12-24T00:00:00","DateString":"12/24/2018","TimingSlot":"01:48 PM-02:08 PM","Amount":20,"Note":null,"BookingType":2},{"ProfileImage":"/Content/images/team-2.jpg","Name":"Testing  Man","Id":6,"ServiceId":"Hair cut","BarberId":1057,"Date":"2018-12-24T00:00:00","DateString":"12/24/2018","TimingSlot":"04:32 PM-04:52 PM","Amount":25,"Note":null,"BookingType":1},{"ProfileImage":"/Content/images/team-2.jpg","Name":"Demo Barber","Id":25,"ServiceId":"Hair color","BarberId":1054,"Date":"2018-12-24T00:00:00","DateString":"12/24/2018","TimingSlot":"05:59 PM-06:29 PM","Amount":50,"Note":null,"BookingType":1},{"ProfileImage":"/Uploads/ProfileImages/99729c35-834c-46f7-8d0d-b63e0d9bd4e2.jpg","Name":"Harry Barber","Id":17,"ServiceId":"Trimming ","BarberId":2061,"Date":"2018-12-22T00:00:00","DateString":"12/22/2018","TimingSlot":"04:27 PM-04:47 PM","Amount":20,"Note":null,"BookingType":1},{"ProfileImage":"/Uploads/ProfileImages/99729c35-834c-46f7-8d0d-b63e0d9bd4e2.jpg","Name":"Harry Barber","Id":16,"ServiceId":"Hair Cutting","BarberId":2061,"Date":"2018-12-21T00:00:00","DateString":"12/21/2018","TimingSlot":"02:46 PM-03:11 PM","Amount":50,"Note":null,"BookingType":1},{"ProfileImage":"/Uploads/ProfileImages/99729c35-834c-46f7-8d0d-b63e0d9bd4e2.jpg","Name":"Harry Barber","Id":18,"ServiceId":"Hair Cutting,Shave","BarberId":2061,"Date":"2018-12-21T00:00:00","DateString":"12/21/2018","TimingSlot":"04:45 PM-05:25 PM","Amount":70,"Note":null,"BookingType":1},{"ProfileImage":"/Uploads/ProfileImages/99729c35-834c-46f7-8d0d-b63e0d9bd4e2.jpg","Name":"Harry Barber","Id":19,"S 12-26 11:45:23.332 12003-13446/com.app.trimcheck.customer D/OkHttp: erviceId":"Shave","BarberId":2061,"Date":"2018-12-21T00:00:00","DateString":"12/21/2018","TimingSlot":"05:50 PM-06:05 PM","Amount":20,"Note":null,"BookingType":1},{"ProfileImage":"/Content/images/team-2.jpg","Name":"Test Nai","Id":7,"ServiceId":"Hair Colouring ","BarberId":1060,"Date":"2018-12-20T00:00:00","DateString":"12/20/2018","TimingSlot":"05:12 PM-05:42 PM","Amount":50,"Note":null,"BookingType":1},{"ProfileImage":"/Content/images/team-2.jpg","Name":"Demo Barber","Id":3,"ServiceId":"Hair color","BarberId":1054,"Date":"2018-12-18T00:00:00","DateString":"12/18/2018","TimingSlot":"09:00 AM-09:50 AM","Amount":50,"Note":null,"BookingType":1},{"ProfileImage":"/Content/images/team-2.jpg","Name":"Test Nai","Id":5,"ServiceId":"Haircut,Hair Colouring ","BarberId":1060,"Date":"2018-12-18T00:00:00","DateString":"12/18/2018","TimingSlot":"05:09 PM-06:09 PM","Amount":105,"Note":null,"BookingType":1},{"ProfileImage":"/Content/images/team-2.jpg","Name":"Demo Barber","Id":1,"ServiceId":"Hair color","BarberId":1054,"Date":"2018-12-17T00:00:00","DateString":"12/17/2018","TimingSlot":"08:00 AM-08:50 AM","Amount":50,"Note":null,"BookingType":1},{"ProfileImage":"/Content/images/team-2.jpg","Name":"Test Nai","Id":2,"ServiceId":"","BarberId":1060,"Date":"2018-12-17T00:00:00","DateString":"12/17/2018","TimingSlot":"08:00 AM-08:30 AM","Amount":50,"Note":null,"BookingType":1}]
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

    public static class ListBean {
        /**
         * ProfileImage : /Uploads/ProfileImages/99729c35-834c-46f7-8d0d-b63e0d9bd4e2.jpg
         * Name : Harry Barber
         * Id : 31
         * ServiceId : Hair Cutting
         * BarberId : 2061
         * Date : 2018-12-26T00:00:00
         * DateString : 12/26/2018
         * TimingSlot : 11:25 AM-11:50 AM
         * Amount : 50
         * Note : null
         * BookingType : 1
         * S 12-26 11:45:23.332 12003-13446/com.app.trimcheck.customer D/OkHttp: erviceId : Shave
         */

        @SerializedName("ProfileImage")
        private String ProfileImage;
        @SerializedName("Name")
        private String Name;
        @SerializedName("Id")
        private int Id;
        @SerializedName("ServiceId")
        private String ServiceId;

        public String getServiceNames() {
            return ServiceNames;
        }

        public void setServiceNames(String serviceNames) {
            ServiceNames = serviceNames;
        }

        @SerializedName("ServiceNames")
        private String ServiceNames;


        @SerializedName("BarberId")
        private int BarberId;
        @SerializedName("Date")
        private String Date;
        @SerializedName("DateString")
        private String DateString;
        @SerializedName("TimingSlot")
        private String TimingSlot;
        @SerializedName("Amount")
        private int Amount;
        @SerializedName("Note")
        private Object Note;
        @SerializedName("BookingType")
        private int BookingType;

        public String getEditData() {
            return EditData;
        }

        public void setEditData(String editData) {
            EditData = editData;
        }

        @SerializedName("EditData")
        private String EditData;

        public String getDistance() {
            return Distance;
        }

        public void setDistance(String distance) {
            Distance = distance;
        }

        @SerializedName("Distance")
        private String Distance;
        public MyBookingsResponseMOdel.Address getAddress() {
            return Address;
        }

        public void setAddress(MyBookingsResponseMOdel.Address address) {
            Address = address;
        }

        @SerializedName("Address")
        private Address Address;

        public boolean isCanceled() {
            return IsCanceled;
        }

        public void setCanceled(boolean canceled) {
            IsCanceled = canceled;
        }

        public boolean isCompleted() {
            return IsCompleted;
        }

        public void setCompleted(boolean completed) {
            IsCompleted = completed;
        }

        @SerializedName("IsCanceled")
        private boolean IsCanceled;
        @SerializedName("IsCompleted")
        private boolean IsCompleted;

        public boolean isConfirmed() {
            return IsConfirmed;
        }

        public void setConfirmed(boolean confirmed) {
            IsConfirmed = confirmed;
        }

        @SerializedName("IsConfirmed")
        private boolean IsConfirmed;

        public int getCancellationCharge() {
            return CancellationCharge;
        }

        public void setCancellationCharge(int cancellationCharge) {
            CancellationCharge = cancellationCharge;
        }

        @SerializedName("CancellationCharge")
        private int CancellationCharge;

        public String getChatDialog() {
            return ChatDialog;
        }

        public void setChatDialog(String chatDialog) {
            ChatDialog = chatDialog;
        }

        @SerializedName("ChatDialog")
        private String ChatDialog;

        public String getDuration() {
            return Duration;
        }

        public void setDuration(String duration) {
            Duration = duration;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String phone) {
            Phone = phone;
        }

        @SerializedName("Duration")
        private String Duration;
        @SerializedName("Phone")
        private String Phone;

        public String getQBdialogId() {
            return QBdialogId;
        }

        public void setQBdialogId(String QBdialogId) {
            this.QBdialogId = QBdialogId;
        }

        @SerializedName("QBdialogId")
        private String QBdialogId;
        @SerializedName("S 12-26 11:45:23.332 12003-13446/com.app.trimcheck.customer D/OkHttp: erviceId")
        private String _$S12261145233321200313446ComAppTrimcheckCustomerDOkHttpErviceId300; // FIXME check this code

        public String getAppointmentStatus() {
            return appointmentStatus;
        }

        public void setAppointmentStatus(String appointmentStatus) {
            this.appointmentStatus = appointmentStatus;
        }

        private String appointmentStatus;

        public String getProfileImage() {
            return ProfileImage;
        }

        public void setProfileImage(String ProfileImage) {
            this.ProfileImage = ProfileImage;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getServiceId() {
            return ServiceId;
        }

        public void setServiceId(String ServiceId) {
            this.ServiceId = ServiceId;
        }

        public int getBarberId() {
            return BarberId;
        }

        public void setBarberId(int BarberId) {
            this.BarberId = BarberId;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String Date) {
            this.Date = Date;
        }

        public String getDateString() {
            return DateString;
        }

        public void setDateString(String DateString) {
            this.DateString = DateString;
        }

        public String getTimingSlot() {
            return TimingSlot;
        }

        public void setTimingSlot(String TimingSlot) {
            this.TimingSlot = TimingSlot;
        }

        public int getAmount() {
            return Amount;
        }

        public void setAmount(int Amount) {
            this.Amount = Amount;
        }

        public Object getNote() {
            return Note;
        }

        public void setNote(Object Note) {
            this.Note = Note;
        }

        public int getBookingType() {
            return BookingType;
        }

        public void setBookingType(int BookingType) {
            this.BookingType = BookingType;
        }

        public String get_$S12261145233321200313446ComAppTrimcheckCustomerDOkHttpErviceId300() {
            return _$S12261145233321200313446ComAppTrimcheckCustomerDOkHttpErviceId300;
        }

        public void set_$S12261145233321200313446ComAppTrimcheckCustomerDOkHttpErviceId300(String _$S12261145233321200313446ComAppTrimcheckCustomerDOkHttpErviceId300) {
            this._$S12261145233321200313446ComAppTrimcheckCustomerDOkHttpErviceId300 = _$S12261145233321200313446ComAppTrimcheckCustomerDOkHttpErviceId300;
        }
    }
}
