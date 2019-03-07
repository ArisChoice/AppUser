package com.app.barber.models.request;

/**
 * Created by harish on 1/11/18.
 */

public class UpdateAddressRequestModel {
    public String getAddressLine1() {
        return AddressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        AddressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return AddressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        AddressLine2 = addressLine2;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String zip) {
        Zip = zip;
    }

    String AddressLine1;
    String AddressLine2;
    String City;
    String Zip;
    double Lat;
    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    String Country;
    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public double getLong() {
        return Long;
    }

    public void setLong(double aLong) {
        Long = aLong;
    }

    double Long;
}
