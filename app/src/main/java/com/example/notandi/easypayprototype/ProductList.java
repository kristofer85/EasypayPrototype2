package com.example.notandi.easypayprototype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        TextView lblUser = (TextView) findViewById(R.id.userProduct);
        TextView lblAmount = (TextView) findViewById(R.id.lblAmount2);
        lblUser.setText(user);
        lblAmount.setText(Amount + " ISK");

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (view.getContext(), ProductList.class);
            }
        });
    }
}
