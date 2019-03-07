package com.app.barber.ui.postauth.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.core.BaseActivity;
import com.app.barber.views.CustomEditText;
import com.app.barber.views.CustomTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by harish on 16/10/18.
 */

public class UpdateDataActivity extends BaseActivity {
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.txt_field_name)
    CustomTextView txtFieldName;
    @BindView(R.id.edtxt_field)
    CustomEditText edtxtField;
    @BindView(R.id.txt_submit)
    CustomTextView txtSubmit;

    @Override
    public int getLayoutId() {
        return R.layout.activity_updatedata_screen;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_toolbar, R.id.txt_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.txt_submit:
                break;
        }
    }
}
