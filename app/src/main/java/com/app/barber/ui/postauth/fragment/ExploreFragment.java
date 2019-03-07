package com.app.barber.ui.postauth.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.app.barber.R;
import com.app.barber.core.BarberApplication;
import com.app.barber.core.BaseFragment;
import com.app.barber.models.request.RequestBarberModel;
import com.app.barber.models.request.RequestFavUnfavModel;
import com.app.barber.models.request.UpdateAddressRequestModel;
import com.app.barber.models.response.BarberListResponseModel;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.ui.postauth.activities.barber.AddressSelectionActivity;
import com.app.barber.ui.postauth.activities.barber.BarberDetailActivity;
import com.app.barber.ui.postauth.activities.barber.barber_adapter.BarberListAdapter;
import com.app.barber.ui.postauth.activities.barber.barber_adapter.HorizontalListAdapter;
import com.app.barber.ui.postauth.activities.barber.barbermvp.BarberMVPView;
import com.app.barber.ui.postauth.activities.barber.barbermvp.BarberPresenter;
import com.app.barber.ui.postauth.activities.barber.booking.BookingSheetFragment;
import com.app.barber.ui.postauth.activities.home.DistrictSheetFragment;
import com.app.barber.ui.postauth.activities.home.SearchActivity;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.FunctionalDialog;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.bus_event.CustomEvent;
import com.app.barber.util.bus_event.Message;
import com.app.barber.util.iface.OnBottomDialogItemListener;
import com.app.barber.views.CustomTextView;
import com.app.barber.views.WorkaroundMapFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.function.LongFunction;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.app.barber.core.BaseActivity.activitySwitcher;

/**
 * Created by harish on 25/10/18.
 */

public class ExploreFragment extends BaseFragment implements BarberMVPView, OnMapReadyCallback {
    private static int BARBER_TYPE = 0;//All=0,Barber=1,CallOutBarber=2;

