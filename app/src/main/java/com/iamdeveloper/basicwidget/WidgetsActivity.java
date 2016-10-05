package com.iamdeveloper.basicwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Random;

public class WidgetsActivity extends AppWidgetProvider {

           @Override
        public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        int currentWidgetId = appWidgetIds.length;
        for(int i = 0; i < currentWidgetId;i++){
            Log.i("onUpdate","onUpdate"+currentWidgetId);
            int widgetId = appWidgetIds[i];
            Log.i("currentWidgetId",""+widgetId);
            String number = String.format("%03d",new Random().nextInt(100));
            RemoteViews remoteView = new RemoteViews
                    (context.getPackageName(),R.layout.activity_widgets);
            remoteView.setTextViewText(R.id.text,number);

            Intent intent = new Intent(context,WidgetsActivity.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getBroadcast
                    (context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            remoteView.setOnClickPendingIntent(R.id.btn_play,pendingIntent);

            appWidgetManager.updateAppWidget(widgetId,remoteView);

        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.i("onDeleted",""+appWidgetIds.length);
    }
}
