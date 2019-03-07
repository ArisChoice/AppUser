package com.app.barber.util.di;


import com.app.barber.net.RestConfig;
import com.app.barber.net.RestService;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.PreferenceManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Provides;


/**
 * Created by Harish on 9/11/16.
 */
@dagger.Module
public class DiModule {


    @Provides
    @Singleton
    public CommonUtils getUtil() {
        return new CommonUtils();
    }

    @Provides
    @Singleton
    public PreferenceManager getSharedStorage() {
        return new PreferenceManager();
    }


   /* @Provides
    @Singleton
    public MyInstanceIDListenerService getFireBaseTokenRefresh() {
        return new MyInstanceIDListenerService();
    }*/

    @Provides
    @Singleton
    @Named(DaggerValues.AUTH)
    RestService provideAuthRestService() {
        return new RestConfig().RestConfigWithAuth().create(RestService.class);
    }

    //provides unauthorised rest service
    @Provides
    @Singleton
    @Named(DaggerValues.NON_AUTH)
    RestService provideNonAuthRestService() {
        return new RestConfig().RestConfigWithOutAuth().create(RestService.class);
    }


}