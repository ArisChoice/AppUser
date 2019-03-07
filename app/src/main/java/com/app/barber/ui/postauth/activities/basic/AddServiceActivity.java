package com.app.barber.ui.postauth.activities.basic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseActivity;
import com.app.barber.models.request.AddServiceModel;
import com.app.barber.models.response.ServiceListResponseModel;
import com.app.barber.models.response.UpdateDataResponse;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.postauth.activities.basic.basicmvp.BasicAuthMVPView;
import com.app.barber.ui.postauth.activities.basic.basicmvp.BssicAuthPresenter;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.GlobalValues;
import com.app.barber.views.CustomEditText;
import com.app.barber.views.CustomTextView;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 23/10/18.
 */

public class AddServiceActivity extends BaseActivity implements BasicAuthMVPView {
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.top_text)
    CustomTextView topText;
    @BindView(R.id.edtxt_service_name)
    CustomEditText edtxtServiceName;
    @BindView(R.id.edtxt_duration)
    CustomEditText edtxtDuration;
    @BindView(R.id.edtxt_price)
    CustomEditText edtxtPrice;
    @BindView(R.id.edtxt_price_type)
    CustomEditText edtxtPriceType;
    @BindView(R.id.continue_btn)
    CustomTextView continueBtn;
    @BindView(R.id.delete_btn)
    CustomTextView deleteBtn;
    private BssicAuthPresenter presenter;
    private boolean isEdit;
    private ServiceListResponseModel.ResponseBean serviceDats;

    @Override
    public int getLayoutId() {
        return R.layout.layout_service_detail_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BarberApplication) getApplication()).getMyComponent(this).inject(this);
        presenter = new BssicAuthPresenter(this);
        presenter.attachView(this);
        getIntentData(getIntent());
    }

    private void getIntentData(Intent intent) {
        Serializable serviceData = intent.getSerializableExtra(GlobalValues.KEYS.SERVICE_DETAIL);
        if (serviceData != null) {
            isEdit = true;
            topText.setText(R.string.str_service_detail);
            continueBtn.setText(R.string.str_update);
            deleteBtn.setVisibility(View.VISIBLE);
            serviceDats = (ServiceListResponseModel.ResponseBean) serviceData;
            edtxtServiceName.setText(((ServiceListResponseModel.ResponseBean) serviceData).getServiceName());
            edtxtDuration.setText(((ServiceListResponseModel.ResponseBean) serviceData).getDuration());
            edtxtPrice.setText("" + ((ServiceListResponseModel.ResponseBean) serviceData).getPrice());
            edtxtPriceType.setText(((ServiceListResponseModel.ResponseBean) serviceData).getPriceType());

        } else isEdit = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @OnClick({R.id.back_toolbar, R.id.continue_btn, R.id.delete_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.continue_btn:
                if (validated()) {
                    AddServiceModel addModel = new AddServiceModel();
                    addModel.setServiceName(edtxtServiceName.getText().toString());
                    addModel.setDuration(edtxtDuration.getText().toString());
                    addModel.setPrice(edtxtPrice.getText().toString());
                    addModel.setPriceType(edtxtPriceType.getText().toString());
                    if (isEdit)
                        addModel.setId("" + serviceDats.getId());
                    presenter.addService(NetworkConstatnts.RequestCode.API_ADD_SERVICE, addModel, true);
                }
//               activitySwitcher(AddServiceActivity.this, HomeActivity.class, null);
                break;
            case R.id.delete_btn:
                break;
        }
    }

    private boolean validated() {
        if (CommonUtils.isEmpty(edtxtServiceName)) {
            CommonUtils.getInstance(AddServiceActivity.this).displayMessage(AddServiceActivity.this, getString(R.string.error_service_name));
            return false;
        } else if (CommonUtils.isEmpty(edtxtDuration)) {
            CommonUtils.getInstance(AddServiceActivity.this).displayMessage(AddServiceActivity.this, getString(R.string.error_duration));
            return false;
        } else if (CommonUtils.isEmpty(edtxtPrice)) {
            CommonUtils.getInstance(AddServiceActivity.this).displayMessage(AddServiceActivity.this, getString(R.string.error_price));
            return false;
        } else if (CommonUtils.isEmpty(edtxtPriceType)) {
            CommonUtils.getInstance(AddServiceActivity.this).displayMessage(AddServiceActivity.this, getString(R.string.error_type));
            return false;
        } else
            return true;
    }

    @Override
    public void onSuccessResponse(int serviceMode, Object model) {
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_ADD_SERVICE:
                if (((UpdateDataResponse) model).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    clearFields();
                }
                break;
        }
    }

    private void clearFields() {
        edtxtPriceType.setText("");
        edtxtPrice.setText("");
        edtxtDuration.setText("");
        edtxtServiceName.setText("");
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
