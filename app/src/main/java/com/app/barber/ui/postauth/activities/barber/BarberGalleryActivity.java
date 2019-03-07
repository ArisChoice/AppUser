package com.app.barber.ui.postauth.activities.barber;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseActivity;
import com.app.barber.models.response.BarberListResponseModel;
import com.app.barber.models.response.BarberPortfoloiImageResponse;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.postauth.activities.barber.barber_adapter.BarberPortfolioAdapter;
import com.app.barber.ui.postauth.activities.barber.barbermvp.BarberMVPView;
import com.app.barber.ui.postauth.activities.barber.barbermvp.BarberPresenter;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.ImageUtility;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 21/1/19.
 */

public class BarberGalleryActivity extends BaseActivity implements BarberMVPView {
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.toolbar_common)
    Toolbar toolbarCommon;
    @BindView(R.id.recyclar_view_lst)
    RecyclerView recyclarViewLst;
    @BindView(R.id.no_list_data_text)
    CustomTextView noListDataText;
    private BarberPresenter presenter;
    //    private BarberListResponseModel.ListBean barberDataModel;
    private BarberPortfolioAdapter listAdapter;
    private int barberID;
    private String barberName;

    /**
     * @return layout resource id
     */
    @Override
    public int getLayoutId() {
        return R.layout.barber_gallery_activity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BarberApplication) getApplication()).getMyComponent(this).inject(this);
        presenter = new BarberPresenter(this);
        presenter.attachView(this);
        txtTitleToolbar.setText(R.string.str_portfolio);
        getIntentData(getIntent());
        initAdapter();
    }

    private void initAdapter() {
        listAdapter = new BarberPortfolioAdapter(BarberGalleryActivity.this, new ArrayList<>(),
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position, int type, Object t) {
                        switch (type) {
                            case GlobalValues.ClickOperations.SHOW_DETAIL:
                                BarberPortfoloiImageResponse.ResponseBean positionData = (BarberPortfoloiImageResponse.ResponseBean) t;
                                new CommonUtils().FullImageScreem(BarberGalleryActivity.this, ImageUtility.getValidUrl(positionData.getMItem1()));
                                break;
                        }
                    }
                });
        recyclarViewLst.setLayoutManager(new GridLayoutManager(BarberGalleryActivity.this, 3));
        recyclarViewLst.setAdapter(listAdapter);
    }

    private void getIntentData(Intent intent) {
//        Serializable barberData = intent.getSerializableExtra(GlobalValues.KEYS.BARBER_DETAIL);
        barberID = intent.getIntExtra(GlobalValues.KEYS.BARBER_ID, 0);
        barberName = intent.getStringExtra(GlobalValues.KEYS.BARBER_NAME);
        if (barberID != 0) {
//            barberDataModel = (BarberListResponseModel.ListBean) barberData;
            Log.d(" getIntentData ", " " + barberID);
            txtTitleToolbar.setText(barberName + " " + getString(R.string.str_portfolio));
            getBarberPortfolio();
        }
    }

    private void getBarberPortfolio() {
        presenter.getPortfolioImage(NetworkConstatnts.RequestCode.API_GET_BARBER_PORTFOLIO_IMAGE, barberID, true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        presenter.detachView();
    }

    @Override
    public void onSuccessResponse(int serviceMode, Object o) {
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_GET_BARBER_PORTFOLIO_IMAGE:
                BarberPortfoloiImageResponse responseData = (BarberPortfoloiImageResponse) o;
                if (responseData.getResponse() != null && responseData.getResponse().size() > 0) {
                    listAdapter.updateAll(responseData.getResponse());
                    noListDataText.setVisibility(View.GONE);
                    recyclarViewLst.setVisibility(View.VISIBLE);
                } else {
                    noListDataText.setVisibility(View.VISIBLE);
                    noListDataText.setText(R.string.str_no_portfolio_images);
                    noListDataText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
                    recyclarViewLst.setVisibility(View.GONE);
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

    @OnClick({R.id.back_toolbar, R.id.img_edit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.img_edit:
                break;
        }
    }
}
