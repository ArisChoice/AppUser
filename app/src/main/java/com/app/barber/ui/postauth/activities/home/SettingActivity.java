package com.app.barber.ui.postauth.activities.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseActivity;
import com.app.barber.ui.postauth.activities.settings.settingmvp.SettingMVPView;
import com.app.barber.ui.postauth.activities.settings.settingmvp.SettingPresenter;
import com.app.barber.ui.preauth.activities.ChangePasswordActivity;
import com.app.barber.util.GlobalValues;
import com.app.barber.views.CustomTextView;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by harish on 12/11/18.
 */

public class SettingActivity extends BaseActivity implements SettingMVPView {


    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.toolbar_common)
    Toolbar toolbarCommon;
    @BindView(R.id.notification_btn_top)
    CustomTextView notificationBtnTop;
    @BindView(R.id.switch_top)
    Switch switchTop;
    @BindView(R.id.app_notification_btn)
    CustomTextView appNotificationBtn;
    @BindView(R.id.switch_app_notification)
    Switch switchAppNotification;
    @BindView(R.id.email_btn)
    CustomTextView emailBtn;
    @BindView(R.id.switch_email)
    Switch switchEmail;
    @BindView(R.id.change_password_btn)
    CustomTextView changePasswordBtn;
    @BindView(R.id.language_btn)
    CustomTextView languageBtn;
    private SettingPresenter presenter;

    @Override
    public int getLayoutId() {
        return R.layout.layout_setting_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BarberApplication) getApplication()).getMyComponent(SettingActivity.this).inject(this);
        presenter = new SettingPresenter(SettingActivity.this);
        presenter.attachView(this);
        txtTitleToolbar.setText(R.string.str_setting);
        switchAppNotification.setChecked(mPref.getPrefrencesBoolean(GlobalValues.KEYS.IS_APP_NOTIFICATION_ACTIVE));
        switchAppNotification.setOnCheckedChangeListener((buttonView, isChecked) -> mPref.setPrefrencesBoolean(GlobalValues.KEYS.IS_APP_NOTIFICATION_ACTIVE, isChecked));
        switchEmail.setChecked(mPref.getPrefrencesBoolean(GlobalValues.KEYS.IS_EMAIL_ACTIVE));
        switchEmail.setOnCheckedChangeListener((buttonView, isChecked) -> mPref.setPrefrencesBoolean(GlobalValues.KEYS.IS_EMAIL_ACTIVE, isChecked));

    }


    @OnClick({R.id.back_toolbar, R.id.change_password_btn, R.id.language_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.change_password_btn:
                activitySwitcher(SettingActivity.this, ChangePasswordActivity.class, new Bundle());
                break;
            case R.id.language_btn:
                break;
        }
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
}
