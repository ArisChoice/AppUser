package com.app.barber.ui.adapters.pageradapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.app.barber.BuildConfig;
import com.app.barber.R;
import com.app.barber.ui.postauth.activities.barber.fragments.DetailFragment;
import com.app.barber.ui.postauth.activities.barber.fragments.PortfolioFragment;
import com.app.barber.ui.postauth.activities.barber.fragments.ReviewFragment;
import com.app.barber.ui.postauth.activities.barber.fragments.ServiceFragment;


/**
 * Created by xicom on 9/5/16.
 */
public class BarberPagerAdapter extends FragmentPagerAdapter {

    public static int COUNT_FRAGMENTS = 2;
    Context applicationContext;
    FragmentManager fragmentManager;

    public BarberPagerAdapter(Context applicationContext, FragmentManager fragmentManager) {
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
                fragment = new ServiceFragment();
                break;
            case 1:
                fragment = new DetailFragment();
                break;
           /* case 2:
                fragment = new ReviewFragment();
                break;
            case 3:
                fragment = new PortfolioFragment();
                break;*/

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

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return applicationContext.getString(R.string.str_service);
            case 1:
                return applicationContext.getString(R.string.str_detail);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return COUNT_FRAGMENTS;
    }
}
