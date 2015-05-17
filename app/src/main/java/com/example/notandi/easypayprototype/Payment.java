package com.example.notandi.easypayprototype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Date;


public class Payment extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        TextView txtUser = (TextView) findViewById(R.id.lblUser);
        TextView txtAmount = (TextView) findViewById(R.id.lblPaymentAmount);
        final String user = getIntent().getStringExtra("User_Name");
        final String Amount = getIntent().getStringExtra("Amount");
        txtUser.setText(user);
        txtAmount.setText("Viðskipti ISK. " + Amount);
        ImageButton ContactlessCard = (ImageButton) findViewById(R.id.Card);
        ContactlessCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CardNr = "123456789";
                Date now = new Date();

                Intent intent = new Intent(v.getContext(), PaymentResult.class);
                intent.putExtra("User_Name", user);
                intent.putExtra("Amount", Amount);
                intent.putExtra("Address", "Strandgata 1337");
                intent.putExtra("PaymentConfirmed", true);
                startActivityForResult(intent, 0);
            }
        });
        Button btnManualCard = (Button) findViewById(R.id.btnManualCard);
        btnManualCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), EnterCardManualy.class);
                intent.putExtra("User_Name", user);
                intent.putExtra("Amount", Amount);
                intent.putExtra("Address", "Strandgata 1337");
                startActivityForResult(intent, 0);
            }
        });

        TextView home = (TextView) findViewById(R.id.CardManualHome);
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
        getMenuInflater().inflate(R.menu.menu_payment, menu);
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
