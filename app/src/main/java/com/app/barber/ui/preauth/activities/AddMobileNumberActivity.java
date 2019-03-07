package com.app.barber.ui.preauth.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.core.BaseActivity;
import com.app.barber.util.GlobalValues;
import com.app.barber.views.CustomEditText;
import com.app.barber.views.CustomTextView;
import com.hbb20.CountryCodePicker;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 29/10/18.
 */

public class AddMobileNumberActivity extends BaseActivity {
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.ccp)
    CountryCodePicker ccp;
    @BindView(R.id.edtext_mobile_number)
    CustomEditText edtextMobileNumber;
    @BindView(R.id.next_btn)
    ImageView nextBtn;

    @Override
    public int getLayoutId() {
        return R.layout.layout_add_mobile;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /* @Override
     public void onBackPressed() {
         Intent intent = new Intent();
         intent.putExtra(GlobalValues.Extras.ADD_MOBILE, ccp.getSelectedCountryCode() + edtextMobileNumber.getText().toString());
         setResult(RESULT_OK, intent);
         finish();
     }*/
    @OnClick({R.id.back_toolbar, R.id.ccp, R.id.next_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.ccp:
                break;
            case R.id.next_btn:
                Intent intent = new Intent();
                intent.putExtra(GlobalValues.Extras.ADD_MOBILE, edtextMobileNumber.getText().toString());
                intent.putExtra(GlobalValues.Extras.COUNTRY_CODE, ccp.getSelectedCountryCode());
                intent.putExtra(GlobalValues.Extras.VERIFIED, false);
                setResult(RESULT_OK, intent);
                Bundle bundle = new Bundle();
                bundle.putString(GlobalValues.Extras.COUNTRY_CODE, ccp.getSelectedCountryCode());
                bundle.putString(GlobalValues.Extras.ADD_MOBILE, edtextMobileNumber.getText().toString());
                activitySwitcherResult(AddMobileNumberActivity.this, VerifyMobileActivity.class, bundle, GlobalValues.RequestCodes.ADD_MOBILE);
                finish();
                break;
        }
    }
}
