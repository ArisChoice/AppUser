package com.app.barber.ui.postauth.activities.barber.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.barber.R;
import com.app.barber.core.BaseFragment;
import com.app.barber.models.response.ServiceListResponseModel;
import com.app.barber.ui.postauth.activities.barber.barber_adapter.BarberReviewsListAdapter;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by harish on 25/10/18.
 */

public class ReviewFragment extends BaseFragment {
    @BindView(R.id.recyclar_view_lst)
    RecyclerView recyclarViewLst;
    @BindView(R.id.no_list_data_text)
    CustomTextView noListDataText;
    private LinearLayoutManager layoutManager;
    private BarberReviewsListAdapter barberReviewAdapter;

    @Override
    protected int getFragmentLayout() {
        return R.layout.layout_review_screen;
    }

    @Override
    public void UpdateData(int position, Bundle bundle) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getFragmentLayout(), container, false);
        ButterKnife.bind(this, rootView);
        initRecyclar();
        return rootView;
    }

    private void initRecyclar() {
        recyclarViewLst.setNestedScrollingEnabled(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclarViewLst.setLayoutManager(layoutManager);
        barberReviewAdapter = new BarberReviewsListAdapter(getActivity(), new ArrayList<ServiceListResponseModel.ResponseBean>(10),
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int positio, int type, Object o) {
                        switch (type) {

                        }

                    }
                });
        recyclarViewLst.setAdapter(barberReviewAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