    @BindView(R.id.top_bar)
    LinearLayout topBar;
    @BindView(R.id.search_field)
    LinearLayout searchField;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.recyclar_view_lst)
    RecyclerView recyclarViewLst;
    @BindView(R.id.no_list_data_text)
    CustomTextView noListDataText;
    @BindView(R.id.barber_type_btn)
    CustomTextView barberTypeBtn;
    @BindView(R.id.day_btn)
    CustomTextView dayBtn;
    @BindView(R.id.filter_btn)
    CustomTextView filterBtn;
    @BindView(R.id.location_filter_home)
    CustomTextView locationFilterHome;
    @BindView(R.id.top_view_holder)
    RelativeLayout topViewHolder;
    @BindView(R.id.map_holder_lay)
    FrameLayout mapHolderLay;
    @BindView(R.id.List_holder_view)
    LinearLayout ListHolderView;
    @BindView(R.id.show_map_btn)
    CustomTextView showMapBtn;
    @BindView(R.id.scroll_vw)
    ScrollView scrollVw;
    @BindView(R.id.horizontal_list)
    RecyclerView horizontalList;

    private BarberListAdapter listAdapter;
    private HorizontalListAdapter hListAdapter;
    private BarberPresenter presenter;

    public static FilterNotifier filterNotifier;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private RequestBarberModel modelRequest;
    int PAGE_LIMIT = 20;
    private static int PAGE_NO = 1;
    int TOTAL_COUNT = 20;
    private LinearLayoutManager layoutManager;
    public static ExploreFragment instance;
    private boolean isLoading;

    public static ExploreFragment getInstance() {
        return instance;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.getUiSettings().setCompassEnabled(false);

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();
//        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        mMap.setMyLocationEnabled(true);
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Toast.makeText(getActivity(), marker.getTitle(),
                        Toast.LENGTH_SHORT).show();

            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.scrollto(hListAdapter, horizontalList, marker.getSnippet(), mMap);
                    }
                }, GlobalValues.TIME_DURATIONS.SMALL);
                return true;
            }
        });
    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkPermis())
                    getLastKnownlocation();
                else requestPermission();
            }
        } catch (SecurityException e) {
        }
    }

    private boolean checkPermis() {
        int locationPermission = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
        return locationPermission == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                GlobalValues.RequestCodes.PERMISSIONS_REQUEST_);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case GlobalValues.RequestCodes.PERMISSIONS_REQUEST_: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLastKnownlocation();
                } else
                    Toast.makeText(getActivity(), "Location permission required", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    private void getLastKnownlocation() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            presenter.updateUserMarker(location, mMap);
//                            mPref.setUserLocation(new Gson().toJson(location));
                            mPref.setPrefrencesString(GlobalValues.KEYS.LATITUDE, "" + location.getLatitude());//save
                            mPref.setPrefrencesString(GlobalValues.KEYS.LONGITUDE, "" + location.getLongitude());//save
                            modelRequest.setLat("" + location.getLatitude());
                            modelRequest.setLong("" + location.getLongitude());
                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessage(CustomEvent event) {
        Log.d("onMessage", " " + event.getType());
        PAGE_NO = 1;
        switch (event.getType()) {
            case GlobalValues.EVENTS.LOCATION_CHANGE:
                UpdateAddressRequestModel selectedData = (UpdateAddressRequestModel) event.getOj();
                modelRequest.setLong(String.valueOf(selectedData.getLong()));
                modelRequest.setLat(String.valueOf(selectedData.getLat()));
//                Log.d("onMessage", " LOCATION CHANGE " + selectedData.getAddressLine1());
                locationFilterHome.setText(selectedData.getAddressLine1() + " " + selectedData.getCity());
                getBarbers();
                break;
            case GlobalValues.EVENTS.DISTRICT_FILTER:
                String sData = (String) event.getOj();
//                Log.d("onMessage", " DISTRICT_FILTER " + sData);
                if (modelRequest.getFilter() != null) {
                    modelRequest.getFilter().setDistricts(sData);
                } else {
                    RequestBarberModel rModel = new RequestBarberModel();
                    RequestBarberModel.Filter fModel = rModel.new Filter();
                    fModel.setDistricts(sData);
                    modelRequest.setFilter(fModel);
                }
                barberTypeBtn.setText(R.string.str_callout);
                getBarbers();
                break;
        }
        EventBus.getDefault().removeStickyEvent(event); // don't forget to remove the sticky event if youre done with it
    }

    /**
     * Screen Notifier Interface.
     */
    public interface FilterNotifier<R> {

        void callBackNotify(Object addressModel);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.layout_explor_screen;
    }

    @Override
    public void UpdateData(int position, Bundle bundle) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getFragmentLayout(), container, false);
        instance = this;
        ((BarberApplication) getActivity().getApplication()).getMyComponent(getActivity()).inject(this);
        presenter = new BarberPresenter(getActivity());
        presenter.attachView(this);
        ButterKnife.bind(this, rootView);
        modelRequest = new RequestBarberModel();
        if (mPref.getPrefrencesString(GlobalValues.KEYS.LATITUDE) != null && !mPref.getPrefrencesString(GlobalValues.KEYS.LATITUDE).equals("")) {
            modelRequest.setLat(mPref.getPrefrencesString(GlobalValues.KEYS.LATITUDE));
            modelRequest.setLong(mPref.getPrefrencesString(GlobalValues.KEYS.LONGITUDE));
            if (mPref.getPrefrencesString(GlobalValues.KEYS.ADDRESS) != null && !mPref.getPrefrencesString(GlobalValues.KEYS.ADDRESS).equals(""))
                locationFilterHome.setText(mPref.getPrefrencesString(GlobalValues.KEYS.ADDRESS));
        }
        initRecyclar();
        initMap();
        PAGE_NO = 1;
        getBarbers();
       /* filterNotifier = oj -> {
            if (oj != null) {
                UpdateAddressRequestModel selectedData = (UpdateAddressRequestModel) oj;
                modelRequest.setLong(String.valueOf(selectedData.getLong()));
                modelRequest.setLat(String.valueOf(selectedData.getLat()));
                locationFilterHome.setText(selectedData.getAddressLine1() + " " + selectedData.getCity());
            }
//            getBarbers();
        };*/
        searchField.bringToFront();

        return rootView;
    }

    private void initMap() {
        WorkaroundMapFragment mapFragment = (WorkaroundMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mapFragment.setListener(() -> scrollVw.requestDisallowInterceptTouchEvent(true));
        initHorizontalView();
    }

    private void initHorizontalView() {
        hListAdapter = new HorizontalListAdapter(getActivity(), new ArrayList<BarberListResponseModel.ListBean>(), ExploreFragment.class.getName(),
                (view, position, type, t) -> {
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

                });
        LinearLayoutManager layoutManagr = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        horizontalList.setLayoutManager(layoutManagr);
        horizontalList.setAdapter(hListAdapter);
        horizontalList.bringToFront();
        horizontalList.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = layoutManagr.getChildCount();
                int firstVisibleItemPosition = layoutManagr.findFirstVisibleItemPosition();
                final int lastItem = firstVisibleItemPosition + visibleItemCount;

                Log.e("onScrolled", "     " + firstVisibleItemPosition + "    " + lastItem);
                presenter.changeCameraFocus(firstVisibleItemPosition, hListAdapter.getList(), mMap);
                if (lastItem == PAGE_LIMIT && hListAdapter.getItemCount() < TOTAL_COUNT && !isLoading) {
                    PAGE_NO++;
                    isLoading = true;
                    getBarbers();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    int position = getCurrentItem();
                }
            }
        });

    }

    private int getCurrentItem() {
        return ((LinearLayoutManager) horizontalList.getLayoutManager())
                .findFirstVisibleItemPosition();
    }

    private void getBarbers() {
        modelRequest.setPageNo(PAGE_NO);
        modelRequest.setRecordsPerPage(String.valueOf(PAGE_LIMIT));
        if (mPref.getPrefrencesBoolean(GlobalValues.KEYS.isLogedIn))
            presenter.getBarberList(NetworkConstatnts.RequestCode.API_GET_BARBERS_AUTH, modelRequest, true);
        else
            presenter.getBarberList(NetworkConstatnts.RequestCode.API_GET_BARBERS, modelRequest, true);
    }

    private void initRecyclar() {
        listAdapter = new BarberListAdapter(getActivity(), new ArrayList<BarberListResponseModel.ListBean>(), ExploreFragment.class.getName(),
                (view, position, type, t) -> {
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

                });
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclarViewLst.setLayoutManager(layoutManager);
        recyclarViewLst.setAdapter(listAdapter);
        recyclarViewLst.setNestedScrollingEnabled(false);

        scrollVw.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                View view = (View) scrollVw.getChildAt(scrollVw.getChildCount() - 1);

                int diff = (view.getBottom() - (scrollVw.getHeight() + scrollVw.getScrollY()));

                if (diff == 0 && TOTAL_COUNT > listAdapter.getItemCount()) {
                    // your pagination code
                    Log.d("onScrolled ", " ==> " + TOTAL_COUNT + "    " + diff + "     " + listAdapter.getItemCount());
                    PAGE_NO++;
                    getBarbers();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onSuccessResponse(int serviceMode, Object o) {
        switch (serviceMode) {
            case NetworkConstatnts.RequestCode.API_GET_BARBERS_AUTH:
            case NetworkConstatnts.RequestCode.API_GET_BARBERS:
                BarberListResponseModel responseData = (BarberListResponseModel) o;
                if (responseData != null && responseData.getList() != null && responseData.getList().size() > 0) {
                    TOTAL_COUNT = responseData.getCount();
                    isLoading = false;
                    if (PAGE_NO == 1) {
                        listAdapter.updateAll(responseData.getList());
                        hListAdapter.updateAll(responseData.getList());
                        noListDataText.setVisibility(View.GONE);
                        recyclarViewLst.setVisibility(View.VISIBLE);
                    } else {
                        listAdapter.addAll(responseData.getList());
                        hListAdapter.addAll(responseData.getList());
                    }
                    presenter.updateMarkers(listAdapter.getList(), mMap);
                } else {
                    if (PAGE_NO == 1) {
                        listAdapter.removeAll();
                        hListAdapter.removeAll();
                        noListDataText.setVisibility(View.VISIBLE);
                        noListDataText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
                        noListDataText.setText(R.string.str_no_barber_found);
                        recyclarViewLst.setVisibility(View.GONE);
                    }
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

    @OnClick({R.id.barber_type_btn, R.id.day_btn, R.id.filter_btn, R.id.search_field, R.id.location_filter_home, R.id.relevance_filter_btn, R.id.show_map_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.barber_type_btn:
                new CommonUtils().openDialogBarberType(getActivity(), modelRequest, (view12, position, type, t) -> {
                    PAGE_NO = 1;
                    modelRequest = (RequestBarberModel) t;
                    Log.d(" onItemClick ", " " + new Gson().toJson(modelRequest));
                    if (modelRequest.getFilter().getBarberType().equals("1")) {
                        barberTypeBtn.setText(R.string.str_regular_);
                        getBarbers();
                    } else if (modelRequest.getFilter().getBarberType().equals("2")) {
                        DistrictSheetFragment bottomSheetFragment = new DistrictSheetFragment();
                        if (modelRequest.getFilter() != null && modelRequest.getFilter().getDistricts() != null) {
                            Bundle data = new Bundle();//create bundle instance
                            data.putString(GlobalValues.KEYS.FILTER_DISTRICT, modelRequest.getFilter().getDistricts());//put string to pass with a key value
                            bottomSheetFragment.setArguments(data);
                        }
                        bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());
                    } else barberTypeBtn.setText(R.string.str_barber);
                });
                break;
            case R.id.location_filter_home:
                mPref.setPrefrencesBoolean(GlobalValues.KEYS.isAddressFromSetting, false);
                activitySwitcher(getActivity(), AddressSelectionActivity.class, null);
                break;
            case R.id.day_btn:
                new FunctionalDialog().openDialogFilterDay(getActivity(), modelRequest, (view1, position, type, t) -> {
                    PAGE_NO = 1;
                    modelRequest = (RequestBarberModel) t;
                    Log.d(" onItemClick ", " " + new Gson().toJson(modelRequest));
                    getBarbers();
                });
                break;
            case R.id.show_map_btn:
                notifyMapView();
                break;
            case R.id.filter_btn:
                new FunctionalDialog().openDialogFilter(getActivity(), modelRequest, (view13, position, type, t) -> {
                    PAGE_NO = 1;
                    modelRequest = (RequestBarberModel) t;
                    Log.d(" onItemClick ", " " + new Gson().toJson(modelRequest));
                    if (modelRequest != null && modelRequest.Filter == null) {
                        barberTypeBtn.setText(R.string.str_barber);
                    }
                    getBarbers();
                });
                break;
            case R.id.search_field:
                activitySwitcher(getActivity(), SearchActivity.class, null);
                break;
            case R.id.relevance_filter_btn:
                new FunctionalDialog().openDialogRelevnceFilter(getActivity(), modelRequest, new OnBottomDialogItemListener() {
                    @Override
                    public void onItemClick(View view, int position, int type, Object t) {
                        PAGE_NO = 1;
                        modelRequest = (RequestBarberModel) t;
                        Log.d(" onItemClick ", " " + new Gson().toJson(modelRequest));
                        getBarbers();
                    }
                });
                break;
        }
    }

    private void notifyMapView() {
        if (mapHolderLay.getVisibility() == View.VISIBLE) {
            mapHolderLay.setVisibility(View.GONE);
            ListHolderView.setVisibility(View.VISIBLE);
            showMapBtn.setText(R.string.str_show_map);
            topViewHolder.bringToFront();
            searchField.bringToFront();
            showMapBtn.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.map_white, 0, 0, 0);
        } else {
            mapHolderLay.setVisibility(View.VISIBLE);
            ListHolderView.setVisibility(View.GONE);
            showMapBtn.setText(R.string.str_show_list);
            showMapBtn.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.paper, 0, 0, 0);
            searchField.bringToFront();
        }
        scrollVw.fullScroll(ScrollView.FOCUS_UP);
        scrollVw.smoothScrollTo(0, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(" onActivityResult ", " ");
    }
}
