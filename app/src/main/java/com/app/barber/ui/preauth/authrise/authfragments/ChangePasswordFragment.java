package com.app.barber.ui.preauth.authrise.authfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseFragment;

import com.app.barber.models.ChangePasswordRequest;
import com.app.barber.models.response.BaseResponse;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.preauth.authrise.AuthMVPView;
import com.app.barber.ui.preauth.authrise.AuthPresenter;
import com.app.barber.ui.preauth.authrise.AuthanticationActivity;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.GlobalValues;
import com.app.barber.views.CustomEditText;
import com.app.barber.views.CustomTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by harish on 14/12/18.
 */

public class ChangePasswordFragment extends BaseFragment implements AuthMVPView {
    @BindView(R.id.edtxt_password)
    CustomEditText edtxtPassword;
    @BindView(R.id.change_password_btn)
    CustomTextView changePasswordBtn;
    Unbinder unbinder;
    private AuthPresenter presenter;
    private String from, enteredNumber, countryCode;

    @Override
    protected int getFragmentLayout() {
        return R.layout.layout_change_password;
    }

    @Override
    public void UpdateData(int position, Bundle bundle) {
        from = bundle.getString(GlobalValues.Extras.FROM);
        enteredNumber = bundle.getString(GlobalValues.Extras.ADD_MOBILE);
        countryCode = bundle.getString(GlobalValues.Extras.COUNTRY_CODE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        ((BarberApplication) getActivity().getApplication()).getMyComponent(getActivity()).inject(this);

        presenter = new AuthPresenter(getActivity());
        presenter.attachView(this);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.change_password_btn)
    public void onClick() {
        if (validated()) {
            ChangePasswordRequest cRequest = new ChangePasswordRequest();
            cRequest.setPassword(edtxtPassword.getText().toString());
            cRequest.setPhoneNumber(enteredNumber);
            cRequest.setUserType(GlobalValues.UserTypes.CUSTOMER);
            presenter.forgotPassword(NetworkConstatnts.RequestCode.API_FORGET_PASS, cRequest, true);
        }
    }

    private boolean validated() {
        if (CommonUtils.isEmpty(edtxtPassword)) {
            CommonUtils.getInstance(getActivity()).displayMessage(getActivity(), getString(R.string.error_password));
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
                        new CommonUtils().displayMessage(getActivity(), baseResponse.getMessage());
                        AuthanticationActivity.getInstance().changeFragment(0, null);
                    } else
                        new CommonUtils().displayMessage(getActivity(), "");
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
}
