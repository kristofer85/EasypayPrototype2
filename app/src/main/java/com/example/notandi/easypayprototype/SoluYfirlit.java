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
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class SoluYfirlit extends Activity {

    public static List<Fearsla> mList = new ArrayList();
    public static int tagIncrementer = 1;
    GridView mGridView;
    ArrayAdapter<Fearsla> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solu_yfirlit);
        mList.add(new Fearsla(134, "B\u00f3nus", "19/03/2015", 500));
        tagIncrementer++;
        mList.add(new Fearsla(174,"Fyrirt\u00e6ki ehf","27/03/2015",7300));
        tagIncrementer++;
        mList.add(new Fearsla(234, "Fyrirt\u00e6ki ehf", "19/09/2016", 13500));
        tagIncrementer++;
        mList.add(new Fearsla(34,"Fyrirt\u00e6ki ehf","19/03/2016",2500));
        tagIncrementer++;
        mList.add(new Fearsla(74,"Fyrirt\u00e6ki ehf","19/02/2016",5000));
        tagIncrementer++;
        final String user = getIntent().getStringExtra("User_Name");

        mGridView = (GridView) findViewById(R.id.faerslur);

        this.adapter = new ArrayAdapter<Fearsla>(this, R.layout.activity_faersla, R.id.FN, mList)
        {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(R.id.FN);
                TextView text2 = (TextView) view.findViewById(R.id.FF);
                TextView text3 = (TextView) view.findViewById(R.id.FD);
                TextView text4 = (TextView) view.findViewById(R.id.FV);
                //TextView text5 = (TextView) view.findViewById(R.id.BLEH);
                Fearsla faersla = mList.get(position);
                text1.setText(Integer.toString(faersla.FearsluNumer));
                text2.setText(faersla.Fyrirtaeki);
                text3.setText(faersla.Date);
                text4.setText(Integer.toString(faersla.Verd));
                //text2.setText(Integer.toString(faersla.Verd) + " ISK");
                //text5.setTag(Bakfaersla.tagIncrementer);
                SoluYfirlit.tagIncrementer++;





                return view;
            }
        };

        mGridView.setAdapter(adapter);

        mGridView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Fearsla faersla = mList.get(position);
                        Toast.makeText(getApplicationContext()," ?? ?ttir ? f?rlu n?mer "+ faersla.FearsluNumer, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        TextView home = (TextView) findViewById(R.id.textViewSoluYfirlit);
        home.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getBaseContext(), start.class);
                intent.putExtra("User_Name", user);
                startActivity(intent);
            }

        /*
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
                intent.putExtra("vorulisti", true);
                intent.putExtra("User_Name", user);
                startActivityForResult(intent, 0);

            }
         */
        });

    }
    //@Override
    public boolean onCreateOptionsMenu(Menu menu)
        {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_solu_yfirlit, menu);
        return true;
        }

    //@Override
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
