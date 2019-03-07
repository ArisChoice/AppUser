package com.app.barber.ui.postauth.activities.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.core.BaseActivity;
import com.app.barber.util.GlobalValues;
import com.app.barber.views.CustomTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 3/1/19.
 */

public class InviteBarberActivity extends BaseActivity {
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.toolbar_common)
    Toolbar toolbarCommon;
    @BindView(R.id.share_btn)
    CustomTextView shareBtn;

    /**
     * @return layout resource id
     */
    @Override
    public int getLayoutId() {
        return R.layout.layout_invite_barber_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        txtTitleToolbar.setText(R.string.str_invite_barbers);
    }

    @OnClick({R.id.back_toolbar, R.id.share_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.share_btn:
                initShare(getString(R.string.str_user_below_code_in_registration) + " " + "WRQRRQQTQT");
                break;
        }
    }
    public void initShare(String sText) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, sText);
        share.putExtra(Intent.EXTRA_TEXT, sText + '\n' + " Install TrimCheck Barber: " + GlobalValues.APPLICATION_PLAYSTORE_URL_BARBER);
        startActivity(Intent.createChooser(share, "Share TrimCheck"));
    }
}
