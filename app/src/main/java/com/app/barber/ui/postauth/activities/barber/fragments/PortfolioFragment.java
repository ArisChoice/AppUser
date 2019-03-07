package com.app.barber.ui.postauth.activities.barber.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.barber.R;
import com.app.barber.core.BaseFragment;
import com.app.barber.ui.postauth.activities.barber.barber_adapter.BarberPortfolioAdapter;
import com.app.barber.ui.postauth.activities.barber.BarberDetailActivity;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.app.barber.core.BaseActivity.activitySwitcher;

/**
 * Created by harish on 25/10/18.
 */

public class PortfolioFragment extends BaseFragment {
    @BindView(R.id.recyclar_view_lst)
    RecyclerView recyclarViewLst;
    @BindView(R.id.no_list_data_text)
    CustomTextView noListDataText;
    private BarberPortfolioAdapter listAdapter;

    @Override
    protected int getFragmentLayout() {
        return R.layout.layout_portfolio_screen;
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
        listAdapter = new BarberPortfolioAdapter(getActivity(), null,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position, int type, Object t) {
                        activitySwitcher(getActivity(), BarberDetailActivity.class, new Bundle());
                    }
                });
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclarViewLst.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclarViewLst.setAdapter(listAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
