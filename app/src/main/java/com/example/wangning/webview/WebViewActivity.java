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
import android.widget.Toast;

import com.example.wangning.R;
import com.example.wangning.ToastUtils;

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
        //webView.addJavascriptInterface(this, "nativeMethod");
        //webView.loadUrl("file:///android_asset/index.html");
        webView.loadUrl("https://owner.fanjianhome.com/fjuser/");
        //webView.loadUrl("http://172.16.19.98:9000/ovu-park-wx/activity-detail.html?activityId=8e471bc0dc1a43d19f5e6f46831b6904");

        /*        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.e(TAG, "onPageStarted: " );
            }



            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e(TAG, "onPageFinished: view.title="+view.getTitle()+",url="+url );
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


        });*/
    }

    @JavascriptInterface
    public void toActivity(String activityName) {
        //此处应该定义常量对应，同时提供给web页面编写者
        if(TextUtils.equals(activityName, "a")){
            ToastUtils.show(getApplicationContext(),"AAAA");
        }else{
            ToastUtils.show(getApplicationContext(),"BBBBBBBBBBBBBB");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }
}
