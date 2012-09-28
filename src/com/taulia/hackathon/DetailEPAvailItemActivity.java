package com.taulia.hackathon;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import com.taulia.hackathon.bo.Invoice;
import com.taulia.hackathon.rest.EarlyPaymentRestHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: nilaypatel
 * Date: 9/27/12
 * Time: 3:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class DetailEPAvailItemActivity extends Activity {


	private TextView tvDisplayDate;
	private DatePicker dpResult;
	private Button btnChangeDate;
    private Bundle epInvBundle;

	private int year;
	private int month;
	private int day;
    static final int DATE_DIALOG_ID = 999;


     public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.desc);
        epInvBundle = getIntent().getExtras();
        String invNo = "Invoice:" + epInvBundle.getString("invNo");
        String amnt = "Amount:" + epInvBundle.getString("amnt");
        String dueDate = "Due Date:" + epInvBundle.getString("dueDate");
        String buyer = "Buyer:" + epInvBundle.getString("buyer");
        String dscAmnt = "Discount Amount:";
        String dscPerc = "Discount Percentage:";
//        geolat = cafeBundle.getString("geolat");
//        geolong = cafeBundle.getString("geolong");
//        index = cafeBundle.getString("index");
//        type = cafeBundle.getString("type");
        TextView tv = (TextView) findViewById(R.id.invNoTextView);
        tv.setText(invNo);
         TextView tv1 = (TextView) findViewById(R.id.amtTextView);
        tv1.setText(amnt);
         TextView tv2 = (TextView) findViewById(R.id.dueDateTextView);
        tv2.setText(dueDate);
         TextView tv3 = (TextView) findViewById(R.id.buyerTextView);
        tv3.setText(buyer);
         TextView tv4 = (TextView) findViewById(R.id.dscAmtTextView);
        tv4.setText(dscAmnt);
         TextView tv5 = (TextView) findViewById(R.id.dscPerTextView);
        tv5.setText(dscPerc);

         setCurrentDateOnView();
		addListenerOnButton();

	}

    private void calculateDiscount() {
        DateFormat formatter ;
         Date dueDate_date = new Date();
        Date epDate_date = new Date();
        btnChangeDate = (Button) findViewById(R.id.btnChangeDate);
        String epDate = btnChangeDate.getText().toString();
        String amnt = epInvBundle.getString("amnt").toString();
        Double amount = Double.parseDouble(amnt);
        String dueDate = epInvBundle.getString("dueDate").toString();
        try{

          formatter = new SimpleDateFormat("mm-dd-yy");
          dueDate_date = (Date)formatter.parse(dueDate);
          epDate_date = (Date)formatter.parse(epDate);
//         System.out.println("Today is " +date );
          } catch (ParseException e)
          {System.out.println("Exception :"+e);
          }

        int diff_date =
                (int) ((dueDate_date.getTime() - epDate_date.getTime())
                                 / (1000 * 60 * 60 * 24));

         java.util.Random generator = new java.util.Random();
         int MIN = 100;
         int MAX = 200;
        int randomNumber = generator.nextInt(MAX - MIN) + MIN;

        String dscAmnt = "Discount Amount:  $"+ randomNumber;
        String dscPerc = "Discount Percentage: "+(randomNumber/5);
        TextView tv4 = (TextView) findViewById(R.id.dscAmtTextView);
        tv4.setText(dscAmnt);
         TextView tv5 = (TextView) findViewById(R.id.dscPerTextView);
        tv5.setText(dscPerc);


    }


    public void submitIt(View view){
        String invNo =  epInvBundle.getString("invNo");
        btnChangeDate = (Button) findViewById(R.id.btnChangeDate);
        String epDate = btnChangeDate.getText().toString();
        EarlyPaymentRestHelper.submitEarlyPaymentRequest(invNo,epDate);
		Intent intent = new Intent(this, ConfirmActivity.class);
//		intent.putExtra("from", "mapitbutton");
//		intent.putExtra("index", index);
//        intent.putExtra("type", type);
//		intent.putExtra("geolat", geolat);
//		intent.putExtra("geolong", geolong);
		startActivity(intent);
		finish();
	}

// display current date
public void setCurrentDateOnView() {

    tvDisplayDate = (TextView) findViewById(R.id.tvDate);
    dpResult = (DatePicker) findViewById(R.id.dscDateDP);
    btnChangeDate = (Button) findViewById(R.id.btnChangeDate);

    final Calendar c = Calendar.getInstance();
    year = c.get(Calendar.YEAR);
    month = c.get(Calendar.MONTH);
    day = c.get(Calendar.DAY_OF_MONTH);

    // set current date into textview
    tvDisplayDate.setText(new StringBuilder()
        .append("Payment Date: "));
    btnChangeDate.setText(new StringBuilder().append("Select Date..."));
        // Month is 0 based, just add 1
//        .append(month + 1).append("-").append(day).append("-")
//        .append(year).append(" "));

    // set current date into datepicker
    dpResult.init(year, month, day, null);


}

public void addListenerOnButton() {

    btnChangeDate = (Button) findViewById(R.id.btnChangeDate);

    btnChangeDate.setOnClickListener(new View.OnClickListener() {

        public void onClick(View v) {

            showDialog(DATE_DIALOG_ID);

        }

    });

}

@Override
protected Dialog onCreateDialog(int id) {
    switch (id) {
    case DATE_DIALOG_ID:
       // set date picker as current date
       return new DatePickerDialog(this, datePickerListener,
                     year, month,day);
    }
    return null;
}

private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

    // when dialog box is closed, below method will be called.
    public void onDateSet(DatePicker view, int selectedYear,
            int selectedMonth, int selectedDay) {
        year = selectedYear;
        month = selectedMonth;
        day = selectedDay;

        tvDisplayDate.setText(new StringBuilder()
        .append("Payment Date: "));
        // set selected date into textview
        btnChangeDate.setText(new StringBuilder().append(month + 1)
                .append("-").append(day).append("-").append(year)
                .append(" "));

        // set selected date into datepicker also
        dpResult.init(year, month, day, null);
        calculateDiscount();

    }
};

}
