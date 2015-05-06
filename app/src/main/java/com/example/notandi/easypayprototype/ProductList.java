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

/**
 * Created by erla on 06/05/15.
 */
public class ProductList extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        final String user = getIntent().getStringExtra("User_Name");
        final String Amount = getIntent().getStringExtra("Amount");
        ImageButton btnPlus = (ImageButton) findViewById(R.id.btnAdd);
        Button btnConfirm = (Button) findViewById(R.id.btnConfirmList);
        TextView lblUser = (TextView) findViewById(R.id.userProduct);
        TextView lblAmount = (TextView) findViewById(R.id.lblAmount2);
        lblUser.setText(user);
        lblAmount.setText("Viðskipti ISK. " + Amount);

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddProduct.class);
                intent.putExtra("Amount", Amount);
                intent.putExtra("User_Name", user);
                startActivityForResult(intent, 0);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Amount.class);
                intent.putExtra("Amount", Amount);
                intent.putExtra("User_Name", user);
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

