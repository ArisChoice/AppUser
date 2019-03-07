package com.app.barber.util.iface;

/**
 * Created by harish on 6/2/19.
 */

public interface OnCallBackResult<T> {
    void onResult(Object oj);

    void onError(Object oj);
}
