package com.app.barber.ui.postauth.activities.basic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.core.BaseActivity;
import com.app.barber.util.GlobalValues;
import com.app.barber.views.CustomTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 26/10/18.
 */

public class TimeSelectionActivity extends BaseActivity {
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.toolbar_common)
    Toolbar toolbarCommon;
    @BindView(R.id.selected_day)
    CustomTextView selectedDay;
    @BindView(R.id.opening_Time)
    CustomTextView openingTime;
    @BindView(R.id.open_change_btn)
    CustomTextView openChangeBtn;
    @BindView(R.id.closing_Time)
    CustomTextView closingTime;
    @BindView(R.id.close_change_btn)
    CustomTextView closeChangeBtn;
    @BindView(R.id.done_btn)
    CustomTextView doneBtn;

    @Override
    public int getLayoutId() {
        return R.layout.layout_time_chooser;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentData(getIntent());
        txtTitleToolbar.setText(R.string.select_times);
    }

    private void getIntentData(Intent intent) {
        String selecteDay = intent.getStringExtra(GlobalValues.KEYS.SELECTED_DAY);
        selectedDay.setText(selecteDay);
    }

    @OnClick({R.id.back_toolbar, R.id.open_change_btn, R.id.close_change_btn, R.id.done_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.open_change_btn:
                break;
            case R.id.close_change_btn:
                break;
            case R.id.done_btn:
                break;
        }
    }
}
