package com.app.barber.ui.postauth.activities.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.barber.R;
import com.app.barber.core.BaseActivity;
import com.app.barber.util.GlobalValues;
import com.app.barber.views.CustomTextView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 20/11/18.
 */

public class ShareActivity extends BaseActivity {
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.toolbar_common)
    Toolbar toolbarCommon;
    @BindView(R.id.share_whatsapp_btn)
    CustomTextView shareWhatsappBtn;
    @BindView(R.id.share_fb_btn)
    CustomTextView shareFbBtn;
    @BindView(R.id.share_email_btn)
    CustomTextView shareEmailBtn;
    private String messageText = "Trim Check  book your appointments with barbers.Install app from this url " + '\n' + GlobalValues.APPLICATION_PLAYSTORE_URL_CUSTOMER;

    @Override
    public int getLayoutId() {
        return R.layout.layout_share_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        txtTitleToolbar.setText(R.string.str_share_trim_check);

    }

    @OnClick({R.id.back_toolbar, R.id.img_edit, R.id.share_whatsapp_btn, R.id.share_fb_btn, R.id.share_email_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.img_edit:
                break;
            case R.id.share_whatsapp_btn:
                shareOnWhatsapp();
                break;
            case R.id.share_fb_btn:
                sharebyFacebook();
                break;
            case R.id.share_email_btn:
                shareByEmail();
                break;
        }
    }

    // To share app on facebook messenger
    public static final String GOOGLE_PLAY_STORE_URI = "http://play.google.com/store/apps/details?id=";
    public static final String FACEBOOK_MESSENGER_PACKAGE = "com.facebook.orca";

    private void sharebyFacebook() {
        Intent sendIntent = new Intent();
        /*
            String ACTION_SEND
                Activity Action: Deliver some data to someone else. Who the data is being delivered
                to is not specified; it is up to the receiver of this action to ask the
                user where the data should be sent.
        */
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                messageText
        );
        sendIntent.setType("text/plain");

        sendIntent.setPackage(FACEBOOK_MESSENGER_PACKAGE);
        try {
            startActivity(sendIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ShareActivity.this, "Please install facebook messenger.", Toast.LENGTH_LONG).show();
        }


    }

    private void shareByEmail() {
        String to = "";
        String subject = "TrimCheck";
        String message = messageText;


        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);

        //need this to prompts email client only
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void shareOnWhatsapp() {
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, messageText);
        try {
            Objects.requireNonNull(ShareActivity.this).startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.whatsapp")));
        }
    }

}
