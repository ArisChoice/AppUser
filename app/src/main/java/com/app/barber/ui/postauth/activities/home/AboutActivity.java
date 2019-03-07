package com.app.barber.ui.postauth.activities.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.core.BaseActivity;
import com.app.barber.ui.postauth.activities.settings.BrowserActivity;
import com.app.barber.util.GlobalValues;
import com.app.barber.views.CustomTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 16/11/18.
 */

public class AboutActivity extends BaseActivity {
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.toolbar_common)
    Toolbar toolbarCommon;
    @BindView(R.id.trim_check_website)
    CustomTextView trimCheckWebsite;
    @BindView(R.id.terms_of_service)
    CustomTextView termsOfService;
    @BindView(R.id.privacy_policy)
    CustomTextView privacyPolicy;
    @BindView(R.id.license_)
    CustomTextView license;

    @Override
    public int getLayoutId() {
        return R.layout.layout_about_activity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        txtTitleToolbar.setText(R.string.str_about_trimcheck);
    }

    @OnClick({R.id.back_toolbar, R.id.trim_check_website, R.id.terms_of_service, R.id.privacy_policy, R.id.license_})
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.trim_check_website:
                bundle = new Bundle();
                bundle.putString(GlobalValues.KEYS.TITLE, GlobalValues.KEYS.WEBSITE);
                activitySwitcher(AboutActivity.this, BrowserActivity.class, bundle);
                break;
            case R.id.terms_of_service:
                bundle = new Bundle();
                bundle.putString(GlobalValues.KEYS.TITLE, GlobalValues.KEYS.TERMS);
                activitySwitcher(AboutActivity.this, BrowserActivity.class, bundle);
                break;
            case R.id.privacy_policy:
                bundle = new Bundle();
                bundle.putString(GlobalValues.KEYS.TITLE, GlobalValues.KEYS.PRIVACY);
                activitySwitcher(AboutActivity.this, BrowserActivity.class, bundle);
                break;
            case R.id.license_:
                break;
        }
    }
}
