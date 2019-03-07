/*
package com.app.barber.ui.preauth.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.app.barber.R;
import com.app.barber.core.BaseActivity;
import com.app.barber.ui.postauth.activities.HomeActivity;
import com.app.barber.views.CustomTextView;

import butterknife.BindView;
import butterknife.OnClick;

*/
/**
 * Created by harish on 22/10/18.
 *//*


public class LandingActivity extends BaseActivity {
    @BindView(R.id.sign_in_btn)
    CustomTextView signInBtn;
    @BindView(R.id.register_btn_btn)
    CustomTextView registerBtnBtn;
    @BindView(R.id.book_barber_btn)
    CustomTextView bookBarberBtn;

    @Override
    public int getLayoutId() {
        return R.layout.layout_landing_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick({R.id.sign_in_btn, R.id.register_btn_btn, R.id.book_barber_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_btn:
                activitySwitcher(LandingActivity.this, LoginSignupActivity.class, null);
                break;
            case R.id.register_btn_btn:
                activitySwitcher(LandingActivity.this, RegisterActivity.class, null);
                break;
            case R.id.book_barber_btn:
                activitySwitcher(LandingActivity.this, HomeActivity.class, null);
                break;
        }
    }
}
*/
