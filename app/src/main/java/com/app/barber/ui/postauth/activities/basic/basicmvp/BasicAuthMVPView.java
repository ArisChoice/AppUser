package com.app.barber.ui.postauth.activities.basic.basicmvp;

import com.app.barber.util.mvp.MvpView;

/**
 * Created by harish on 26/10/18.
 */

public interface BasicAuthMVPView<T> extends MvpView {
    void onSuccessResponse(int serviceMode, T t);

    void onfaliurResponse(int serviceMode, T t);
}
