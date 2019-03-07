package com.app.barber.ui.postauth.activities.barber;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseActivity;
import com.app.barber.ui.postauth.activities.basic.AddressActivity;
import com.app.barber.ui.postauth.activities.basic.basicmvp.BasicAuthMVPView;
import com.app.barber.ui.postauth.activities.basic.basicmvp.BssicAuthPresenter;
import com.app.barber.ui.postauth.activities.basic.SearchAddressActivity;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.iface.OnCallBackResult;
import com.app.barber.util.thread.GetLocationFromLatLong;
import com.app.barber.views.CustomTextView;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import io.fabric.sdk.android.services.concurrency.AsyncTask;

/**
 * Created by harish on 23/10/18.
 */

public class AddressSelectionActivity extends BaseActivity implements BasicAuthMVPView, LocationListener {

    @BindView(R.id.btn_current_loctaion)
    CustomTextView btnCurrentLoctaion;
    @BindView(R.id.btn_postcode_loctaion)
    CustomTextView btnPostcodeLoctaion;
    @BindView(R.id.btn_enter_manually)
    CustomTextView btnEnterManually;
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    private BssicAuthPresenter presenter;
    private LocationManager locationManager;
    private String provider;
    private Location currentLocation;

    @Override
    public int getLayoutId() {
        return R.layout.layout_address_selectio_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BarberApplication) getApplication()).getMyComponent(this).inject(this);
        presenter = new BssicAuthPresenter(this);
        txtTitleToolbar.setText(R.string.str_change_location);
        presenter.attachView(this);
        getBundleData(getIntent());

    }

    private boolean checkAndRequestPermissions() {
        int location1 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int location2 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (location1 != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (location2 != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), GlobalValues.RequestCodes.PERMISSIONS_REQUEST_);
            return false;
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case GlobalValues.RequestCodes.PERMISSIONS_REQUEST_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    getCurrentLocation();
                    //Permission Granted Successfully. Write working code here.
                } else {
                    //You did not accept the request can not use the functionality.
                    CommonUtils.getInstance(this).ShowToast(getString(R.string.str_permission_denied));
                }
                break;
        }
    }

    private void getCurrentLocation() {
        // Get the location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);

        // Initialize the location fields
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    private void getBundleData(Intent intent) {
        if (intent.getBooleanExtra(GlobalValues.KEYS.isAddressFromSetting, false)) {
            txtTitleToolbar.setText(R.string.str_add_address);
        } else {
            txtTitleToolbar.setText(R.string.str_change_location);
        }
    }

    /* Request updates at startup */
    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT < 23) {
            //Do not need to check the permission
            getCurrentLocation();
        } else {
            if (checkAndRequestPermissions()) {
                //If you have already permitted the permission
                getCurrentLocation();
            }
        }
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
        try {
            locationManager.removeUpdates(this);
        } catch (Exception e) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void onSuccessResponse(int serviceMode, Object o) {

    }

    @Override
    public void onfaliurResponse(int serviceMode, Object o) {

    }

    @Override
    public void showProgres() {

    }

    @Override
    public void hidePreogress() {

    }

    @Override
    public void onSuccess(Object o, int type) {

    }

    @Override
    public void onError(String localizedMessage) {

    }

    @Override
    public void onException(Exception e) {

    }

    @OnClick({R.id.btn_current_loctaion, R.id.btn_postcode_loctaion, R.id.btn_enter_manually, R.id.back_toolbar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.btn_current_loctaion:
                selectAddress();
//                activitySwitcherResult(AddressSelectionActivity.this, SearchAddressActivity.class, null, GlobalValues.RequestCodes.ADDRESS_SEARCH);
                break;
            case R.id.btn_postcode_loctaion:
                activitySwitcherResult(AddressSelectionActivity.this, SearchAddressActivity.class, null, GlobalValues.RequestCodes.ADDRESS_SEARCH);
                finish();
                break;
            case R.id.btn_enter_manually:
                activitySwitcherResult(AddressSelectionActivity.this, AddressActivity.class, null, GlobalValues.RequestCodes.ADDRESS_SEARCH);
                finish();
                break;
        }
    }

    private void selectAddress() {
        try {
            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
            try {
                LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                LatLngBounds latLngBounds = new LatLngBounds(latLng, latLng);
                builder.setLatLngBounds(latLngBounds);
            } catch (Exception e) {

            }
            startActivityForResult(builder.build(this), GlobalValues.RequestCodes.LOCATION_PICKER);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onLocationChanged(Location location) {

        if (location != null) {
            currentLocation = location;
//            new GetLocationFromLatLong(AddressSelectionActivity.this, location.getLatitude(), location.getLongitude()).execute();
            Log.e("onLocationChanged", " get " + new Gson().toJson(mPref.getLocation()));
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GlobalValues.RequestCodes.LOCATION_PICKER)
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                double latitude = place.getLatLng().latitude;
                double longitude = place.getLatLng().longitude;
                String fullAddress = place.getAddress().toString();

                new com.app.barber.util.thread.GetLocationFromLatLong(AddressSelectionActivity.this, latitude, longitude,
                        new OnCallBackResult() {
                            @Override
                            public void onResult(Object oj) {
                                Log.e(" onResult ", " " + new Gson().toJson((String) oj));
                                Bundle bundle = new Bundle();
                                bundle.putString(GlobalValues.KEYS.PLACE_DETAIL, (String) oj);
                                bundle.putDouble(GlobalValues.KEYS.LATITUDE, latitude);
                                bundle.putDouble(GlobalValues.KEYS.LONGITUDE, longitude);
//                                bundle.putBoolean(GlobalValues.KEYS.CURRENT_ADDRESS, true);
                                activitySwitcher(AddressSelectionActivity.this, AddressActivity.class, bundle);
                                finish();
                            }

                            @Override
                            public void onError(Object oj) {
                                Log.e(" onError ", " " + new Gson().toJson((String) oj));
                            }
                        }).execute();
                /*Bundle bundle = new Bundle();
                bundle.putString(GlobalValues.KEYS.PLACE_DETAIL, place.getAddress().toString());
                bundle.putDouble(GlobalValues.KEYS.LATITUDE, latitude);
                bundle.putDouble(GlobalValues.KEYS.LONGITUDE, longitude);
                activitySwitcher(AddressSelectionActivity.this, AddressActivity.class, bundle);
                finish();*/
            }
    }

//    private class GetLocationFromLatLong extends AsyncTask {
//        AddressSelectionActivity addressSelectionActivity;
//        double latitude;
//        double longitude;
//
//        public GetLocationFromLatLong(AddressSelectionActivity addressSelectionActivity, double latitude, double longitude) {
//            this.addressSelectionActivity = addressSelectionActivity;
//            this.latitude = latitude;
//            this.longitude = longitude;
//        }
//
//        @Override
//        protected Object doInBackground(Object[] objects) {
//            StringBuilder result = new StringBuilder();
//            try {
//                Geocoder geocoder = new Geocoder(addressSelectionActivity, Locale.getDefault());
//                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
//                if (addresses.size() > 0) {
//                    Address address = addresses.get(0);
//                    result.append(address.getLocality()).append("\n");
//                    result.append(address.getCountryName());
//                }
//            } catch (IOException e) {
//                Log.e("tag", e.getMessage());
//            }
//            mPref.setPrefrencesString(GlobalValues.KEYS.LATITUDE, "" + latitude);
//            mPref.setPrefrencesString(GlobalValues.KEYS.LONGITUDE, "" + longitude);
//            mPref.setPrefrencesString(GlobalValues.KEYS.ADDRESS, "" + result.toString());
//            Log.d(" GetLocationFromLatLong ", result.toString());
//            return result.toString();
//        }
//    }
}
