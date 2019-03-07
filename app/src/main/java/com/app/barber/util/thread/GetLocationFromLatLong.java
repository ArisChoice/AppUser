package com.app.barber.util.thread;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.util.Log;

import com.app.barber.ui.postauth.activities.HomeActivity;
import com.app.barber.ui.postauth.activities.barber.AddressSelectionActivity;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.PreferenceManager;
import com.app.barber.util.iface.OnCallBackResult;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GetLocationFromLatLong extends AsyncTask<Integer, Void, String> {


    Activity addressSelectionActivity;
    double latitude;
    double longitude;
    OnCallBackResult o;

    public GetLocationFromLatLong(Activity addressSelectionActivity, double latitude, double longitude, OnCallBackResult o) {
        this.addressSelectionActivity = addressSelectionActivity;
        this.latitude = latitude;
        this.longitude = longitude;
        this.o = o;
    }

    @Override

    protected String doInBackground(Integer... params) {

        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(addressSelectionActivity, Locale.UK);
            Log.e(" doInBackground  -------> ", "  " + latitude + "  " + longitude);
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
//                properFormatSample();
                if (o != null) {
                    Log.d(" doInBackground  ", new Gson().toJson(address));
//                    result.append(address.getAddressLine(0).replace(",", "@"));
//                  result.append(address.getLocality()).append("\n");
                    if (address.getSubThoroughfare() != null && !address.getSubThoroughfare().equals("") && address.getThoroughfare() != null && !address.getThoroughfare().equals(""))
                        result.append(address.getSubThoroughfare() + "@" + address.getThoroughfare());
                    else if (address.getThoroughfare() != null && !address.getThoroughfare().equals(""))
                        result.append(address.getThoroughfare());
                    else if (address.getSubThoroughfare() != null && !address.getSubThoroughfare().equals(""))
                        result.append(address.getSubThoroughfare());
                    else if (address.getFeatureName() != null && !address.getFeatureName().equals(""))
                        result.append(address.getFeatureName());

                    result.append("," + address.getLocality() + ",");
                    result.append(address.getPostalCode() + ",");
                    result.append(address.getCountryName());
                    o.onResult(result.toString());
                } else {
                    result.append(address.getLocality()).append("\n");
                    result.append(address.getCountryName());
                    new PreferenceManager().setPrefrencesString(GlobalValues.KEYS.LATITUDE, "" + latitude);
                    new PreferenceManager().setPrefrencesString(GlobalValues.KEYS.LONGITUDE, "" + longitude);
                    new PreferenceManager().setPrefrencesString(GlobalValues.KEYS.ADDRESS, "" + address.getAddressLine(0));
                }
            }
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
            if (o != null)
                o.onError(result.toString());
        }

        Log.d("doInBackground -", result.toString());
        return result.toString();

    }


    private void properFormatSample() {
        try {
            Geocoder geocoder = new Geocoder(addressSelectionActivity, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

            //get current Street name
            String address = addresses.get(0).getAddressLine(0);

            //get current province/City
            String province = addresses.get(0).getAdminArea();

            //get country
            String country = addresses.get(0).getCountryName();

            //get postal code
            String postalCode = addresses.get(0).getPostalCode();

            //get place Name
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
            Log.e(" properFormatSample  ", "-----------------  " + "Street: " + address + "\n" + "City/Province: " + province + "\nCountry: " + country
                    + "\nPostal CODE: " + postalCode + "\n" + "Place Name: " + knownName);


        } catch (IOException ex) {
            ex.printStackTrace();


        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();

        }

    }

    @Override

    protected void onPostExecute(String result) {

    }

}