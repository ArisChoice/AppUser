package com.app.barber.ui.postauth.activities.barber;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.core.BaseActivity;
import com.app.barber.views.CustomTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 15/11/18.
 */

public class EditReviewActivity extends BaseActivity {
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.submit_btn)
    CustomTextView submitBtn;

    @Override
    public int getLayoutId() {
        return R.layout.layout_edit_review;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        txtTitleToolbar.setText(R.string.str_review);
    }

    @OnClick({R.id.back_toolbar, R.id.submit_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.submit_btn:
                break;
        }
    }
}
