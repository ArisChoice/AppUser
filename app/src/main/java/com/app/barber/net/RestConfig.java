package com.app.barber.net;

import com.app.barber.core.BarberApplication;
import com.app.barber.models.response.LoginResponseModel;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.PreferenceManager;
import com.app.barber.util.ShaGenerator;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.app.barber.util.CommonUtils.getTimestampOther;


/**
 * Created by Harish on 24/08/18.
 */

public class RestConfig {

    public Retrofit RestConfigWithOutAuth() {
        return new Retrofit.Builder()
                .baseUrl(NetworkConstatnts.URL.BASE_URL)
                .client(provideClient(false))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit RestConfigWithAuth() {
        return new Retrofit.Builder()
                .baseUrl(NetworkConstatnts.URL.BASE_URL)
                .client(provideClient(true))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    OkHttpClient provideClient(final boolean isAuthorized) {
        final String timeStamp = String.valueOf(getTimestampOther());
        final String secretKey = NetworkConstatnts.KEYS.secretKey;

//        final String uniqueDeviceId = CommonUtils.getUniqueDeviceId(RumApplication.getInstance());
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.MINUTES);
        builder.readTimeout(5, TimeUnit.MINUTES);
        builder.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url()
                        .newBuilder()
                        .build();
                TimeZone tz = TimeZone.getDefault();
                if (isAuthorized) {
                    //for post login api's
                    final String sessionId = getSession();
                    String clientHash = sessionId + timeStamp + secretKey;
                    request = request.newBuilder()
                            .addHeader(NetworkConstatnts.KEYS.deviceType, NetworkConstatnts.DeviceType.Android)
//                            .addHeader(NetworkConstatnts.KEYS.userType, "1")//for barber=1 ,user=2
////                            .addHeader(NetworkConstatnts.KEYS.deviceId, CommonUtils.getDeviceId(RumApplication.getInstance()))
//                            .addHeader(NetworkConstatnts.KEYS.uniqueDeviceId, CommonUtils.getUniqueDeviceId(RumApplication.getInstance()))
                            .addHeader(NetworkConstatnts.KEYS.timezone, tz.getID())
                            .addHeader(NetworkConstatnts.KEYS.deviceToken, getDeviceToken())
////                            .addHeader(NetworkConstatnts.KEYS.userId, String.valueOf(getuserId()))
                            .addHeader(NetworkConstatnts.KEYS.sessionToken, sessionId)
                            .addHeader(NetworkConstatnts.KEYS.ClientHash, ShaGenerator.getInstance().getSha1Key(clientHash))
                            .addHeader(NetworkConstatnts.KEYS.TimeStamp, timeStamp)
                            .url(url)
                            .build();

                } else {
                    String clientHash = timeStamp + secretKey;
                    request = request.newBuilder()
                            .addHeader(NetworkConstatnts.KEYS.deviceType, NetworkConstatnts.DeviceType.Android)//Android=1
//                            .addHeader(NetworkConstatnts.KEYS.userType, "1")//for barber=1 ,user=2
                            .addHeader(NetworkConstatnts.KEYS.deviceId, "4trtrgfdgfdgdfgdfg"/* CommonUtils.getUniqueDeviceId(BarberApplication.getInstance())*/)
                            .addHeader(NetworkConstatnts.KEYS.timezone, tz.getID())
                            .addHeader(NetworkConstatnts.KEYS.deviceToken, getDeviceToken())
                            .addHeader(NetworkConstatnts.KEYS.TimeStamp, timeStamp)
                            .addHeader(NetworkConstatnts.KEYS.ClientHash, ShaGenerator.getInstance().getSha1Key(clientHash))
                            .url(url)
                            .build();
                }
                return chain.proceed(request);
            }
        });

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(5);
        builder.dispatcher(dispatcher);
        return builder.build();
    }

    private String getDeviceToken() {
        return "fghgfhfghgfhgfhfghgfhgf";
//        return new PreferenceManager().getDeviceId();
    }

    /*private int getuserId() {
        return new Gson().fromJson(new PreferenceManager().getUserData(), UserResponseModel.ResultBean.UserBean.class).getUserId();
    }*/

    private String getSession() {
        try {
            if (new Gson().fromJson(new PreferenceManager().getUserData(), LoginResponseModel.UserBean.class).getSessionId() != null)
                return new Gson().fromJson(new PreferenceManager().getUserData(), LoginResponseModel.UserBean.class).getSessionId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}