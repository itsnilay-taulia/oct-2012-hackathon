package com.taulia.hackathon;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.taulia.hackathon.bo.Invoice;
import com.taulia.hackathon.data.EPInvItems;
import com.taulia.hackathon.rest.EarlyPaymentRestHelper;

/**
 * Created by IntelliJ IDEA.
 * User: nilaypatel
 * Date: 9/27/12
 * Time: 1:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListEPAvailItemsActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        EPInvItems epInvItems = new EPInvItems();
		setListAdapter(new ArrayAdapter(this, R.layout.ep_inv, EPInvItems.getEPInvItems()));
//		setListAdapter(new ArrayAdapter<Invoice>(this, R.layout.ep_inv, EarlyPaymentRestHelper.getListOfEarlyPayableInvoices()));
    }

   	public void onListItemClick( ListView parent, View v, int position, long id)  {
	    Intent intent = new Intent(ListEPAvailItemsActivity.this, DetailEPAvailItemActivity.class);
//	    Invoice epInvItem = (Invoice) this.getListAdapter().getItem(position);
	    String epInvItem = (String) this.getListAdapter().getItem(position);
//	    intent.putExtra("index", deptItem.getId()+"");
//        intent.putExtra("type", "dept");
        intent.putExtra("invNo", epInvItem);
        intent.putExtra("amnt", epInvItem);
        intent.putExtra("dueDate", epInvItem);
        intent.putExtra("buyer", epInvItem);
//        intent.putExtra("desc", deptItem.getSnippet());
//	    intent.putExtra("geolat", deptItem.getPoint().getLatitudeE6()+"");
//	    intent.putExtra("geolong", deptItem.getPoint().getLongitudeE6()+"");
	     startActivity(intent);
    }
}

//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        Bundle sendBundle = new Bundle();
//        sendBundle.putString("value", "value");
//        Intent intent = new Intent(ListEPAvailItemsActivity.this, MyActivity.class);
//        intent.putExtras(sendBundle);
//        startActivity(intent);
//    }
//}
