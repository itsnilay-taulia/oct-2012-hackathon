package com.taulia.hackathon;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by IntelliJ IDEA.
 * User: nilaypatel
 * Date: 9/27/12
 * Time: 5:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConfirmActivity extends Activity{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm);
    }

    public void goBack(View view){

         final NotificationManager notiManager = (NotificationManager)
        		getSystemService(Context.NOTIFICATION_SERVICE);

        final Notification noti = new Notification(R.drawable.ic_launcher,
        		"New EP Invoice Available", System.currentTimeMillis());

        Intent intent = new Intent(this, ListEPAvailItemsActivity.class);
        PendingIntent destIntent = PendingIntent.getActivity(this, 0, intent, 0);

        noti.setLatestEventInfo(this, "New EP Invoice Available", "You have 1 new EP Invoice Available", destIntent);

        notiManager.notify(1, noti);

//		Intent intent = new Intent(this, NotificationActivity.class);
//		intent.putExtra("from", "mapitbutton");
//		intent.putExtra("index", index);
//        intent.putExtra("type", type);
//		intent.putExtra("geolat", geolat);
//		intent.putExtra("geolong", geolong);
//		startActivity(intent);
		finish();

	}

}
