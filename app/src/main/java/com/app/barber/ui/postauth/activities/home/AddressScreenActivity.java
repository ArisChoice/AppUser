package com.app.barber.ui.postauth.activities.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseActivity;
import com.app.barber.models.response.AddressListResponseModel;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.postauth.activities.barber.AddressSelectionActivity;
import com.app.barber.ui.postauth.activities.basic.AddressActivity;
import com.app.barber.ui.postauth.activities.home.home_adapter.AddressListAdapter;
import com.app.barber.ui.postauth.activities.home.homemvp.HomeAuthMVPView;
import com.app.barber.ui.postauth.activities.home.homemvp.HomeAuthPresenter;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 16/11/18.
 */

public class AddressScreenActivity extends BaseActivity implements HomeAuthMVPView {

    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.recyclar_view_lst)
    RecyclerView recyclarViewLst;
    @BindView(R.id.no_list_data_text)
    CustomTextView noListDataText;
    @BindView(R.id.add_address_fab)
    ImageView addAddressFab;
    private HomeAuthPresenter presenter;
    private AddressListAdapter adDapter;

    @Override
    public int getLayoutId() {
        return R.layout.layout_user_address_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BarberApplication) getApplication()).getMyComponent(this).inject(this);
        presenter = new HomeAuthPresenter(this);
        presenter.attachView(this);
        txtTitleToolbar.setText(R.string.str_manage_address);
        initAddressAdapter();
    }

    private void initAddressAdapter() {
        adDapter = new AddressListAdapter(AddressScreenActivity.this, new ArrayList<AddressListResponseModel.ListBean>(), new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int type, Object o) {
                switch (type) {
                    case GlobalValues.ClickOperations.DETAILS:
                        mPref.setPrefrencesBoolean(GlobalValues.KEYS.isAddressFromSetting, true);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(GlobalValues.KEYS.ADDRESS, ((AddressListResponseModel.ListBean) o));
                        bundle.putBoolean(GlobalValues.KEYS.IS_EDIT, true);
                        activitySwitcher(AddressScreenActivity.this, AddressActivity.class, bundle);
                        break;
                }
            }
        });
        LinearLayoutManager lMAnger = new LinearLayoutManager(AddressScreenActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclarViewLst.setLayoutManager(lMAnger);
        recyclarViewLst.setAdapter(adDapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMyAddress();
    }

    private void getMyAddress() {
        presenter.getMyAddress(NetworkConstatnts.RequestCode.API_GET_USER_ADDRESS, null, true);
    }

    @OnClick({R.id.back_toolbar, R.id.img_edit, R.id.add_address_fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.img_edit:
                break;
            case R.id.add_address_fab:
                mPref.setPrefrencesBoolean(GlobalValues.KEYS.isAddressFromSetting, true);
                activitySwitcher(AddressScreenActivity.this, AddressSelectionActivity.class, null);
                break;
        }
    }

    @Override
    public void onSuccessResponse(int serviceMode, Object o) {
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_GET_USER_ADDRESS:
                AddressListResponseModel userAddress = ((AddressListResponseModel) o);
                if (userAddress.getList() != null && userAddress.getList().size() > 0) {
                    adDapter.updateAll(userAddress.getList());
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
