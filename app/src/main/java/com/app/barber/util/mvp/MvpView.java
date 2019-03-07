package com.app.barber.util.mvp;

/**
 * Base interface that any class that wants to act as a View in the MVP (Model View Presenter)
 * pattern must implement. Generally this interface will be extended by a more specific interface
 * that then usually will be implemented by an Activity or Fragment.
 */
public interface MvpView<T> {

    void showProgres();

    void hidePreogress();

    void onSuccess(T t,int type);

    void onError(String localizedMessage);

    void onException(Exception e);


}