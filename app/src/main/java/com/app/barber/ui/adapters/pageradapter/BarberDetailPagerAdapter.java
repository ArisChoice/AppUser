/*
package com.app.barber.ui.adapters.pageradapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.app.barber.BuildConfig;
import com.app.barber.R;
import com.app.barber.ui.postauth.activities.barber.fragments.DetailFragment;
import com.app.barber.ui.postauth.activities.barber.fragments.InfoFragment;
import com.app.barber.ui.postauth.activities.barber.fragments.PortfolioFragment;
import com.app.barber.ui.postauth.activities.barber.fragments.ReviewFragment;
import com.app.barber.ui.postauth.activities.barber.fragments.ServiceFragment;


*/
/**
 * Created by xicom on 9/5/16.
 *//*

public class BarberDetailPagerAdapter extends FragmentPagerAdapter {

    public static int COUNT_FRAGMENTS = 3;
    Context applicationContext;
    FragmentManager fragmentManager;

    public BarberDetailPagerAdapter(Context applicationContext, FragmentManager fragmentManager) {
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
                fragment = new InfoFragment();
                break;
            case 1:
                fragment = new ReviewFragment();
                break;
            case 2:
                fragment = new PortfolioFragment();
                break;

           */
/* default:
                BaseActivity.menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                fragment = new LandingFragment();
                break;*//*

        }
        return fragment;
    }

    public static void setCountFragments(int countFragments) {
        COUNT_FRAGMENTS = countFragments;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return applicationContext.getString(R.string.str_barber_info);
            case 1:
                return applicationContext.getString(R.string.str_reviews);
            case 2:
                return applicationContext.getString(R.string.str_portfolio);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return COUNT_FRAGMENTS;
    }
}
*/
