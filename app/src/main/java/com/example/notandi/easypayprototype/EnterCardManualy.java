package com.example.notandi.easypayprototype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class EnterCardManualy extends Activity implements AdapterView.OnItemSelectedListener {


    Spinner spin1;
    Spinner spin2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_card_manualy);
        final String user = getIntent().getStringExtra("User_Name");
        final String address = getIntent().getStringExtra("Address");
        final String Amount = getIntent().getStringExtra("Amount");
        Button btnConfirm = (Button) findViewById(R.id.btnConfirm);
        TextView lblUser = (TextView) findViewById(R.id.lblUser);
        TextView lblAmount = (TextView) findViewById(R.id.lblAmmount);
        lblUser.setText(user);
        lblAmount.setText("Upph. " + Amount +" ISK.");

        spin1 = (Spinner) findViewById(R.id.SpinMonth);
        spin2 = (Spinner) findViewById(R.id.SpinYear);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.months , android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.years , android.R.layout.simple_spinner_dropdown_item);

        spin1.setAdapter(adapter);
        spin2.setAdapter(adapter2);
        spin1.setOnItemSelectedListener(this);
        spin2.setOnItemSelectedListener(this);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), PaymentResult.class);
                EditText txtCardNr = (EditText) findViewById(R.id.txtCardNr);
                //EditText txtExpDate = (EditText) findViewById(R.id.txtExpDate);
                //EditText txtCVS = (EditText) findViewById(R.id.txtCVS);
                String Card = txtCardNr.getText().toString();
                //String ExpDate = txtExpDate.getText().toString();
                //String Cvs = txtCVS.getText().toString();
                intent.putExtra("User_Name", user);
                intent.putExtra("Amount", Amount);
                intent.putExtra("Address", address);
                intent.putExtra("PaymentConfirmed", true);
                startActivityForResult(intent, 0);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_enter_card_manualy, menu);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        TextView myText = (TextView) view;
        //Toast.makeText(this, "you selected " + myText.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
