package com.app.barber.ui.postauth.activities.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.app.barber.R;
import com.app.barber.core.BaseActivity;
import com.app.barber.ui.postauth.activities.settings.support_chat.SupportChatActivity;
import com.app.barber.util.CommonUtils;
import com.app.barber.util.GlobalValues;
import com.app.barber.views.CustomTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 12/11/18.
 */

public class SupportActivity extends BaseActivity {
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.toolbar_common)
    Toolbar toolbarCommon;
    @BindView(R.id.support_chat_btn)
    CustomTextView supportChatBtn;
    @BindView(R.id.call_btn)
    CustomTextView callBtn;
    @BindView(R.id.help_btn)
    CustomTextView helpBtn;

    @Override
    public int getLayoutId() {
        return R.layout.layout_support_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        txtTitleToolbar.setText(R.string.str_support);
    }

    @OnClick({R.id.back_toolbar, R.id.support_chat_btn, R.id.call_btn, R.id.help_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.support_chat_btn:
                Intent intent = new Intent(SupportActivity.this, SupportChatActivity.class);
                intent.putExtra(GlobalValues.KEYS.EXTRA_DIALOG_ID, new CommonUtils().getSupportChatDialog(getUserData()));
                intent.putExtra(GlobalValues.KEYS.OTHER_IMAGE, "");
                intent.putExtra(GlobalValues.KEYS.USER_ID, "" + GlobalValues.SUPPORT_ADMIN_ID);
                startActivity(intent);
                break;
            case R.id.call_btn:
                break;
            case R.id.help_btn:
                break;
        }
    }
}
