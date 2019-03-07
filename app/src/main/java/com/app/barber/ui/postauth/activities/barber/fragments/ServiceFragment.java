package com.app.barber.ui.postauth.activities.barber.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.barber.R;
import com.app.barber.core.BaseFragment;
import com.app.barber.models.response.BarberDetialResponse;
import com.app.barber.models.response.ServiceListResponseModel;
import com.app.barber.ui.postauth.activities.barber.barber_adapter.ServiceListAdapter;
import com.app.barber.ui.postauth.activities.barber.BookAppointmentActivity;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.app.barber.core.BaseActivity.activitySwitcher;

/**
 * Created by harish on 25/10/18.
 */

public class ServiceFragment extends BaseFragment {
    @BindView(R.id.recyclar_view_lst)
    RecyclerView recyclarViewLst;
    @BindView(R.id.no_list_data_text)
    CustomTextView noListDataText;
    private LinearLayoutManager layoutManager;
    private ServiceListAdapter serviceAdapter;
    private BarberDetialResponse.InfoBean responseDataModel;

    @Override
    protected int getFragmentLayout() {
        return R.layout.layout_service_screen;
    }

    @Override
    public void UpdateData(int position, Bundle bundle) {
        Serializable responseData = bundle.getSerializable(GlobalValues.KEYS.BARBER_DETAIL);
        if (responseData != null) {
            responseDataModel = (BarberDetialResponse.InfoBean) responseData;
            if (responseDataModel != null && responseDataModel.getServices() != null && responseDataModel.getServices().size() > 0) {
                serviceAdapter.updateAll(responseDataModel.getServices());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getFragmentLayout(), container, false);
        ButterKnife.bind(this, rootView);
        initRecyclar();
        return rootView;
    }

    private void initRecyclar() {
        layoutManager = new LinearLayoutManager(getActivity());
        recyclarViewLst.setLayoutManager(layoutManager);
        serviceAdapter = new ServiceListAdapter(getActivity(), new ArrayList<ServiceListResponseModel.ResponseBean>(10),
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int positio, int type, Object o) {
                        switch (type) {
                            case GlobalValues.ClickOperations.SERVICE_DELETE:
//                                serviceAdapter.remove(positio);
                                break;
                            case GlobalValues.ClickOperations.SERVICE_BOOK:
                                Bundle bundle = new Bundle();
                                bundle.putSerializable(GlobalValues.KEYS.SERVICE_DETAIL, (ServiceListResponseModel.ResponseBean) o);
                                activitySwitcher(getActivity(), BookAppointmentActivity.class, bundle);
                                break;
                        }
                    }
                });
        recyclarViewLst.setAdapter(serviceAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
