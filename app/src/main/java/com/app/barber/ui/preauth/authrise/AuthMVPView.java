package com.app.barber.ui.preauth.authrise;

import com.app.barber.util.mvp.MvpView;

/**
 * Created by harish on 26/10/18.
 */

public interface AuthMVPView<T> extends MvpView {
    void onSuccessResponse(int serviceMode, T t);

    void onfaliurResponse(int serviceMode, T t);
}
