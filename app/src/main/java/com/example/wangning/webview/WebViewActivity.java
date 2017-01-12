package com.example.wangning.webview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.example.wangning.R;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-01-12
 * @since JDK 1.8
 */
public class WebViewActivity extends Activity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(this, "nativeMethod");
        webView.loadUrl("file:///android_asset/index.html");

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
