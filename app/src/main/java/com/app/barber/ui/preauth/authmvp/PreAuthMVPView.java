package com.app.barber.ui.preauth.authmvp;

import com.app.barber.util.mvp.MvpView;

/**
 * Created by harish on 26/10/18.
 */

public interface PreAuthMVPView<T> extends MvpView {
    void onSuccessResponse(int serviceMode, T t);

    void onfaliurResponse(int serviceMode,T t);
}
