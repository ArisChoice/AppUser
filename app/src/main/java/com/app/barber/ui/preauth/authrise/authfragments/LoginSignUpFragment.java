package com.app.barber.ui.preauth.authrise.authfragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseFragment;

import com.app.barber.models.request.LoginRequestModel;
import com.app.barber.models.request.RegisterRequestModel;
import com.app.barber.models.request.ValidatePhoneNumberModel;
import com.app.barber.models.response.BaseResponse;
import com.app.barber.models.response.LoginResponseModel;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.preauth.authrise.AuthMVPView;
import com.app.barber.ui.preauth.authrise.AuthPresenter;
import com.app.barber.ui.preauth.authrise.AuthanticationActivity;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.GlobalValues;
import com.app.barber.views.CustomEditText;
import com.app.barber.views.CustomTextView;
import com.hbb20.CountryCodePicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by harish on 14/12/18.
 */

public class LoginSignUpFragment extends BaseFragment implements AuthMVPView {
    @BindView(R.id.tab_Layout)
    TabLayout tabLayout;
    @BindView(R.id.close_btn)
    ImageView closeBtn;
    @BindView(R.id.edtxt_full_name)
    CustomEditText edtxtFullName;
    @BindView(R.id.layout_fullname)
    LinearLayout layoutFullname;
    @BindView(R.id.ccp)
    CountryCodePicker ccp;
    @BindView(R.id.edtxt_mobile)
    CustomEditText edtxtMobile;
    @BindView(R.id.edtxt_password)
    CustomEditText edtxtPassword;
    @BindView(R.id.txt_forget_password)
    CustomTextView txtForgetPassword;
    @BindView(R.id.sign_in_btn)
    CustomTextView signInBtn;
    Unbinder unbinder;
    private boolean isLogin = true;
    private AuthPresenter presenter;
    private boolean isNumberVerified;

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_login_screen;
    }

    @Override
    public void UpdateData(int position, Bundle bundle) {
        isNumberVerified = false;
        if (bundle != null) {
            isNumberVerified = bundle.getBoolean(GlobalValues.Extras.VERIFIED, false);
            if (isNumberVerified)
                signupRequest();
        }
//        isNumberVerified = true;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        ((BarberApplication) getActivity().getApplication()).getMyComponent(getActivity()).inject(this);
        presenter = new AuthPresenter(getActivity());
        presenter.attachView(this);
        initTabs();
        return rootView;
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.txt_forget_password, R.id.sign_in_btn, R.id.close_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_forget_password:
                AuthanticationActivity.getInstance().changeFragment(1, new Bundle());
                break;
            case R.id.sign_in_btn:

                if (validated()) {
                    if (isLogin) {
                        loginRequest();
                    } else {
                        if (!isNumberVerified) {
                            checkPhoneNumberValidity();
                            //                            activitySwitcherResult(LoginSignupActivity.this, VerifyMobileActivity.class, bundle, GlobalValues.RequestCodes.ADD_MOBILE);
                        } else signupRequest();
                    }
                }
                break;
            case R.id.close_btn:
                if (AuthanticationActivity.getInstance() != null)
                    AuthanticationActivity.getInstance().onBackPressed();
                break;
        }
    }

    private boolean validated() {
        if (CommonUtils.isEmpty(edtxtFullName) && !isLogin) {
            CommonUtils.getInstance(getActivity()).displayMessage(getActivity(), getString(R.string.error_full_name));
            return false;
        } else if (CommonUtils.isEmpty(edtxtMobile)) {
            CommonUtils.getInstance(getActivity()).displayMessage(getActivity(), getActivity().getString(R.string.error_phone_number));
            return false;
        } else if (edtxtPassword.getText().toString().length() <= 5) {
            CommonUtils.getInstance(getActivity()).displayMessage(getActivity(), getString(R.string.error_password));
            return false;
        } else
            return true;
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
        presenter.signUpRequest(NetworkConstatnts.RequestCode.API_REGISTER, registerRequest, false);
    }

    private void checkPhoneNumberValidity() {
        ValidatePhoneNumberModel vModel = new ValidatePhoneNumberModel();
        vModel.setPhone(edtxtMobile.getText().toString());
        vModel.setUserType(GlobalValues.UserTypes.CUSTOMER);
        presenter.validatePhoneNUmber(NetworkConstatnts.RequestCode.API_VALIDATE_NUMBER, vModel, true);
    }

    private void loginRequest() {
        LoginRequestModel loginRequestModel = new LoginRequestModel();
        loginRequestModel.setUserName(edtxtMobile.getText().toString());
        loginRequestModel.setPassword(edtxtPassword.getText().toString());
        loginRequestModel.setUserType(GlobalValues.UserTypes.CUSTOMER);
        loginRequestModel.setDeviceId(presenter.getDeviceIdRegString());
        loginRequestModel.setDeviceType(NetworkConstatnts.DeviceType.Android);
        presenter.signInRequest(NetworkConstatnts.RequestCode.API_LOGIN, loginRequestModel, false);
    }

    @Override
    public void onSuccessResponse(int serviceMode, Object model) {
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_LOGIN:
                LoginResponseModel baseResponse = (LoginResponseModel) model;
                if (baseResponse != null)
                    if (baseResponse.getStatus() == NetworkConstatnts.ResponseCode.success) {
                        new CommonUtils().hideSoftKeyboard(getActivity(), signInBtn);
//                        presenter.loginToQb(getActivity(), baseResponse.getUser());
                        presenter.navigateUser(getActivity(), baseResponse);

//                        finish();
                    } else
                        new CommonUtils().ShowToast(baseResponse.getMessage());
                break;
            case NetworkConstatnts.RequestCode.API_REGISTER:
                if (model != null)
                    if (((BaseResponse) model).getStatus() == NetworkConstatnts.ResponseCode.success) {
                        new CommonUtils().displayMessage(getActivity(), ((BaseResponse) model).getMessage());
                        loginRequest();
                    } else
                        new CommonUtils().ShowToast(((BaseResponse) model).getMessage());
                else
                    new CommonUtils().ShowToast("");
                break;
            case NetworkConstatnts.RequestCode.API_VALIDATE_NUMBER:
                Bundle bundle = new Bundle();
                bundle.putString(GlobalValues.Extras.FROM, LoginSignUpFragment.class.getName());
                bundle.putString(GlobalValues.Extras.ADD_MOBILE, edtxtMobile.getText().toString());
                bundle.putString(GlobalValues.Extras.COUNTRY_CODE, ccp.getSelectedCountryCode());
                AuthanticationActivity.getInstance().changeFragment(2, bundle);
                break;
        }
    }

    @Override
    public void onfaliurResponse(int serviceMode, Object o) {
        new CommonUtils().ShowToast(((BaseResponse) o).getMessage());

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
