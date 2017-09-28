package com.example.sy001.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by dengyibin on 2016/12/19.
 */

public class WebViewActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();
        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl("file:///android_asset/111.html");
        Log.d("WebViewActivity", "onCreate: ");
//        findViewById(R.id.btn_click_me).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("WebViewActivity", "onClick: ");
//                webView.loadUrl("http://192.9.8.205:8887");
//            }
//        });

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            WebSettings settings = view.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setDomStorageEnabled(true);
            if (url.startsWith("http:") || url.startsWith("https:")) {
                view.loadUrl(url);
            }
            return super.shouldOverrideUrlLoading(view,url);
        }
    }
}
