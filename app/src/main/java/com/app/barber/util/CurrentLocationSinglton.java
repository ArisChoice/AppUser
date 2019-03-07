package com.app.barber.util;

import android.app.Activity;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.gson.Gson;

/**
 * Created by Harish on 24/11/17.
 */

public class CurrentLocationSinglton {


    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 20000;
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    public static final int REQUEST_CHECK_SETTINGS = 200;
    static CurrentLocationSinglton mCurrentLocationSinglton;
    protected LocationRequest mLocationRequest;
    CurrentLocationCallback mCurrentLocationCallback;
    Activity mcontext;
    GoogleApiClient mGoogleApiClient;

    private LocationListener mLocationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {

            try {
                if (mGoogleApiClient.isConnected()) {

                    stopLocationUpdates();
                    if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
                        mGoogleApiClient.disconnect();
                        mGoogleApiClient = null;
                    }
                }
//                SharedStorage.setLocation(mcontext, new Gson().toJson(location));
                mCurrentLocationCallback.onSuccess(location);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    };


    private CurrentLocationSinglton() {


    }

    public static CurrentLocationSinglton getInstance() {

        if (mCurrentLocationSinglton == null) {
            mCurrentLocationSinglton = new CurrentLocationSinglton();
        }

        return mCurrentLocationSinglton;

    }


    public void getCurrentLocation(Activity context, CurrentLocationCallback mCurrentLocationCallback) {

        this.mcontext = context;
        this.mCurrentLocationCallback = mCurrentLocationCallback;


        if (!checkLocationPermission()) {
            return;
        }


        createLocationRequest();


        setUpGoogleClient();

    }


    protected void createLocationRequest() {

        // Log.e("createLocationRequest","@@@@@@@@@@@@@@@@@@@@@");

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    }

    public void startLocationUpdates() {
        // Log.e("startLocationUpdates","$$$$$$$$$$$$$$$$$$$$$$$$$");
        if (mGoogleApiClient == null) {
            // Log.e("startLocationUpdates","return true");
            return;
        }

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true);
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient,
                        builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {

                final Status status = result.getStatus();
                final LocationSettingsStates states = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        /**
                         * if we do not have permission to access location
                         */
                        // Log.e("setResultCallback.SUCCESS","%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                        if (!checkLocationPermission()) {
                            // Log.e("setResultCallback.SUCCESS","return true");
                            return;
                        }
                        try {
                            LocationServices.FusedLocationApi.requestLocationUpdates(
                                    mGoogleApiClient,
                                    mLocationRequest,
                                    mLocationListener
                            );
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Log.e("setResultCallback","RESOLUTION_REQUIRED");
                        try {
                            status.startResolutionForResult(mcontext, REQUEST_CHECK_SETTINGS);

                        } catch (IntentSender.SendIntentException e) {
                            e.printStackTrace();
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Log.e("setResultCallback","SETTINGS_CHANGE_UNAVAILABLE");
                        try {
                            status.startResolutionForResult(mcontext, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            e.printStackTrace();
                        }
                        break;
                }

            }
        });
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient,
                mLocationListener
        ).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(Status status) {
            }
        });
    }

    private void setUpGoogleClient() {
        // Log.e("setUpGoogleClient","setUpGoogleClient");
        mGoogleApiClient = new GoogleApiClient.Builder(mcontext, new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(Bundle bundle) {
                // Log.e("setUpGoogleClient","onConnected");
                startLocationUpdates();
            }

            @Override
            public void onConnectionSuspended(int i) {
                // Log.e("setUpGoogleClient","onConnectionSuspended");
                if (mGoogleApiClient != null) {
                    mGoogleApiClient.disconnect();
                }

            }
        }, new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(ConnectionResult connectionResult) {
                mCurrentLocationCallback.onFailure();
                if (mGoogleApiClient != null) {
                    mGoogleApiClient.disconnect();
                }
            }
        }).addApi(LocationServices.API).build();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.reconnect();
        }
    }

    /**
     * '
     * Runtime permission for location services
     *
     * @return true if permission granted
     */
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(mcontext,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(mcontext,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(mcontext,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    GlobalValues.RequestCodes.REQUEST_PERMISSION_FOR_LOCATION);
            return false;
        }
        return true;
    }

    public interface CurrentLocationCallback {

        void onSuccess(Location location);

        void onFailure();

    }
}