package com.app.barber.ui.preauth.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseActivity;
import com.app.barber.net.firebase.MyFirebaseInstanceIDService;
import com.app.barber.ui.postauth.activities.HomeActivity;
import com.app.barber.ui.postauth.activities.barber.ratesettings.RateBarberActivity;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.PreferenceManager;

import javax.inject.Inject;

/**
 * Created by harish on 16/10/18.
 */

public class SplashActivity extends BaseActivity {
    @Inject
    PreferenceManager mPref;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BarberApplication) getApplication()).getMyComponent(this).inject(this);

//        activitySwitcher(SplashActivity.this, RateBarberActivity.class, null);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /*if (mPref.getPrefrencesBoolean(GlobalValues.KEYS.isLogedIn))
                    activitySwitcher(SplashActivity.this, HomeActivity.class, null);
                else activitySwitcher(SplashActivity.this, LoginSignupActivity.class, null);*/
                finish();
                activitySwitcher(SplashActivity.this, HomeActivity.class, null);
                finish();
            }
        }, GlobalValues.TIME_DURATIONS.EXTRA_LARGE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // GET GOOGLE  TOKEN FOR PUSH NOTIFICATION....................
        new StartRegisterationService().execute();
    }

    private class StartRegisterationService extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(SplashActivity.this, MyFirebaseInstanceIDService.class);
            startService(intent);
            return null;
        }
    }
}
