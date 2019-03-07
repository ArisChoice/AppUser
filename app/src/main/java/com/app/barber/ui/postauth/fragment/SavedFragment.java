package com.app.barber.ui.postauth.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseFragment;
import com.app.barber.models.request.RequestFavUnfavModel;
import com.app.barber.models.response.BarberListResponseModel;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.postauth.activities.barber.barber_adapter.BarberListAdapter;
import com.app.barber.ui.postauth.activities.barber.BarberDetailActivity;
import com.app.barber.ui.postauth.activities.barber.barbermvp.BarberMVPView;
import com.app.barber.ui.postauth.activities.barber.barbermvp.BarberPresenter;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.app.barber.core.BaseActivity.activitySwitcher;

/**
 * Created by harish on 25/10/18.
 */

public class SavedFragment extends BaseFragment implements BarberMVPView {
    @BindView(R.id.recyclar_view_lst)
    RecyclerView recyclarViewLst;
    @BindView(R.id.no_list_data_text)
    CustomTextView noListDataText;
    @BindView(R.id.first_header_txt)
    CustomTextView firstHeaderTxt;
    @BindView(R.id.second_header_txt)
    CustomTextView secondHeaderTxt;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private BarberListAdapter listAdapter;
    private BarberPresenter presenter;

    @Override
    protected int getFragmentLayout() {
        return R.layout.layout_saved_screen;
    }

    @Override
    public void UpdateData(int position, Bundle bundle) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((BarberApplication) getActivity().getApplication()).getMyComponent(getActivity()).inject(this);
        presenter = new BarberPresenter(getActivity());
        presenter.attachView(this);
        View rootView = inflater.inflate(getFragmentLayout(), container, false);
        ButterKnife.bind(this, rootView);
        firstHeaderTxt.setText(R.string.str_saved);
        secondHeaderTxt.setText("0 " + getActivity().getString(R.string.str_barber_saved));
        initRecyclayview();
        getSavedList();
        initSwipeRefresh();
        return rootView;
    }

    private void initSwipeRefresh() {
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(true);
                getSavedList();
            }
        });
    }

    private void getSavedList() {
        presenter.getSavedList(NetworkConstatnts.RequestCode.API_SAVED_BARBER_LIST, false);
    }

    private void initRecyclayview() {
        listAdapter = new BarberListAdapter(getActivity(), new ArrayList<BarberListResponseModel.ListBean>(),
                SavedFragment.class.getName(), new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, int type, Object t) {
                switch (type) {
                    case GlobalValues.ClickOperations.SHOW_DETAIL:
                        BarberListResponseModel.ListBean positionData = ((BarberListResponseModel.ListBean) t);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(GlobalValues.KEYS.BARBER_DETAIL, positionData);
                        activitySwitcher(getActivity(), BarberDetailActivity.class, bundle);
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
            }
        });
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclarViewLst.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclarViewLst.setAdapter(listAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onSuccessResponse(int serviceMode, Object o) {
        swipeRefresh.setRefreshing(false);
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_SAVED_BARBER_LIST:
                BarberListResponseModel responseData = (BarberListResponseModel) o;
                if (responseData != null && responseData.getList() != null && responseData.getList().size() > 0) {
                    listAdapter.updateAll(responseData.getList());
                    noListDataText.setVisibility(View.GONE);
                    recyclarViewLst.setVisibility(View.VISIBLE);
                    secondHeaderTxt.setText(listAdapter.getItemCount() + " " + getActivity().getString(R.string.str_barber_saved));
                } else {
                    noListDataText.setVisibility(View.VISIBLE);
                    recyclarViewLst.setVisibility(View.GONE);
                }
                break;
            case NetworkConstatnts.RequestCode.API_FAV_UNFAV:
                ExploreFragment.FilterNotifier refreshCall = ExploreFragment.filterNotifier;
                if (refreshCall != null) {
                    refreshCall.callBackNotify(null);
                }
                break;
        }
    }

    @Override
    public void onfaliurResponse(int serviceMode, Object o) {
        swipeRefresh.setRefreshing(false);
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
