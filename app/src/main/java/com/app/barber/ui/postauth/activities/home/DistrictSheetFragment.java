package com.app.barber.ui.postauth.activities.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.models.response.BaseResponse;
import com.app.barber.models.response.DistricResponseModel;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.postauth.activities.home.home_adapter.DistrictsAdapter;
import com.app.barber.ui.postauth.activities.home.homemvp.HomeAuthMVPView;
import com.app.barber.ui.postauth.activities.home.homemvp.HomeAuthPresenter;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.PreferenceManager;
import com.app.barber.util.bus_event.CustomEvent;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomEditText;
import com.app.barber.views.CustomTextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DistrictSheetFragment extends BottomSheetDialogFragment implements HomeAuthMVPView {
    @Inject
    PreferenceManager mPref;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.recyclar_view_lst)
    RecyclerView recyclarViewLst;
    @BindView(R.id.no_list_data_text)
    CustomTextView noListDataText;
    @BindView(R.id.continue_btn)
    CustomTextView continueBtn;
    Unbinder unbinder;
    @BindView(R.id.search_field)
    CustomEditText searchField;
    private DistrictSheetFragment bottomSheetFragment;
    private HomeAuthPresenter presenter;
    private DistrictsAdapter districtsAdapter;
    private String selectedDistricts = null;


    public DistrictSheetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bottomSheetFragment = this;
        View view = inflater.inflate(R.layout.fragment_district_sheet_dialog, container, false);
        unbinder = ButterKnife.bind(this, view);
        ((BarberApplication) getActivity().getApplication()).getMyComponent(getActivity()).inject(this);
        presenter = new HomeAuthPresenter(getActivity());
        presenter.attachView(this);
        initDistrictView();
        getBundleData();
        getAvailableDistricts();
        initSearchFilter();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void getBundleData() {
        Bundle bundle = this.getArguments();
        if (bundle != null && bundle.getString(GlobalValues.KEYS.FILTER_DISTRICT) != null)
            selectedDistricts = bundle.getString(GlobalValues.KEYS.FILTER_DISTRICT);
    }

    private void getAvailableDistricts() {
        progressBar.setVisibility(View.VISIBLE);
        presenter.getAvailableDistricts(NetworkConstatnts.RequestCode.API_AVAILABLE_GET_DISTRICTS, false);
    }

    private void initDistrictView() {
        districtsAdapter = new DistrictsAdapter(getActivity(), new ArrayList<>(), new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int type, Object o) {
                Log.e(" onItemClick ", "  -- " + position);
                if (districtsAdapter.getselected() != null && !districtsAdapter.getselected().equals("")) {
                    continueBtn.setVisibility(View.VISIBLE);
                } else continueBtn.setVisibility(View.GONE);
//                getAvailableDistricts(new CommonUtils().getNewDate(position));
            }
        });
        LinearLayoutManager lManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclarViewLst.setLayoutManager(lManager);
        recyclarViewLst.setAdapter(districtsAdapter);

    }

    private void initSearchFilter() {
        // listening to search query text change
        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                districtsAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public static void activitySwitcher(Activity from, Class<?> to, Bundle bundle) {
        Intent intent = new Intent(from, to);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        from.startActivity(intent);
        from.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @Override
    public void onSuccessResponse(int serviceMode, Object o) {
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_AVAILABLE_GET_DISTRICTS:
                progressBar.setVisibility(View.GONE);
                DistricResponseModel responseModel = (DistricResponseModel) o;
                if (responseModel != null && responseModel.getList() != null && responseModel.getList().size() > 0) {
                    districtsAdapter.updateAll(responseModel.getList());
                    if (selectedDistricts != null && !selectedDistricts.equals("")) {
                        districtsAdapter.setSelected(selectedDistricts);
                    }
                    noListDataText.setVisibility(View.GONE);
                    recyclarViewLst.setVisibility(View.VISIBLE);
                } else {
                    noListDataText.setVisibility(View.VISIBLE);
                    noListDataText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
                    recyclarViewLst.setVisibility(View.GONE);
                }
                break;

        }
    }

    @Override
    public void onfaliurResponse(int serviceMode, Object o) {
        progressBar.setVisibility(View.GONE);
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

    @OnClick(R.id.continue_btn)
    public void onClick() {
        EventBus.getDefault().postSticky(new CustomEvent(GlobalValues.EVENTS.DISTRICT_FILTER, districtsAdapter.getselected()));
        bottomSheetFragment.dismiss();
    }
}