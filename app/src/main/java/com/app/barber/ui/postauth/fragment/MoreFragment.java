package com.app.barber.ui.postauth.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseFragment;
import com.app.barber.ui.adapters.OptionListAdapter;
import com.app.barber.ui.postauth.activities.home.AboutActivity;
import com.app.barber.ui.postauth.activities.home.AddressScreenActivity;
import com.app.barber.ui.postauth.activities.home.MyStyleActivity;
import com.app.barber.ui.postauth.activities.home.ProfileScreenActivity;
import com.app.barber.ui.postauth.activities.home.ReviewsActivity;
import com.app.barber.ui.postauth.activities.home.SettingActivity;
import com.app.barber.ui.postauth.activities.home.SupportActivity;
import com.app.barber.ui.postauth.activities.settings.InviteBarberActivity;
import com.app.barber.ui.postauth.activities.settings.NotificationActivity;
import com.app.barber.ui.postauth.activities.settings.PaymentHistoryActivity;
import com.app.barber.ui.postauth.activities.settings.PaymentListActivity;
import com.app.barber.ui.postauth.activities.settings.ShareActivity;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.FunctionalDialog;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.ImageUtility;
import com.app.barber.util.PreferenceManager;
import com.app.barber.util.iface.OnItemClickListener;
import com.app.barber.views.CustomTextView;
import com.facebook.drawee.view.SimpleDraweeView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.app.barber.core.BaseActivity.activitySwitcher;

/**
 * Created by harish on 23/10/18.
 */

public class MoreFragment extends BaseFragment {
    @Inject
    PreferenceManager mPref;
    @BindView(R.id.recyclar_view_lst)
    RecyclerView recyclarViewLst;
    @BindView(R.id.no_list_data_text)
    CustomTextView noListDataText;
    @BindView(R.id.user_image)
    SimpleDraweeView userImage;
    @BindView(R.id.user_name)
    CustomTextView userName;
    @BindView(R.id.account_phone_number)
    CustomTextView accountPhoneNumber;
    @BindView(R.id.edit_btn)
    ImageView editBtn;

    private OptionListAdapter optionAdapter;

    @Override
    protected int getFragmentLayout() {
        return R.layout.layout_more_screen;
    }

    @Override
    public void UpdateData(int position, Bundle bundle) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserData() != null) {
            userName.setText(getUserData().getFullName());
            accountPhoneNumber.setText(getUserData().getPhoneNumber());
            if (getUserData().getProfileImage() != null && !getUserData().getProfileImage().equals("")) {
                userImage.setImageURI(ImageUtility.getValidUrl(getUserData().getProfileImage()));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((BarberApplication) getActivity().getApplication()).getMyComponent(getActivity()).inject(this);
        View rootView = inflater.inflate(getFragmentLayout(), container, false);
        ButterKnife.bind(this, rootView);
        initRecyclarView();

        return rootView;
    }

    private void initRecyclarView() {
        optionAdapter = new OptionListAdapter(getActivity(), getNames(),
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position, int type, Object t) {
                        switch (position) {
                            case 0:
                                activitySwitcher(getActivity(), NotificationActivity.class, null);
                                break;
                            case 1:
                                activitySwitcher(getActivity(), MyStyleActivity.class, null);
                                break;
                            case 2:
                                activitySwitcher(getActivity(), AddressScreenActivity.class, null);
//                                activitySwitcher(getActivity(), AccountDetailScreen.class, null);
                                break;
                            /*case 3:
                                activitySwitcher(getActivity(), ReviewsActivity.class, null);
                                break;*/
                            case 3:
                                activitySwitcher(getActivity(), PaymentHistoryActivity.class, null);
                                break;
                            case 4:
                                activitySwitcher(getActivity(), SettingActivity.class, null);
                                break;
                            case 5:
                                activitySwitcher(getActivity(), InviteBarberActivity.class, null);
                                break;
                            case 6:
                                activitySwitcher(getActivity(), SupportActivity.class, null);
                                break;
                            case 7:
                                activitySwitcher(getActivity(), AboutActivity.class, null);
                                break;
                            case 8:
                                activitySwitcher(getActivity(), ShareActivity.class, null);
                                break;
                            case 9:
                                new FunctionalDialog().createAppRatingDialog(getActivity()).show();
                                break;
                            case 10:
                                activitySwitcher(getActivity(), ShareActivity.class, null);
                                break;
                            case 11:
                                mPref.setPrefrencesBoolean(GlobalValues.Extras.VERIFIED, true);
                                new CommonUtils().LogoutUser();
                                getActivity().finish();
                                break;
                        }
                    }
                });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclarViewLst.setLayoutManager(layoutManager);
        recyclarViewLst.setAdapter(optionAdapter);
    }

   /* private List<MoreOptionModel> generateList() {
        ArrayList<MoreOptionModel> list = new ArrayList<>();
        MoreOptionModel model = new MoreOptionModel();
        model.setTxtArray(getNames());
        list.add(model);
        return list;
    }*/

    private String[] getNames() {
        String[] name = getResources().getStringArray(R.array.option_names);
        return name;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @OnClick({R.id.user_image, R.id.user_name, R.id.edit_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_image:
                activitySwitcher(getActivity(), ProfileScreenActivity.class, null);
                break;
            case R.id.user_name:
                break;
            case R.id.edit_btn:
                activitySwitcher(getActivity(), ProfileScreenActivity.class, null);
                break;
        }
    }
}
