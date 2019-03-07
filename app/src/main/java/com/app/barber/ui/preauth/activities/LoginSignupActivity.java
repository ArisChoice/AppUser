/*
package com.app.barber.ui.preauth.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseActivity;
import com.app.barber.models.request.LoginRequestModel;
import com.app.barber.models.request.RegisterRequestModel;
import com.app.barber.models.response.BaseResponse;
import com.app.barber.models.response.LoginResponseModel;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.preauth.authmvp.PreAuthMVPView;
import com.app.barber.ui.preauth.authmvp.PreAuthPresenter;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.PreferenceManager;
import com.app.barber.views.CustomEditText;
import com.app.barber.views.CustomTextView;
import com.hbb20.CountryCodePicker;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

*/
/**
 * Created by harish on 16/10/18.
 *//*


public class LoginSignupActivity extends BaseActivity implements PreAuthMVPView {
    String TAG = LoginSignupActivity.class.getSimpleName();
    @Inject
    public PreferenceManager mPref;
    @BindView(R.id.tab_Layout)
    TabLayout tabLayout;
    @BindView(R.id.edtxt_full_name)
    CustomEditText edtxtFullName;
    @BindView(R.id.layout_fullname)
    LinearLayout layoutFullname;
    @BindView(R.id.edtxt_mobile)
    CustomEditText edtxtMobile;
    @BindView(R.id.edtxt_password)
    CustomEditText edtxtPassword;
    @BindView(R.id.txt_forget_password)
    CustomTextView txtForgetPassword;
    @BindView(R.id.sign_in_btn)
    CustomTextView signInBtn;
    */
/*@BindView(R.id.imgVw_fb_btn)
    CustomTextView imgVwFbBtn;
    @BindView(R.id.imgVw_google_btn)
    CustomTextView imgVwGoogleBtn;
    @BindView(R.id.layout_social)
    LinearLayout layoutSocial;*//*

    @BindView(R.id.ccp)
    CountryCodePicker ccp;
    @BindView(R.id.close_btn)
    ImageView closeBtn;

    private PreAuthPresenter presenter;
    private boolean isLogin = true;
    private boolean isNumberVerified = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BarberApplication) getApplication()).getMyComponent(this).inject(this);
        presenter = new PreAuthPresenter(this);
        presenter.attachView(this);
        initTabs();
    }

    private void initTabs() {
        tabLayout.addTab(tabLayout.newTab().setText(R.string.str_sign_in));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.str_sign_up));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e(" onTabSelected ", " " + tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        loginVisibility();
                        break;
                    case 1:
                        signUpVisibility();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void signUpVisibility() {
        isLogin = false;
        layoutFullname.setVisibility(View.VISIBLE);
        signInBtn.setText(R.string.str_continue);
        txtForgetPassword.setVisibility(View.GONE);
    }

    private void loginVisibility() {
        isLogin = true;
        layoutFullname.setVisibility(View.GONE);
        signInBtn.setText(R.string.str_sign_in);
        txtForgetPassword.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }


    private boolean validated() {
        if (CommonUtils.isEmpty(edtxtFullName) && !isLogin) {
            CommonUtils.getInstance(this).displayMessage(this, getString(R.string.error_full_name));
            return false;
        } else if (CommonUtils.isEmpty(edtxtMobile)) {
            CommonUtils.getInstance(this).displayMessage(this, this.getString(R.string.error_phone_number));
            return false;
        } else if (edtxtPassword.getText().toString().length() <= 5) {
            CommonUtils.getInstance(this).displayMessage(this, getString(R.string.error_password));
            return false;
        } else
            return true;
    }

    private Object getRequestData() {
        return new Object();
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

    @Override
    public void onSuccessResponse(int serviceMode, Object model) {
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_LOGIN:
                LoginResponseModel baseResponse = (LoginResponseModel) model;
                if (baseResponse != null)
                    if (baseResponse.getStatus() == NetworkConstatnts.ResponseCode.success) {
                        new CommonUtils().displayMessage(LoginSignupActivity.this, baseResponse.getMessage());
                        presenter.navigateUser(this, baseResponse);
                        finish();
                    } else
                        new CommonUtils().displayMessage(LoginSignupActivity.this, "");
                new CommonUtils().displayMessage(LoginSignupActivity.this, "");
                break;
            case NetworkConstatnts.RequestCode.API_REGISTER:
                if (model != null)
                    if (((BaseResponse) model).getStatus() == NetworkConstatnts.ResponseCode.success) {
                        new CommonUtils().displayMessage(LoginSignupActivity.this, ((BaseResponse) model).getMessage());
                        loginRequest();
                    } else
                        new CommonUtils().displayMessage(LoginSignupActivity.this, ((BaseResponse) model).getMessage());
                else
                    new CommonUtils().displayMessage(LoginSignupActivity.this, "");
        }
    }


    @Override
    public void onfaliurResponse(int serviceMode, Object o) {

    }


    @OnClick({R.id.txt_forget_password, R.id.sign_in_btn, R.id.close_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_forget_password:
                activitySwitcher(LoginSignupActivity.this, ForgotPasswordActivity.class, null);
                onBackPressed();
                break;
            case R.id.sign_in_btn:

                if (validated()) {
                    if (isLogin) {
                        loginRequest();
                    } else {
                        if (!isNumberVerified) {
                            Bundle bundle = new Bundle();
                            bundle.putString(GlobalValues.Extras.FROM, LoginSignupActivity.class.getName());
                            bundle.putString(GlobalValues.Extras.ADD_MOBILE, edtxtMobile.getText().toString());
                            bundle.putString(GlobalValues.Extras.COUNTRY_CODE, ccp.getSelectedCountryCode());
                            activitySwitcherResult(LoginSignupActivity.this, VerifyMobileActivity.class, bundle, GlobalValues.RequestCodes.ADD_MOBILE);
                        }
//                        signupRequest();
                    }
                }
                break;
            case R.id.close_btn:
                onBackPressed();
                break;
        }
    }

    private void signupRequest() {
        RegisterRequestModel registerRequest = new RegisterRequestModel();
        registerRequest.setUserType(GlobalValues.UserTypes.CUSTOMER);//barber=1, user=2;
        registerRequest.setFullname(edtxtFullName.getText().toString());
        registerRequest.setEmail("");
        registerRequest.setUserName(edtxtFullName.getText().toString());
        registerRequest.setPhoneNumber(edtxtMobile.getText().toString());
        registerRequest.setPassword(edtxtPassword.getText().toString());
        registerRequest.setConfirmPassword(edtxtPassword.getText().toString());
        presenter.signUpRequest(NetworkConstatnts.RequestCode.API_REGISTER, registerRequest, true);
    }

    private void loginRequest() {
        LoginRequestModel loginRequestModel = new LoginRequestModel();
        loginRequestModel.setUserName(edtxtMobile.getText().toString());
        loginRequestModel.setPassword(edtxtPassword.getText().toString());
        loginRequestModel.setUserType(GlobalValues.UserTypes.CUSTOMER);
        loginRequestModel.setDeviceId(presenter.getDeviceIdRegString());
        loginRequestModel.setDeviceType(NetworkConstatnts.DeviceType.Android);
        presenter.signInRequest(NetworkConstatnts.RequestCode.API_LOGIN, loginRequestModel, true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case GlobalValues.RequestCodes.ADD_MOBILE:
                if (resultCode == RESULT_OK) {
                    if (data.getBooleanExtra(GlobalValues.Extras.VERIFIED, false)) {
                        isNumberVerified = true;
                        signupRequest();
                    } else isNumberVerified = false;
                }
                break;

        }
    }
}
*/
