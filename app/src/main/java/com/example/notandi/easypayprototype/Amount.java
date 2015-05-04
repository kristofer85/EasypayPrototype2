package com.example.notandi.easypayprototype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Amount extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount);
        TextView txtUser = (TextView) findViewById(R.id.lblUser);
        Button btnConfirm = (Button) findViewById(R.id.btnConfirm);
        final String user = getIntent().getStringExtra("User_Name");
        txtUser.setText("User: "+ user);
        final EditText CurAmount = (EditText) findViewById(R.id.txtAmount);
        CurAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                TextView welcomeTxt = (TextView) findViewById(R.id.welcomeTxt);
                Button btnConfirm = (Button) findViewById(R.id.btnConfirm);

                welcomeTxt.setVisibility(View.GONE);
                btnConfirm.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String pay = CurAmount.getText().toString();
                Intent intent = new Intent(v.getContext(), Payment.class);
                intent.putExtra("Amount", pay);
                intent.putExtra("User_Name", user);
                startActivityForResult(intent, 0);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_amount, menu);
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
