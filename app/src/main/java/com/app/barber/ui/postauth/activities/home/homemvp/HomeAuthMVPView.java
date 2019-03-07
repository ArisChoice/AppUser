package com.app.barber.ui.postauth.activities.home.homemvp;

import com.app.barber.util.mvp.MvpView;

/**
 * Created by harish on 26/10/18.
 */

public interface HomeAuthMVPView<T> extends MvpView {
    void onSuccessResponse(int serviceMode, T t);

    void onfaliurResponse(int serviceMode, T t);
}
