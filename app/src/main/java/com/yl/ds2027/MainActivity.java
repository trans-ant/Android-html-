package com.yl.ds2027;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mbtn1, mbtn2;
    private WebView contentWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentWebView = (WebView) findViewById(R.id.webview);
        //启用javascript
        contentWebView.getSettings().setJavaScriptEnabled(true);
        //从assets目录下面加载html
        contentWebView.loadUrl("file:///android_asset/web.html");
        contentWebView.addJavascriptInterface(MainActivity.this, "android");

        //Button按钮 无参调用HTML js方法
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                contentWebView.loadUrl("javascript:javacalljswish("+"'http://blog.csdn.net/Leejizhou'"+")");
            }
        });




        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //传递参数调用JS的方法
                contentWebView.loadUrl("javascript:javacalljswith(" + "'http://blag.csdn.net/Leejizhou'" + ")");
            }


        });
    }
    //由于安全原因 targetSdkVersion>=17需要加 @JavascriptInterface
    //JS调用Android JAVA方法名和HTML中的按钮 onclick后的别名后面的名字对应
    @JavascriptInterface
        public void startFunction(){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                        Toast.makeText(MainActivity.this,"show",Toast.LENGTH_LONG).show();
                }
            });
        }

    @JavascriptInterface
    public void startFunction(final String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(MainActivity.this).setMessage(text).show();
            }
        });
    }
}