package com.app.barber.models.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by harish on 29/1/19.
 */

public class AddressListResponseModel {

    /**
     * Message : Success
     * Status : 201
     * List : [{"Id":27,"AddressLine1":"Daria  Chandigarh","AddressLine2":" Chandigarh","City":" Chandigarh","Zip":" 160102","Lat":"30.702393299999994","Long":"76.8214717","Country":null,"IsDefault":null}]
     */

    @SerializedName("Message")
    private String Message;
    @SerializedName("Status")
    private int Status;
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

    public static class ListBean implements Serializable {
        /**
         * Id : 27
         * AddressLine1 : Daria  Chandigarh
         * AddressLine2 :  Chandigarh
         * City :  Chandigarh
         * Zip :  160102
         * Lat : 30.702393299999994
         * Long : 76.8214717
         * Country : null
         * IsDefault : null
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
        @SerializedName("Country")
        private String Country;
        @SerializedName("IsDefault")
        private boolean IsDefault;

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

        public String getCountry() {
            return Country;
        }

        public void setCountry(String Country) {
            this.Country = Country;
        }

        public boolean getIsDefault() {
            return IsDefault;
        }

        public void setIsDefault(boolean IsDefault) {
            this.IsDefault = IsDefault;
        }
    }
}
