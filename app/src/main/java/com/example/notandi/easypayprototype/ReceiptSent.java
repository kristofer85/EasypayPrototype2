package com.example.notandi.easypayprototype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ReceiptSent extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_sent);
        TextView lblUser = (TextView) findViewById(R.id.lblUser);
        TextView lblReceiptMsg = (TextView) findViewById(R.id.lblReceiptMsg);
        Button btnFinalize = (Button) findViewById(R.id.btnFinalize);
        final String user = getIntent().getStringExtra("User_Name");
        final String ReceiptType = getIntent().getStringExtra("ReceiptType");
        final String ReceiptValue = getIntent().getStringExtra("ReceiptValue");
        lblUser.setText(user);
        if(ReceiptType.equals("SMS"))
        {
            lblReceiptMsg.setText("Kvittun hefur verið send á símanúmerið: "+ ReceiptValue);
        }
        else if(ReceiptType.equals("Email"))
        {
            lblReceiptMsg.setText("Kvittun hefur verið send á netfangið: "+ ReceiptValue);
        }
        btnFinalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), Amount.class);
                intent.putExtra("User_Name", user);
                startActivityForResult(intent, 0);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_receipt_sent, menu);
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
