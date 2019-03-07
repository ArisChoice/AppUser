package com.app.barber.ui.preauth.authrise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.app.barber.R;
import com.app.barber.core.BaseActivity;
import com.app.barber.ui.preauth.authmvp.PreAuthMVPView;
import com.app.barber.ui.preauth.authrise.authfragments.ChangePasswordFragment;
import com.app.barber.ui.preauth.authrise.authfragments.ForgotPasswordFragment;
import com.app.barber.ui.preauth.authrise.authfragments.LoginSignUpFragment;
import com.app.barber.ui.preauth.authrise.authfragments.VerifyMobileFragment;
import com.app.barber.views.CustomViewPager;

import java.util.List;

import butterknife.BindView;

/**
 * Created by harish on 14/12/18.
 */

public class AuthanticationActivity extends BaseActivity implements PreAuthMVPView {

    private static AuthanticationActivity instance;
    @BindView(R.id.auth_pager)
    CustomViewPager authPager;
    private AuthPageAdapter authPagerAdapter;

    public static AuthanticationActivity getInstance() {
        return instance;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_auth_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        initPager();
    }

    /**
     * initialize pager
     */
    private void initPager() {
        authPagerAdapter = new AuthPageAdapter(getApplicationContext(), getSupportFragmentManager());
        authPager.setAdapter(authPagerAdapter);
//        authPager.setPagingEnabled(true);
        authPager.setOffscreenPageLimit(1);
        authPager.swipeable = false;
//        pointDetailPager.setPageTransformer(true, new AccordionTransformer());
        authPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                currentPage = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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

    public void changeFragment(int i, Bundle bundle) {
        authPager.setCurrentItem(i);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                switch (i) {
                    case 0:
                        if (fragment instanceof LoginSignUpFragment) {
                            ((LoginSignUpFragment) fragment).UpdateData(i, bundle);
                        }
                        break;
                    case 1:
                        if (fragment instanceof ForgotPasswordFragment) {
                            ((ForgotPasswordFragment) fragment).UpdateData(i, bundle);
                        }
                        break;
                    case 2:
                        if (fragment instanceof VerifyMobileFragment) {
                            ((VerifyMobileFragment) fragment).UpdateData(i, bundle);
                        }
                        break;
                    case 3:
                        if (fragment instanceof ChangePasswordFragment) {
                            ((ChangePasswordFragment) fragment).UpdateData(i, bundle);
                        }
                        break;
                }
            }

        }
    }
}
