package com.example.notandi.easypayprototype;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class PaymentResult extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_result);
        TextView businessAddress = (TextView) findViewById(R.id.lblBusinessAddress);
        TextView businessName = (TextView) findViewById(R.id.lblBusinessName);
        TextView PaydAmount = (TextView) findViewById(R.id.lblChargedAmount);
        TextView ReceivedPayment = (TextView) findViewById(R.id.lblPaymentRecived);
        final String user = getIntent().getStringExtra("User_Name");
        final String address = getIntent().getStringExtra("Address");
        final String Amount = getIntent().getStringExtra("Amount");
        boolean paymentReceived = getIntent().getBooleanExtra("PaymentConfirmed", false);
        businessAddress.setText(address);
        businessName.setText(user);
        PaydAmount.setText("Viðskipti ISK. "+ Amount);
        if(!paymentReceived)
        {
            ReceivedPayment.setBackgroundColor(Color.rgb(255,0,0));
            ReceivedPayment.setText("Payment was not Received");
        }
        Button btnReceiptYes = (Button) findViewById(R.id.btnReceiptYes);
        Button btnReceiptNo = (Button) findViewById(R.id.btnReceiptNo);
        btnReceiptNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), start.class);
                intent.putExtra("User_Name", user);
                startActivityForResult(intent, 0);
            }
        });

        btnReceiptYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), Receipt.class);
                intent.putExtra("User_Name", user);
                startActivityForResult(intent, 0);
            }
        });

        TextView home = (TextView) findViewById(R.id.PaymentResultHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), start.class);
                intent.putExtra("User_Name", user);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_payment_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
