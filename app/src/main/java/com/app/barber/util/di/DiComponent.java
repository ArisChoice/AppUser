package com.app.barber.util.di;


import com.app.barber.core.BaseActivity;
import com.app.barber.core.BaseFragment;

import com.app.barber.core.core_mvp.CorePresenter;
import com.app.barber.net.firebase.MyFirebaseInstanceIDService;
import com.app.barber.net.firebase.MyFirebaseMessagingService;
import com.app.barber.ui.postauth.activities.HomeActivity;
import com.app.barber.ui.postauth.activities.barber.barbermvp.BarberPresenter;
import com.app.barber.ui.postauth.activities.barber.booking.BookingSheetFragment;
import com.app.barber.ui.postauth.activities.barber.booking.ConfirmBookingActivity;
import com.app.barber.ui.postauth.activities.barber.ratesettings.RateBarberActivity;
import com.app.barber.ui.postauth.activities.chat.chatmvp.ChatPresenter;
import com.app.barber.ui.postauth.activities.home.DistrictSheetFragment;
import com.app.barber.ui.postauth.activities.settings.settingmvp.SettingPresenter;
import com.app.barber.ui.postauth.activities.socket_work.chat.ChatActivity;
import com.app.barber.ui.postauth.fragment.ExploreFragment;
import com.app.barber.ui.postauth.fragment.MessageFragment;
import com.app.barber.ui.preauth.activities.ForgotPasswordActivity;
import com.app.barber.ui.preauth.activities.SplashActivity;
import com.app.barber.ui.postauth.activities.basic.AddServiceActivity;
import com.app.barber.ui.postauth.activities.basic.basicmvp.BssicAuthPresenter;
import com.app.barber.ui.postauth.activities.basic.SpecialiseActivity;
import com.app.barber.ui.postauth.activities.home.homemvp.HomeAuthPresenter;
import com.app.barber.ui.postauth.activities.home.ProfileScreenActivity;
import com.app.barber.ui.postauth.fragment.MoreFragment;
import com.app.barber.ui.preauth.authmvp.PreAuthPresenter;
import com.app.barber.ui.preauth.authrise.AuthPresenter;


import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Harish on 9/11/16.
 */


@Singleton
@Component(modules = {DiModule.class})

public interface DiComponent {
    void inject(SplashActivity splashActivityActivity);


    void inject(MyFirebaseInstanceIDService myFirebaseInstanceIDService);

    void inject(BaseActivity baseActivity);

    void inject(BaseFragment baseFragment);

    void inject(PreAuthPresenter preAuthPresenter);

    void inject(BssicAuthPresenter bssicAuthPresenter);

    void inject(SpecialiseActivity specialisation);


    void inject(AddServiceActivity addService);

    void inject(HomeAuthPresenter homeAuthPresenter);

    void inject(ProfileScreenActivity profileScreen);

    void inject(MoreFragment profileScreen);

    void inject(ForgotPasswordActivity profileScreen);

    void inject(HomeActivity homeActivity);

    void inject(BarberPresenter barberPresenter);

    void inject(ExploreFragment explorFreg);

    void inject(MessageFragment explorFreg);

    void inject(AuthPresenter authPresenter);

    void inject(BookingSheetFragment bookingSheetFragment);

    void inject(ConfirmBookingActivity cnfrmBooking);

    void inject(MyFirebaseMessagingService myFirebaseMessagingService);

    void inject(SettingPresenter settingPresenter);

    void inject(RateBarberActivity rateBarberActivity);

    void inject(ChatPresenter chatPresenter);

    void inject(CorePresenter corePresenter);

    void inject(DistrictSheetFragment districtSheetFragment);

    void inject(ChatActivity chatActivity);


    // to update the fields in your activities


}