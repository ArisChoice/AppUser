package com.app.barber.ui.preauth.authrise.authfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.core.BaseFragment;

import com.app.barber.ui.preauth.authrise.AuthMVPView;
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

public class ForgotPasswordFragment extends BaseFragment implements AuthMVPView {

    @BindView(R.id.close_btn)
    ImageView closeBtn;
    @BindView(R.id.ccp)
    CountryCodePicker ccp;
    @BindView(R.id.edtxt_mobile)
    CustomEditText edtxtMobile;
    @BindView(R.id.send_otp_btn)
    CustomTextView sendOtpBtn;
    Unbinder unbinder;

    @Override
    protected int getFragmentLayout() {
        return R.layout.layout_forgot_password;
    }

    @Override
    public void UpdateData(int position, Bundle bundle) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.close_btn, R.id.send_otp_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.close_btn:
                AuthanticationActivity.getInstance().changeFragment(0, null);
                break;
            case R.id.send_otp_btn:
                if (validated()) {
                    Bundle bundle = new Bundle();
                    bundle.putString(GlobalValues.Extras.FROM, ForgotPasswordFragment.class.getName());
                    bundle.putString(GlobalValues.Extras.ADD_MOBILE, edtxtMobile.getText().toString());
                    bundle.putString(GlobalValues.Extras.COUNTRY_CODE, ccp.getSelectedCountryCode());
                    AuthanticationActivity.getInstance().changeFragment(2, bundle);
//            presenter.forgotPassword(NetworkConstatnts.RequestCode.API_FORGET_PASS, edtxtMobile.getText().toString(), true);
                }
                break;
        }
    }

    private boolean validated() {
        if (CommonUtils.isEmpty(edtxtMobile)) {
            CommonUtils.getInstance(getActivity()).displayMessage(getActivity(), getString(R.string.error_phone_number));
            return false;
        } else
            return true;
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
