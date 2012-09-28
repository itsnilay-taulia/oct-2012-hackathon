package com.taulia.hackathon;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NotificationActivity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noti);
        
        final NotificationManager notiManager = (NotificationManager) 
        		getSystemService(Context.NOTIFICATION_SERVICE);
        
        final Notification noti = new Notification(R.drawable.ic_launcher, 
        		"New EP Invoice Available", System.currentTimeMillis());
        
        Intent intent = new Intent(this, ListEPAvailItemsActivity.class);
        PendingIntent destIntent = PendingIntent.getActivity(this, 0, intent, 0);

        noti.setLatestEventInfo(this, "New EP Invoice Available", "You have 1 new EP Invoice Available", destIntent);
        
        Button show1 = (Button)findViewById(R.id.show1);
        show1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {				
				notiManager.notify(1, noti);
			}
		});
        
        Button show2 = (Button)findViewById(R.id.show2);
        show2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				noti.defaults = Notification.DEFAULT_SOUND;
				notiManager.notify(2, noti);
			}
		});
        
        Button show3 = (Button)findViewById(R.id.show3);
        show3.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				noti.defaults |= Notification.DEFAULT_SOUND;
				noti.defaults |= Notification.DEFAULT_VIBRATE;
				notiManager.notify(3, noti);
			}
		});   
    }
}