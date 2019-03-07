package com.app.barber.ui.postauth.activities.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseActivity;
import com.app.barber.models.request.RequestBarberModel;
import com.app.barber.models.request.RequestFavUnfavModel;
import com.app.barber.models.response.BarberListResponseModel;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.postauth.activities.barber.BarberDetailActivity;
import com.app.barber.ui.postauth.activities.barber.barber_adapter.BarberListAdapter;
import com.app.barber.ui.postauth.activities.barber.barbermvp.BarberMVPView;
import com.app.barber.ui.postauth.activities.barber.barbermvp.BarberPresenter;
import com.app.barber.ui.postauth.fragment.ExploreFragment;
import com.app.barber.util.GlobalValues;
import com.app.barber.views.CustomEditText;
import com.app.barber.views.CustomTextView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by harish on 12/12/18.
 */

public class SearchActivity extends BaseActivity implements BarberMVPView {
    private static final int PAGE_NO = 1;
    @BindView(R.id.search_field)
    CustomEditText searchField;
    @BindView(R.id.recyclar_view_lst)
    RecyclerView recyclarViewLst;
    @BindView(R.id.no_list_data_text)
    CustomTextView noListDataText;
    private BarberListAdapter listAdapter;
    private BarberPresenter presenter;
    private RequestBarberModel modelRequest;

    @Override
    public int getLayoutId() {
        return R.layout.layout_search_barber;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BarberApplication) getApplication()).getMyComponent(this).inject(this);
        presenter = new BarberPresenter(SearchActivity.this);
        presenter.attachView(this);
        modelRequest = new RequestBarberModel();
        modelRequest.setPageNo(PAGE_NO);
        modelRequest.setRecordsPerPage("20");
        initAdapter();
        initField();
    }

    private void initField() {
        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("onTextChanged", " " + s);
                if (s.length() > 0) {
                    modelRequest.setSearch(s.toString());
                    presenter.getSerachBarberList(NetworkConstatnts.RequestCode.API_SEARCH_BARBERS, modelRequest, false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("afterTextChanged", " " + s);
                if (s == null || s.length() == 0) {
                    modelRequest.setSearch("**");
                    presenter.getSerachBarberList(NetworkConstatnts.RequestCode.API_SEARCH_BARBERS, modelRequest, false);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void initAdapter() {
        listAdapter = new BarberListAdapter(SearchActivity.this, new ArrayList<BarberListResponseModel.ListBean>(), ExploreFragment.class.getName(),
                (view, position, type, t) -> {
                    switch (type) {
                        case GlobalValues.ClickOperations.SHOW_DETAIL:
                            BarberListResponseModel.ListBean positionData = ((BarberListResponseModel.ListBean) t);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(GlobalValues.KEYS.BARBER_DETAIL, positionData);
                            activitySwitcher(SearchActivity.this, BarberDetailActivity.class, bundle);
                            break;
                        case GlobalValues.ClickOperations.FAV_UNFAV:
                            if (mPref.getPrefrencesBoolean(GlobalValues.KEYS.isLogedIn)) {
                                BarberListResponseModel.ListBean barberData = ((BarberListResponseModel.ListBean) t);
                                RequestFavUnfavModel setModel = new RequestFavUnfavModel();
                                if (barberData.isFavourite())
                                    setModel.setAction(false);
                                else setModel.setAction(true);

                                setModel.setBarberId(barberData.getBarberId());
                                presenter.favUnfav(NetworkConstatnts.RequestCode.API_FAV_UNFAV, setModel, false);
                            }
                            break;

                    }

                });
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclarViewLst.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclarViewLst.setAdapter(listAdapter);
        recyclarViewLst.setNestedScrollingEnabled(false);
    }

    @Override
    public void onSuccessResponse(int serviceMode, Object o) {
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_GET_BARBERS_AUTH:
            case NetworkConstatnts.RequestCode.API_SEARCH_BARBERS:
                BarberListResponseModel responseData = (BarberListResponseModel) o;
                if (responseData != null && responseData.getList() != null && responseData.getList().size() > 0) {
                    listAdapter.updateAll(responseData.getList());
                    noListDataText.setVisibility(View.GONE);
                    recyclarViewLst.setVisibility(View.VISIBLE);
                } else {
                    noListDataText.setVisibility(View.VISIBLE);
                    recyclarViewLst.setVisibility(View.GONE);
                    noListDataText.setText(R.string.str_no_result_found);
                    noListDataText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
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
