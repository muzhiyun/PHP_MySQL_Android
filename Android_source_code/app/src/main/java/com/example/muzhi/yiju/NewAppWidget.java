package com.example.muzhi.yiju;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {
    //public static final String CLICK_ACTION = "app_widget_provider";
    public static final String ACTION_MAKE_NUMBER = "app_widget_provider";
    // public static final String AppWidgetService = "AppWidgetService";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        //Intent intent = new Intent(CLICK_ACTION);
        //PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        //remoteViews.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);

        Intent numberIntent = new Intent(ACTION_MAKE_NUMBER);
        remoteViews.setOnClickPendingIntent(R.id.appwidget_text, PendingIntent.getBroadcast(context, 0, numberIntent, 0));
        //Jsondecode jsondecode = new Jsondecode();
        //jsondecode.httpget();
        Log.d("TAG", "发送广播");
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        // Toast.makeText(context, "广播已发送", Toast.LENGTH_SHORT).show();
      /*
        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

        */
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d("TAG", "onUpdate方法调用了");



        //Intent intent = new Intent(context,AppWidgetService.class);
        //context.startService(intent);
        //Toast.makeText(context, "onUpdate被调用", Toast.LENGTH_SHORT).show();
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        super.onEnabled(context);
        //启动MyService
        //Toast.makeText(context, "onEnable方法被调用", Toast.LENGTH_SHORT).show();
        Log.d("TAG", "onEnable方法调用了");
        Intent intent = new Intent(context, AppWidgetService.class);
        context.startService(intent);

    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        //删除AppWidget的时候调用
        super.onDeleted(context, appWidgetIds);
        System.out.println("onDeleted");
        Intent intent = new Intent(context, AppWidgetService.class);
        context.startService(intent);
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
        super.onDisabled(context);
        //停止MyService
        Log.d("TAG", "onDisable方法调用了");
        Intent intent = new Intent(context, AppWidgetService.class);
        context.stopService(intent);

    }

    /**
     * 接收窗口小部件点击时发送的广播
     */


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        //AppWidgetService appWidgetService = new AppWidgetService();
        if (intent.getAction().equals(ACTION_MAKE_NUMBER)) {
            //Toast.makeText(context, "小北你好!", Toast.LENGTH_SHORT).show();
            Intent intent2 = new Intent(context, AppWidgetService.class);
            context.startService(intent2);
            // appWidgetService.onCreate();
        }


    }
}