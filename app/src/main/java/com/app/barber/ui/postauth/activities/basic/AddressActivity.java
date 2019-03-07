package com.app.barber.ui.postauth.activities.basic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseActivity;
import com.app.barber.models.request.UpdateAddressRequestModel;
import com.app.barber.models.response.AddressListResponseModel;
import com.app.barber.models.response.BarberListResponseModel;
import com.app.barber.models.response.LoginResponseModel;
import com.app.barber.models.response.UpdateDataResponse;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.postauth.activities.basic.basicmvp.BasicAuthMVPView;
import com.app.barber.ui.postauth.activities.basic.basicmvp.BssicAuthPresenter;
import com.app.barber.ui.postauth.fragment.ExploreFragment;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.bus_event.CustomEvent;
import com.app.barber.util.bus_event.Message;
import com.app.barber.views.CustomEditText;
import com.app.barber.views.CustomTextView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 23/10/18.
 */

public class AddressActivity extends BaseActivity implements BasicAuthMVPView {
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.edtxt_street_address)
    CustomEditText edtxtStreetAddress;
    @BindView(R.id.edtxt_appartmnet_suite_number)
    CustomEditText edtxtAppartmnetSuiteNumber;
    @BindView(R.id.edtxt_city)
    CustomEditText edtxtCity;
    @BindView(R.id.edtxt_zip_code)
    CustomEditText edtxtZipCode;
    @BindView(R.id.continue_btn)
    CustomTextView continueBtn;
    @BindView(R.id.edtxt_country)
    CustomEditText edtxtCountry;

    private BssicAuthPresenter presenter;
    private double latitude = 0.0, longitude = 0.0;
    private boolean isCurrent;
    private boolean isEdit;

    @Override
    public int getLayoutId() {
        return R.layout.layout_address_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BarberApplication) getApplication()).getMyComponent(this).inject(this);
        txtTitleToolbar.setText(R.string.str_enter_address);
        txtTitleToolbar.setVisibility(View.VISIBLE);
        presenter = new BssicAuthPresenter(this);
        presenter.attachView(this);
        getBundleData(getIntent());
    }

    private void getBundleData(Intent intent) {

        isEdit = intent.getBooleanExtra(GlobalValues.KEYS.IS_EDIT, false);
        if (!isEdit) {
            String data = intent.getStringExtra(GlobalValues.KEYS.PLACE_DETAIL);
            try {
                isCurrent = intent.getBooleanExtra(GlobalValues.KEYS.CURRENT_ADDRESS, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (data != null) {
                Log.d(" getBundleData ", " " + isCurrent + "     " + new Gson().toJson(data));
                String[] address = intent.getStringExtra(GlobalValues.KEYS.PLACE_DETAIL).split(",");
                try {
                    if (address != null && address.length != 0) {
                        try {

                            try {
                                edtxtStreetAddress.setText(address[0].replace( "@",","));
                            } catch (Exception e) {
                                edtxtStreetAddress.setText(address[0].split("@")[0]);
                            }
                            edtxtCountry.setText(address[address.length - 1]);
                            if (isCurrent) {
                                String picode = address[address.length - 2];
                                picode = picode.split(" ")[2];
                                edtxtZipCode.setText(picode);
                            } else {
                                try {
                                    edtxtZipCode.setText(address[address.length - 2]);
                                } catch (Exception e) {

                                }
                                if (address[address.length - 2].split("").length > 2)
                                    edtxtZipCode.setText(address[address.length - 2].split(" ")[2]);
                                else edtxtZipCode.setText(address[address.length - 2]);
                            }
                        } catch (Exception e) {

                        }

                        try {
                            edtxtCity.setText(address[address.length - 3]);
                        } catch (Exception e) {
                            edtxtCity.setText(address[2]);
                        }
                        latitude = intent.getDoubleExtra(GlobalValues.KEYS.LATITUDE, 0.0);
                        longitude = intent.getDoubleExtra(GlobalValues.KEYS.LONGITUDE, 0.0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    new CommonUtils().ShowToast("Unable to find a proper address.Please fill manually");
                }
            }
            try {
                if (latitude == 0.0) {
                    latitude = mPref.getLocation().getLatitude();
                    longitude = mPref.getLocation().getLongitude();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Serializable savedAddress = intent.getSerializableExtra(GlobalValues.KEYS.ADDRESS);
            if (savedAddress != null) {
                continueBtn.setText(R.string.str_update);
                AddressListResponseModel.ListBean savedData = (AddressListResponseModel.ListBean) savedAddress;
                edtxtStreetAddress.setText(savedData.getAddressLine1());
                edtxtAppartmnetSuiteNumber.setText(savedData.getAddressLine2());
                edtxtCity.setText(savedData.getCity());
                edtxtZipCode.setText(savedData.getZip());
                edtxtCountry.setText(savedData.getCountry());
                if (savedData.getLat() != null) {
                    latitude = Double.parseDouble(savedData.getLat());
                    longitude = Double.parseDouble(savedData.getLong());
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @OnClick({R.id.back_toolbar, R.id.continue_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.continue_btn:
                if (validated()) {
                    UpdateAddressRequestModel addressModel = new UpdateAddressRequestModel();
                    addressModel.setAddressLine1(edtxtStreetAddress.getText().toString());
                    addressModel.setAddressLine2(edtxtAppartmnetSuiteNumber.getText().toString());
                    addressModel.setCity(edtxtCity.getText().toString());
                    addressModel.setZip(edtxtZipCode.getText().toString());
                    addressModel.setLat(latitude);
                    addressModel.setLong(longitude);
                    addressModel.setCountry(edtxtCountry.getText().toString());

                    Log.e(" continue_btn ", "  " + new Gson().toJson(addressModel));
                    if (mPref.getPrefrencesBoolean(GlobalValues.KEYS.isAddressFromSetting)) {
                        presenter.uppdateUserAddress(NetworkConstatnts.RequestCode.API_UPDATE_ADDRESS, addressModel, true);
                    } else {
                      /*  ExploreFragment.FilterNotifier fNotifier = ExploreFragment.filterNotifier;
                        if (fNotifier != null) {
                            fNotifier.callBackNotify(addressModel);
                        }*/
                        EventBus.getDefault().postSticky(new CustomEvent(GlobalValues.EVENTS.LOCATION_CHANGE, addressModel));
                        finish();
                    }
                }
                break;
        }
    }

    private boolean validated() {
        if (CommonUtils.isEmpty(edtxtStreetAddress)) {
            CommonUtils.getInstance(AddressActivity.this).displayMessage(AddressActivity.this, getString(R.string.error_street));
            return false;
        } /*else if (CommonUtils.isEmpty(edtxtAppartmnetSuiteNumber)) {
            CommonUtils.getInstance(AddressActivity.this).displayMessage(AddressActivity.this, getString(R.string.error_street));
            return false;
        }*/ else if (CommonUtils.isEmpty(edtxtCity)) {
            CommonUtils.getInstance(AddressActivity.this).displayMessage(AddressActivity.this, getString(R.string.error_street));
            return false;
        } else if (CommonUtils.isEmpty(edtxtZipCode)) {
            CommonUtils.getInstance(AddressActivity.this).displayMessage(AddressActivity.this, getString(R.string.error_postal_code));
            return false;
        } else
            return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case GlobalValues.RequestCodes.ADDRESS_SEARCH:
                if (resultCode == RESULT_OK) {
                    try {
                        Log.e("onActivityResult ", " " + data.getStringExtra(GlobalValues.KEYS.PLACE_DETAIL));
                        String[] address = data.getStringExtra(GlobalValues.KEYS.PLACE_DETAIL).split(",");


                        if (address != null && address.length != 0) {
                            for (int i = 0; i < address.length; i++) {
                                edtxtStreetAddress.setText(address[0]);
                                try {
                                    edtxtStreetAddress.setText(address[0] + " " + address[1]);
                                } catch (Exception e) {
                                    edtxtCity.setText(address[1]);
                                }
                                try {
                                    edtxtZipCode.setText(address[address.length - 2]);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    new CommonUtils().displayMessage(AddressActivity.this, "Unable to find a proper address.Please fill manually");
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        new CommonUtils().displayMessage(AddressActivity.this, "Unable to find a proper address.Please fill manually");
                    }
                }
                break;
        }
    }

    @Override
    public void onSuccessResponse(int serviceMode, Object model) {
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_UPDATE_ADDRESS:
                if (((UpdateDataResponse) model).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    LoginResponseModel.UserBean userData = getUserData();
                    userData.setUserAddresses(((UpdateDataResponse) model).getAddress());
                    userData.setAddressAdded(true);
                    presenter.saveUserData(userData);
                    if (SearchAddressActivity.getInstance() != null)
                        SearchAddressActivity.getInstance().finish();
                    if (mPref.getPrefrencesBoolean(GlobalValues.KEYS.isAddressFromSetting)) {
                        mPref.setPrefrencesBoolean(GlobalValues.KEYS.isAddressFromSetting, false);
                        finish();
                    }

                }
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
