package com.app.barber.ui.postauth.activities.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.core.BaseActivity;
import com.app.barber.views.CustomEditText;
import com.app.barber.views.CustomTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 16/11/18.
 */

public class AccountDetailScreen extends BaseActivity {
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.toolbar_common)
    Toolbar toolbarCommon;
    @BindView(R.id.edtxt_username)
    CustomEditText edtxtUsername;
    @BindView(R.id.edtxt_email)
    CustomEditText edtxtEmail;
    @BindView(R.id.edtxt_number)
    CustomEditText edtxtNumber;
    @BindView(R.id.edtxt_password)
    CustomEditText edtxtPassword;
    @BindView(R.id.continue_btn)
    ImageView continueBtn;

    @Override
    public int getLayoutId() {
        return R.layout.layout_address_detail_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        txtTitleToolbar.setText(R.string.str_account_detail);
    }

    @OnClick({R.id.back_toolbar, R.id.continue_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.continue_btn:
                onBackPressed();
                break;
        }
    }
}
