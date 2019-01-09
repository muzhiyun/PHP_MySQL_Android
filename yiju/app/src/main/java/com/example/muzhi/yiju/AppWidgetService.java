package com.example.muzhi.yiju;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AppWidgetService extends Service{

    public static final String ACTION_MAKE_NUMBER = "app_widget_provider";

/*
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        flags = START_STICKY;
        return super.onStartCommand(intent, flags, startId);
    }
*/
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    MyReceiver receiver;
    @Override
    public void onCreate() {
        super.onCreate();
        //动态注册广播接收器
        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        Log.d("TAG","动态注册广播接收器");
        filter.addAction(ACTION_MAKE_NUMBER);
        registerReceiver(receiver, filter);
    }
    @Override
    public void onDestroy() {
        //注销广播接收器
        unregisterReceiver(receiver);
        Log.d("TAG","注销广播接收器");
        super.onDestroy();
    }

    public class GetExample {
        OkHttpClient client = new OkHttpClient();

        String run(String url) throws IOException {
            Log.d("TAG","url是  ");
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Log.d("TAG",url);
            try (Response response = client.newCall(request).execute()) {
                Log.d("TAG","获取的  "+response.body().string());
                return response.body().string();
            }catch (Exception e)
            {
                //Log.d("TAG","连接失败");
                return "连接失败";
            }
        }

/*
         String okhttp() throws IOException {
            GetExample example = new GetExample();
             Log.d("TAG", "okhttp");
            String response = example.run("http://192.168.11.100/yiju/index.php?m=json");
            System.out.println(response);
            Log.d("TAG", response);
            return response;
        }
*/
    }

    /**
     * 广播接收器
     */
    public class MyReceiver extends BroadcastReceiver {


        // 接收到Widget发送的广播
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("TAG","接收器已收到");
            //Toast.makeText(context, "接收到已刷新", Toast.LENGTH_SHORT).show();
            //receiver = new MyReceiver();
            //IntentFilter filter = new IntentFilter();
            //Log.d("TAG",context+"");
            //filter.addAction(ACTION_MAKE_NUMBER);
            //registerReceiver(receiver, filter);
            Toast.makeText(context, "接收到已刷新", Toast.LENGTH_SHORT).show();
            //Log.d("TAG",context+"");
            if (ACTION_MAKE_NUMBER.equals(intent.getAction())) {
                // 生成一个随机数字，以系统广播的形式将这个数字提交到
                //Toast.makeText(context, "小北你好!", Toast.LENGTH_SHORT).show();
                AppWidgetManager manager = AppWidgetManager.getInstance(context);
                ComponentName provider = new ComponentName(context,NewAppWidget.class);
                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
                //设置要显示的TextView，及显示的内容
                try {
                    //Log.d("TAG","进来了");
                    GetExample example = new GetExample();
                    String next_text = example.run("http://127.0.0.1/yiju/index.php?m=json");
                    //Log.d("TAG","得到了");
                    //Toast.makeText(context, next_text, Toast.LENGTH_SHORT).show();
                    Log.d("TAG","拿到回复 内容是  "+ next_text);

                    //Toast.makeText(context, "小北你好!", Toast.LENGTH_SHORT).show();
                    views.setTextViewText(R.id.appwidget_text, next_text);
                    // 发送一个系统广播
                    manager.updateAppWidget(provider, views);
                }catch (Exception e)
            {
                views.setTextViewText(R.id.appwidget_text, "服务器未连接");
                // 发送一个系统广播
                manager.updateAppWidget(provider, views);
            }

            }
        }

    }

}

