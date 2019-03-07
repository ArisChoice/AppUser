package com.app.barber.ui.preauth.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseActivity;
import com.app.barber.models.ChangePasswordRequest;
import com.app.barber.models.response.BaseResponse;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.preauth.authmvp.PreAuthMVPView;
import com.app.barber.ui.preauth.authmvp.PreAuthPresenter;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.GlobalValues;
import com.app.barber.views.CustomEditText;
import com.app.barber.views.CustomTextView;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 22/10/18.
 */

public class ChangePasswordActivity extends BaseActivity implements PreAuthMVPView {

    @BindView(R.id.change_password_btn)
    CustomTextView changePasswordBtn;
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.message_txt)
    CustomTextView messageTxt;
    @BindView(R.id.ccp)
    CountryCodePicker ccp;
    @BindView(R.id.edtext_mobile_number)
    CustomEditText edtextMobileNumber;
    @BindView(R.id.layout_verify_mbile)
    LinearLayout layoutVerifyMbile;
    @BindView(R.id.resend_otp)
    CustomEditText resendOtp;
    @BindView(R.id.resend_btn)
    CustomTextView resendBtn;
    @BindView(R.id.layout_verify_otp)
    LinearLayout layoutVerifyOtp;
    @BindView(R.id.edtxt_new_password)
    CustomEditText edtxtNewPassword;
    @BindView(R.id.edtxt_password)
    CustomEditText edtxtPassword;
    @BindView(R.id.layout_password_update)
    LinearLayout layoutPasswordUpdate;
    private PreAuthPresenter presenter;

    @Override
    public int getLayoutId() {
        return R.layout.layout_change_password_activity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BarberApplication) getApplication()).getMyComponent(this).inject(this);

        presenter = new PreAuthPresenter(this);
        presenter.attachView(this);
        txtTitleToolbar.setText(R.string.str_change_password);
//        changePasswordBtn.setText(R.string.str_send_otp);
    }

    private boolean validated() {
        if (CommonUtils.isEmpty(edtxtNewPassword)) {
            CommonUtils.getInstance(this).displayMessage(this, getString(R.string.error_password));
            return false;
        } else if (!edtxtNewPassword.getText().toString().equals(edtxtPassword.getText().toString())) {
            CommonUtils.getInstance(this).displayMessage(this, getString(R.string.error_password_not_matched));
            return false;
        } else
            return true;
    }

    @Override
    public void onSuccessResponse(int serviceMode, Object o) {
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_FORGET_PASS:
                BaseResponse baseResponse = (BaseResponse) o;
                if (baseResponse != null)
                    if (baseResponse.getStatus() == NetworkConstatnts.ResponseCode.success) {
                        new CommonUtils().ShowToast(getString(R.string.str_password_updated_));
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                new CommonUtils().LogoutUser();
                                onBackPressed();
                            }
                        }, GlobalValues.TIME_DURATIONS.LARGE);
                    } else
                        new CommonUtils().displayMessage(ChangePasswordActivity.this, "");
                break;
        }
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

    @OnClick()
    public void onClick() {
/*        if (validated()) {
//            presenter.forgotPassword(NetworkConstatnts.RequestCode.API_FORGET_PASS, edtxtMobile.getText().toString(), true);
        }*/
    }

    @OnClick({R.id.back_toolbar, R.id.resend_btn, R.id.change_password_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                break;
            case R.id.resend_btn:
                break;
            case R.id.change_password_btn:
                if (validated()) {
                    ChangePasswordRequest cRequest = new ChangePasswordRequest();
                    cRequest.setPassword(edtxtPassword.getText().toString());
                    cRequest.setPhoneNumber(getUserData().getPhoneNumber());
                    cRequest.setUserType(GlobalValues.UserTypes.CUSTOMER);
                    presenter.forgotPassword(NetworkConstatnts.RequestCode.API_FORGET_PASS, cRequest, true);
                }
                break;
        }
    }
}
