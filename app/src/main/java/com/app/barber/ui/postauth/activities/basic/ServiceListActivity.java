/*
package com.app.barber.ui.postauth.activities.basic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseActivity;
import com.app.barber.models.response.ServiceListResponseModel;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.postauth.activities.barber.barber_adapter.ServiceListAdapter;
import com.app.barber.ui.postauth.activities.basic.basicmvp.BasicAuthMVPView;
import com.app.barber.ui.postauth.activities.basic.basicmvp.BssicAuthPresenter;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

*/
/**
 * Created by harish on 1/11/18.
 *//*


public class ServiceListActivity extends BaseActivity implements BasicAuthMVPView {
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
    @BindView(R.id.add_service_btn)
    LinearLayout addServiceBtn;
    @BindView(R.id.continue_btn)
    ImageView continueBtn;
    private LinearLayoutManager layoutManager;
    private ServiceListAdapter serviceAdapter;
    private BssicAuthPresenter presenter;

    @Override
    public int getLayoutId() {
        return R.layout.service_list_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BarberApplication) getApplication()).getMyComponent(this).inject(this);
        presenter = new BssicAuthPresenter(this);
        presenter.attachView(this);
        initRecyclar();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getServiceList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void getServiceList() {
        presenter.getUserServices(NetworkConstatnts.RequestCode.API_GET_SERVICE, false);

    }

    private void initRecyclar() {
        layoutManager = new LinearLayoutManager(ServiceListActivity.this);
        recyclarViewLst.setLayoutManager(layoutManager);
        serviceAdapter = new ServiceListAdapter(ServiceListActivity.this, new ArrayList<ServiceListResponseModel.ResponseBean>(),
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int positio, int type, Object o) {
                        switch (type) {
                            case GlobalValues.ClickOperations.SERVICE_DELETE:
                                serviceAdapter.remove(positio);
                                break;
                            case GlobalValues.ClickOperations.SERVICE_DETAIL:
                                Bundle bundle = new Bundle();
                                bundle.putSerializable(GlobalValues.KEYS.SERVICE_DETAIL, (ServiceListResponseModel.ResponseBean) o);
                                activitySwitcher(ServiceListActivity.this, AddServiceActivity.class, bundle);
                                break;
                        }

                    }
                });
        recyclarViewLst.setAdapter(serviceAdapter);
    }

    @OnClick({R.id.back_toolbar, R.id.add_service_btn, R.id.continue_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.add_service_btn:
                activitySwitcher(ServiceListActivity.this, AddServiceActivity.class, null);
                break;
            case R.id.continue_btn:
                activitySwitcher(ServiceListActivity.this, PaymentTypeActivity.class, null);
                break;
        }
    }

    @Override
    public void onSuccessResponse(int serviceMode, Object model) {
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_GET_SERVICE:
                if (((ServiceListResponseModel) model).getStatus() == NetworkConstatnts.ResponseCode.success) {
                    ServiceListResponseModel responseModel = ((ServiceListResponseModel) model);
                    if (responseModel.getResponse() != null && responseModel.getResponse().size() > 0) {
                        serviceAdapter.updateAll(responseModel.getResponse());
                        recyclarViewLst.setVisibility(View.VISIBLE);
                        noListDataText.setVisibility(View.GONE);
                    } else {
                        recyclarViewLst.setVisibility(View.GONE);
                        noListDataText.setVisibility(View.VISIBLE);
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
*/
