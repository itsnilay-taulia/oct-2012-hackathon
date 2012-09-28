package com.taulia.hackathon;

import android.app.Activity;
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
		Intent intent = new Intent(this, ListEPAvailItemsActivity.class);
//		intent.putExtra("from", "mapitbutton");
//		intent.putExtra("index", index);
//        intent.putExtra("type", type);
//		intent.putExtra("geolat", geolat);
//		intent.putExtra("geolong", geolong);
		startActivity(intent);
		finish();
	}

}
