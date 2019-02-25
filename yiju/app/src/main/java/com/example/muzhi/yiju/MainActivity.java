package com.example.muzhi.yiju;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    @Override
   protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

             webView = (WebView) findViewById(R.id.wv);
             //如果只写下面这一句，会提示无法访问
              //加载网页需要连接互联网的权限，需要在AndroidManifest中进行声明
                //这样的好处：可以让用户很清楚的看到app所需要的权限
               //用户并不清楚和重视权限问题，造成了病毒问题的泛滥。其实是可以避免的！
              webView.loadUrl("http://192.168.43.91");
         }
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // Context context;
        //Intent intent = new Intent(context,AppWidgetService.class);
        //ontext.startService(intent);
    }
    */
}
