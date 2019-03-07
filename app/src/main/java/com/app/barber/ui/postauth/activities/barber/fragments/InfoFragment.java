package com.app.barber.ui.postauth.activities.barber.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.barber.R;
import com.app.barber.core.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by harish on 25/10/18.
 */

public class InfoFragment extends BaseFragment {
    @Override
    protected int getFragmentLayout() {
        return R.layout.layout_barberinfo_screen;
    }

    @Override
    public void UpdateData(int position, Bundle bundle) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getFragmentLayout(), container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
