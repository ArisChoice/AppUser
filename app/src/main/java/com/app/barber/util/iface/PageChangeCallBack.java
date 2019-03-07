package com.app.barber.util.iface;

/**
 * Created by harish on 23/10/18.
 */

public interface PageChangeCallBack<T> {
    void onChangePage(int i,Object oj);

    void goPrevicePage();
}
