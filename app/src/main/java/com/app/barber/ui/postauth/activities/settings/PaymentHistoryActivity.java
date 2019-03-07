package com.app.barber.ui.postauth.activities.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseActivity;
import com.app.barber.models.response.PaymentHistoryResponse;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.postauth.activities.settings.setting_adapters.PaymentsListAdapter;
import com.app.barber.ui.postauth.activities.settings.settingmvp.SettingMVPView;
import com.app.barber.ui.postauth.activities.settings.settingmvp.SettingPresenter;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 30/1/19.
 */

public class PaymentHistoryActivity extends BaseActivity implements SettingMVPView {
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.toolbar_common)
    Toolbar toolbarCommon;
    @BindView(R.id.payment_tabs)
    TabLayout paymentTabs;
    @BindView(R.id.recyclar_view_lst)
    RecyclerView recyclarViewLst;
    @BindView(R.id.no_list_data_text)
    CustomTextView noListDataText;
    private PaymentsListAdapter inPaymentAdapter, outPaymentAdapter;
    private SettingPresenter presenter;
    private int currentTab;

    /**
     * @return layout resource id
     */
    @Override
    public int getLayoutId() {
        return R.layout.layout_payment_history;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BarberApplication) getApplication()).getMyComponent(PaymentHistoryActivity.this).inject(this);
        presenter = new SettingPresenter(PaymentHistoryActivity.this);
        presenter.attachView(this);
        txtTitleToolbar.setText(R.string.str_payment_transec);
        initTabs();
        initAdapter();
        getPaymentHistory();
    }

    private void getPaymentHistory() {
        presenter.getPaymentHistory(NetworkConstatnts.RequestCode.API_GET_PAYMENT_HISTORY_IN, null, false);
        presenter.getPaymentHistory(NetworkConstatnts.RequestCode.API_GET_PAYMENT_HISTORY_OUT, null, false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(PaymentHistoryActivity.this, LinearLayoutManager.VERTICAL, false);
        inPaymentAdapter = new PaymentsListAdapter(PaymentHistoryActivity.this, paymentTabs.getSelectedTabPosition(), new ArrayList<>(), new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int type, Object o) {
                switch (type) {

                }
            }
        });
        recyclarViewLst.setLayoutManager(layoutManager);
//        recyclarViewLst.setAdapter(inPaymentAdapter);
        recyclarViewLst.setNestedScrollingEnabled(false);


        LinearLayoutManager layoutManager1 = new LinearLayoutManager(PaymentHistoryActivity.this, LinearLayoutManager.VERTICAL, false);
        outPaymentAdapter = new PaymentsListAdapter(PaymentHistoryActivity.this, paymentTabs.getSelectedTabPosition(), new ArrayList<>(), new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int type, Object o) {
                switch (type) {

                }
            }
        });
        recyclarViewLst.setLayoutManager(layoutManager1);
        recyclarViewLst.setAdapter(outPaymentAdapter);
        recyclarViewLst.setNestedScrollingEnabled(false);
    }

    private void initTabs() {
        paymentTabs.addTab(createTab(R.string.str_payment_out, R.drawable.arrow_right_copy));
        paymentTabs.addTab(createTab(R.string.str_payment_in, R.drawable.arrow_right_copy));
        paymentTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e(" onTabSelected ", " " + tab.getPosition());
                switch (tab.getPosition()) {

                    case 0:
                        currentTab = 0;
                        if (outPaymentAdapter.getItemCount() > 0) {
                            recyclarViewLst.setVisibility(View.VISIBLE);
                            recyclarViewLst.setAdapter(outPaymentAdapter);
                            noListDataText.setVisibility(View.GONE);
                        } else {
                            recyclarViewLst.setVisibility(View.GONE);
                            noListDataText.setVisibility(View.VISIBLE);
                            noListDataText.setText(R.string.no_payments_available);
                        }
                        break;
                    case 1:
                        currentTab = 1;
                        if (inPaymentAdapter.getItemCount() > 0) {
                            recyclarViewLst.setVisibility(View.VISIBLE);
                            recyclarViewLst.setAdapter(inPaymentAdapter);
                            noListDataText.setVisibility(View.GONE);
                        } else {
                            recyclarViewLst.setVisibility(View.GONE);
                            noListDataText.setVisibility(View.VISIBLE);
                            noListDataText.setText(R.string.no_payments_available);
                        }
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
//        wrapTabIndicatorToTitle(exploreTabLayout, 60, 60);
    }

    private TabLayout.Tab createTab(int title_appointments, int appoinments_) {
        TabLayout.Tab tab = paymentTabs.newTab().setText(getString(title_appointments)).setIcon(appoinments_).setCustomView(R.layout.custom_tab);
        // remove imageView bottom margin
        if (tab.getCustomView() != null) {
            ImageView imageView = (ImageView) tab.getCustomView().findViewById(android.R.id.icon);
            ViewGroup.MarginLayoutParams lp = ((ViewGroup.MarginLayoutParams) imageView.getLayoutParams());
            lp.bottomMargin = 0;
            lp.height = 20;
            imageView.requestLayout();
        }
        return tab;
    }

    @OnClick(R.id.back_toolbar)
    public void onClick() {
        onBackPressed();
    }

    @Override
    public void onSuccessResponse(int serviceMode, Object o) {
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_GET_PAYMENT_HISTORY_IN:
                PaymentHistoryResponse responseData = (PaymentHistoryResponse) o;
                if (responseData != null && responseData.getResponse() != null && responseData.getResponse().size() > 0) {
                    inPaymentAdapter.updateAll(responseData.getResponse());
                }
                break;
            case NetworkConstatnts.RequestCode.API_GET_PAYMENT_HISTORY_OUT:
                responseData = (PaymentHistoryResponse) o;
                if (responseData != null && responseData.getResponse() != null && responseData.getResponse().size() > 0) {
                    outPaymentAdapter.updateAll(responseData.getResponse());
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
