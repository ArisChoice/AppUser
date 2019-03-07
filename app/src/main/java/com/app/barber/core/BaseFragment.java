package com.app.barber.core;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.barber.models.response.LoginResponseModel;
import com.app.barber.ui.postauth.fragment.AppointsmentsFragment;
import com.app.barber.ui.postauth.fragment.ExploreFragment;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.PreferenceManager;
import com.app.barber.util.bus_event.CustomEvent;
import com.app.barber.util.bus_event.Message;
import com.google.gson.Gson;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by harish on 16/10/18.
 */

public abstract class BaseFragment extends Fragment {
    @Inject
    public PreferenceManager mPref;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((BarberApplication) getActivity().getApplication()).getMyComponent(getActivity()).inject(this);
        View rootView = inflater.inflate(getFragmentLayout(), container, false);
        ButterKnife.bind(this, rootView);
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessage(CustomEvent event) {
        List<Fragment> cFragment = getChildFragmentManager().getFragments();
        for (int i = 0; i < cFragment.size(); i++) {
            if (cFragment instanceof ExploreFragment) {
                ExploreFragment.getInstance().onMessage(event);
            } else if (cFragment instanceof AppointsmentsFragment) {
                ExploreFragment.getInstance().onMessage(event);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    protected abstract int getFragmentLayout();

    public abstract void UpdateData(int position, Bundle bundle);

    public LoginResponseModel.UserBean getUserData() {

        return new Gson().fromJson(new PreferenceManager().getUserData(), LoginResponseModel.UserBean.class);
    }

    public void callInit(String s) {

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + s));
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, GlobalValues.RequestCodes.REQUEST_PHONE_CALL);
        } else {
            startActivity(intent);
        }

    }
}
