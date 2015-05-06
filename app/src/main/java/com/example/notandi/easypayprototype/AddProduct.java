package com.example.notandi.easypayprototype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by erla on 06/05/15.
 */
public class AddProduct extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        final String user = getIntent().getStringExtra("User_Name");
        final String Amount = getIntent().getStringExtra("Amount");
        Button btnConfirm = (Button) findViewById(R.id.btnMakeProduct);
        TextView lblUser = (TextView) findViewById(R.id.addProductUser);
        lblUser.setText(user);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (view.getContext(), ProductList.class);
                intent.putExtra("User_Name", user);
                intent.putExtra("Amount", Amount);
                EditText txtNewProduct = (EditText) findViewById(R.id.txtProductName);
                EditText txtNewProductPrice = (EditText) findViewById(R.id.txtProductPrice);
                String Name = txtNewProduct.getText().toString();
                String Price = txtNewProductPrice.getText().toString();
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