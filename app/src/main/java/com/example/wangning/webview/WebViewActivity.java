package com.example.wangning.webview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.wangning.R;

import javax.security.auth.login.LoginException;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-01-12
 * @since JDK 1.8
 */
public class WebViewActivity extends Activity {
    private static final String TAG = "WebViewActivity";
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(this, "nativeMethod");
        //webView.loadUrl("file:///android_asset/index.html");
        webView.loadUrl("https://www.baidu.com");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.e(TAG, "onPageStarted: " );
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e(TAG, "onPageFinished: view="+view+",url="+url );
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Log.e(TAG, "onPageFinished:error="+error );
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
                Log.e(TAG, "onPageFinished:errorResponse="+errorResponse );
            }


        });
    }

    @JavascriptInterface
    public void toActivity(String activityName) {
        //此处应该定义常量对应，同时提供给web页面编写者
        if(TextUtils.equals(activityName, "a")){
            startActivity(new Intent(this,AActivity.class));
        }else{
            startActivity(new Intent(this,BActivity.class));
        }
    }


}