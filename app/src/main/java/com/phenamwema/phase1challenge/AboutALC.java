package com.phenamwema.phase1challenge;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AboutALC extends AppCompatActivity {
    private WebView webView;
    private String url = "https://andela.com/alc";
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_alc);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//display back navigation in toolbar

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        webView = (WebView) findViewById(R.id.webview);

        WebSettings webSettings = webView.getSettings();//get webview settings
        webSettings.setJavaScriptEnabled(true);//enable  javascript for the webview
        webView.setWebViewClient(new MyWebViewClient());//set webview client
        webView.loadUrl(url);//load url of webpage
    }



    private class MyWebViewClient extends WebViewClient{

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);//show progressbar while webpage loads
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);//load webpage
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);//on completion of webpage load, hide progressbar
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            //super.onReceivedSslError(view, handler, error);
            handler.proceed();//handles Ssl errors that prevent page from loading
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            //show alert dialog when there is no internet connection
            AlertDialog alertDialog = new AlertDialog.Builder(AboutALC.this).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Please check your internet connection.");
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Try again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                    startActivity(getIntent());
                }
            });
            alertDialog.show();
            alertDialog.setCanceledOnTouchOutside(false);
            super.onReceivedError(view, errorCode, description, failingUrl);
            return;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
