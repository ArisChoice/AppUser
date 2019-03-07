package com.app.barber.ui.preauth.authrise;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.app.barber.BuildConfig;
import com.app.barber.ui.preauth.authrise.authfragments.ChangePasswordFragment;
import com.app.barber.ui.preauth.authrise.authfragments.ForgotPasswordFragment;
import com.app.barber.ui.preauth.authrise.authfragments.LoginSignUpFragment;
import com.app.barber.ui.preauth.authrise.authfragments.VerifyMobileFragment;


/**
 * Created by xicom on 9/5/16.
 */
public class AuthPageAdapter extends FragmentStatePagerAdapter {

    public static int COUNT_FRAGMENTS = 4;
    Context applicationContext;
    FragmentManager fragmentManager;

    public AuthPageAdapter(Context applicationContext, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
        this.applicationContext = applicationContext;
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (BuildConfig.DEBUG) {
            Log.d("CURRENT POSITION", "==>" + position);
        }
        switch (position) {
            case 0:
                fragment = new LoginSignUpFragment();
                break;
            case 1:
                fragment = new ForgotPasswordFragment();
                break;
            case 2:
                fragment = new VerifyMobileFragment();
                break;
            case 3:
                fragment = new ChangePasswordFragment();
                break;


           /* default:
                BaseActivity.menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                fragment = new LandingFragment();
                break;*/
        }
        return fragment;
    }

    public static void setCountFragments(int countFragments) {
        COUNT_FRAGMENTS = countFragments;
    }

    @Override
    public int getCount() {
        return COUNT_FRAGMENTS;
    }
}
