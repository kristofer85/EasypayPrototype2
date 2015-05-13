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


public class Access extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);
        final String[] AccessCodes = new String[10];
        AccessCodes[0] = "12345";
        AccessCodes[1] = "11111";
        AccessCodes[2] = "22222";
        AccessCodes[3] = "33333";
        AccessCodes[4] = "44444";
        AccessCodes[5] = "55555";
        AccessCodes[6] = "66666";
        AccessCodes[7] = "77777";
        AccessCodes[8] = "88888";
        AccessCodes[9] = "99999";
        Button btnAccess = (Button) findViewById(R.id.btnAccess);
        btnAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText AccessCode = (EditText) findViewById(R.id.txtAccess);
                boolean activeCode = false;
                TextView LoginResult = (TextView) findViewById(R.id.lblWrongAccess);
                String Code = AccessCode.getText().toString();

                for(int i =0; i < 10; i++)
                {
                    if(AccessCodes[i].equals(Code))
                    {
                        activeCode = true;
                    }
                }

                if(activeCode)
                {
                    Intent intent = new Intent(v.getContext(), start.class);
                    intent.putExtra("User_Name","Fyrirtæki ehf.");
                    startActivityForResult(intent, 0);
                }
                else
                {
                    LoginResult.setText(Code +" is not a valid access code try again");
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_access, menu);
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
