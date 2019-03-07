package com.app.barber.ui.postauth.activities.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.app.barber.R;
import com.app.barber.core.BaseActivity;
import com.app.barber.net.NetworkConstatnts;
import com.app.barber.util.FunctionalDialog;
import com.app.barber.util.GlobalValues;
import com.app.barber.views.CustomTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by harish on 19/11/18.
 */

public class BrowserActivity extends BaseActivity {
    @BindView(R.id.back_toolbar)
    ImageView backToolbar;
    @BindView(R.id.txt_title_toolbar)
    CustomTextView txtTitleToolbar;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    @BindView(R.id.toolbar_common)
    Toolbar toolbarCommon;
    @BindView(R.id.web_browser)
    WebView webBrowser;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private String url;

    @Override
    public int getLayoutId() {
        return R.layout.layout_browser_screen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntertData(getIntent());
    }

    private void getIntertData(Intent intent) {
        try {
            txtTitleToolbar.setText(intent.getStringExtra(GlobalValues.KEYS.TITLE));
        } catch (Exception e) {

        }
        webBrowser.getSettings().setLoadsImagesAutomatically(true);
        webBrowser.getSettings().setJavaScriptEnabled(true);
        webBrowser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        if (intent.getStringExtra(GlobalValues.KEYS.TITLE).equals(GlobalValues.KEYS.WEBSITE)) {
            url = NetworkConstatnts.API.WEBSITE;
        } else if (intent.getStringExtra(GlobalValues.KEYS.TITLE).equals(GlobalValues.KEYS.TERMS))
            url = NetworkConstatnts.API.TERMS;
        else if (intent.getStringExtra(GlobalValues.KEYS.TITLE).equals(GlobalValues.KEYS.PRIVACY))
            url = NetworkConstatnts.API.TERMS;

        webBrowser.loadUrl(url);
        webBrowser.setWebViewClient(new MyBrowser(progressBar));
    }

    private class MyBrowser extends WebViewClient {
        ProgressBar progressBar;

        public MyBrowser(ProgressBar progressBar) {
            this.progressBar = progressBar;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.back_toolbar, R.id.img_edit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toolbar:
                onBackPressed();
                break;
            case R.id.img_edit:
                break;
        }
    }
}
