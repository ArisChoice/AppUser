package com.app.barber.ui.postauth.activities;

import android.os.Bundle;
import android.view.View;

import com.app.barber.R;
import com.app.barber.core.BaseActivity;

import butterknife.OnClick;

/**
 * Created by harish on 16/10/18.
 */

public class VerificationActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_verification_screen;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    @OnClick({R.id.back_toolbar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;

        }
    }
}
