package com.example.notandi.easypayprototype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Receipt extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
        final String user = getIntent().getStringExtra("User_Name");


        final Button btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
        final Button btnSendEmail = (Button) findViewById(R.id.btnSendEmail);


        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                EditText txtPhone = (EditText) findViewById(R.id.txtPhone);
                String SMS = txtPhone.getText().toString();
                Intent intent = new Intent(v.getContext(), ReceiptSent.class);
                intent.putExtra("User_Name", user);
                intent.putExtra("ReceiptType", "SMS");
                intent.putExtra("ReceiptValue", SMS);
                startActivityForResult(intent, 0);
            }
        });
        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
                String Email = txtEmail.getText().toString();
                Intent intent = new Intent(v.getContext(), ReceiptSent.class);
                intent.putExtra("User_Name", user);
                intent.putExtra("ReceiptType", "Email");
                intent.putExtra("ReceiptValue", Email);
                startActivityForResult(intent, 0);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_receipt, menu);
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
