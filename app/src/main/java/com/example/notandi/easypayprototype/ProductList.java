package com.example.notandi.easypayprototype;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by erla on 06/05/15.
 */
public class ProductList extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        final String user = getIntent().getStringExtra("User_Name");

    }
}
