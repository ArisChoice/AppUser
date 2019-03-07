package com.app.barber.core;

import android.app.Application;
import android.arch.lifecycle.ProcessLifecycleOwner;
import android.content.Context;

import com.app.barber.models.response.LoginResponseModel;
import com.app.barber.ui.postauth.activities.socket_work.util.AppLifeCycleObserver;
import com.app.barber.util.GlobalValues;
import com.app.barber.util.PreferenceManager;
import com.app.barber.util.di.DaggerDiComponent;
import com.app.barber.util.di.DiComponent;
import com.app.barber.util.di.DiModule;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;

/**
 * Created by harish on 16/10/18.
 */

public class BarberApplication extends Application {

    private static BarberApplication instance;
    private DiComponent myComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        myComponent = DaggerDiComponent.builder().diModule(new DiModule()).build();
        FirebaseApp.initializeApp(this);
        Fresco.initialize(this);
       /* QBSettings.getInstance().init(this, NetworkConstatnts.QB.APP_ID, NetworkConstatnts.QB.AUTH_KEY, NetworkConstatnts.QB.AUTH_SECRET);
        QBSettings.getInstance().setAccountKey(NetworkConstatnts.QB.ACCOUNT_KEY);
        QBChatService.setDebugEnabled(true);
        QBChatService.setDefaultPacketReplyTimeout(10000);
        QBSettings.getInstance().setEnablePushNotification(true);
        QBSettings.getInstance().setSubscribePushStrategy(SubscribePushStrategy.ALWAYS);

        QBChatService.ConfigurationBuilder chatServiceConfigurationBuilder = new QBChatService.ConfigurationBuilder();
        chatServiceConfigurationBuilder.setSocketTimeout(60); //Sets chat socket's read timeout in seconds
        chatServiceConfigurationBuilder.setKeepAlive(true); //Sets connection socket's keepAlive option.
        chatServiceConfigurationBuilder.setUseTls(true); //Sets the TLS security mode used when making the connection. By default TLS is disabled.
        QBChatService.setConfigurationBuilder(chatServiceConfigurationBuilder);*/


        // Observer to detect if the app is in background or foreground.
        AppLifeCycleObserver lifeCycleObserver
                = new AppLifeCycleObserver(getApplicationContext());

        // Adding the above observer to process lifecycle
        ProcessLifecycleOwner.get()
                .getLifecycle()
                .addObserver(lifeCycleObserver);
    }

    public static Context getInstance() {
        return instance;
    }

    public static DiComponent getMyComponent(Context applicationContext) {
        return ((BarberApplication) applicationContext.getApplicationContext()).myComponent;
    }

    public void logoutUser() {
//        CommonUtils.getInstance(this).ShowToast("Session Expired");
        new PreferenceManager().setPrefrencesBoolean(GlobalValues.KEYS.isLogedIn, false);

    }

    public LoginResponseModel.UserBean getUserData() {

        return new Gson().fromJson(new PreferenceManager().getUserData(), LoginResponseModel.UserBean.class);
    }

}
