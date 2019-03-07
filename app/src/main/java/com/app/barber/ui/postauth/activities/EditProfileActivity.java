package com.app.barber.ui.postauth.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.barber.R;
import com.app.barber.core.BaseActivity;
import com.app.barber.views.CustomTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by harish on 16/10/18.
 */

public class EditProfileActivity extends BaseActivity {
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.layout_first_name)
    LinearLayout layoutFirstName;

    @Override
    public int getLayoutId() {
        return R.layout.activity_editprofile_screen;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        txtTitleToolbar.setText(R.string.str_edit_profile);
    }

    @OnClick({R.id.back_toolbar, R.id.layout_first_name, R.id.layout_last_name,
            R.id.layout_phone_number, R.id.layout_email})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.layout_first_name:
                activitySwitcher(EditProfileActivity.this, UpdateDataActivity.class, null);
                break;
            case R.id.layout_last_name:
                activitySwitcher(EditProfileActivity.this, UpdateDataActivity.class, null);
                break;
            case R.id.layout_phone_number:
                activitySwitcher(EditProfileActivity.this, UpdateDataActivity.class, null);
                break;
            case R.id.layout_email:
                activitySwitcher(EditProfileActivity.this, UpdateDataActivity.class, null);
                break;
        }
    }
}
