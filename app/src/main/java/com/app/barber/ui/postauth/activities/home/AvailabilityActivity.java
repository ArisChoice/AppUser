package com.app.barber.ui.postauth.activities.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.core.BaseActivity;
import com.app.barber.views.CustomTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 6/11/18.
 */

public class AvailabilityActivity extends BaseActivity {
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.toolbar_common)
    Toolbar toolbarCommon;
    @BindView(R.id.opening_hrs_btn)
    CustomTextView openingHrsBtn;
    @BindView(R.id.callout_hours_btn)
    CustomTextView calloutHoursBtn;
    @BindView(R.id.block_hrs_btn)
    CustomTextView blockHrsBtn;
    @BindView(R.id.how_far_btn)
    CustomTextView howFarBtn;

    @Override
    public int getLayoutId() {
        return R.layout.layout_availability_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        txtTitleToolbar.setText(R.string.str_availabliity);

    }

    @OnClick({R.id.back_toolbar, R.id.opening_hrs_btn, R.id.callout_hours_btn, R.id.block_hrs_btn, R.id.how_far_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.opening_hrs_btn:
                break;
            case R.id.callout_hours_btn:
                break;
            case R.id.block_hrs_btn:
                break;
            case R.id.how_far_btn:
                break;
        }
    }
}
