package com.example.notandi.easypayprototype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erla on 06/05/15.
 */
public class ProductList extends Activity {

    List<ProductRecord> mList = new ArrayList();
    GridView mGridView;
    TextView mTextView;
    String Amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        final String user = getIntent().getStringExtra("User_Name");
        Amount = getIntent().getStringExtra("Amount");
        ImageButton btnPlus = (ImageButton) findViewById(R.id.btnAdd);
        Button btnConfirm = (Button) findViewById(R.id.btnConfirmList);
        TextView lblUser = (TextView) findViewById(R.id.userProduct);
        final TextView lblAmount = (TextView) findViewById(R.id.lblAmount2);
        lblUser.setText(user);
        if(Amount.isEmpty()) {
            Amount = "0";
        }
        lblAmount.setText("Viðskipti ISK. " + Amount);

        mList.add(new ProductRecord("Blái Naglinn", 1000));
        mList.add(new ProductRecord("WC Pappír", 2500));
        mList.add(new ProductRecord("Eldhúsrúllur", 3000));
        mList.add(new ProductRecord("Neyðarkall", 1000));
        mList.add(new ProductRecord("Annað", 10000));
        mList.add(new ProductRecord("Enn Annað", 20000));
        mList.add(new ProductRecord("Annað", 10000));
        mList.add(new ProductRecord("Enn Annað", 20000));
        mList.add(new ProductRecord("Annað", 30000));
        mList.add(new ProductRecord("Enn Annað", 40000));
        mList.add(new ProductRecord("Annað", 10000));
        mList.add(new ProductRecord("Enn Annað", 20000));
        mList.add(new ProductRecord("Enn Annað", 20000));
        mList.add(new ProductRecord("Enn Annað", 20000));
        mList.add(new ProductRecord("Enn Annað", 20000));

        mGridView = (GridView) findViewById(R.id.products);

        ArrayAdapter<ProductRecord> adapter = new ArrayAdapter<ProductRecord>(this, R.layout.activity_grid_cell, android.R.id.text1, mList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                ProductRecord record = mList.get(position);
                text1.setText(record.getName());
                text2.setText(Integer.toString(record.getPrice()) + " ISK");
                return view;
            }
        };

        mGridView.setAdapter(adapter);

        mGridView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ProductRecord record = mList.get(position);
                        Integer total = Integer.parseInt(Amount);
                        total = total + record.getPrice();
                        lblAmount.setText("Viðskipti ISK. " + total.toString());
                        Amount = total.toString();
                        Toast.makeText(getApplicationContext(), record.getName() + " bætt við heildarverð", Toast.LENGTH_SHORT).show();
                    }
                }
        );

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

