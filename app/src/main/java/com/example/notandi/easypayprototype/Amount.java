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


public class Amount extends Activity
{
    private Calc Sum;
    private EditText Screen;
    private float numberBfr;
    private float numberAtr;
    private boolean oper =false;
    private char operator;
    public ButtonClickListener btnClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount);
        TextView txtUser = (TextView) findViewById(R.id.lblUser);
        Button btnConfirm = (Button) findViewById(R.id.btnConfirm);
        Button btnProduct = (Button) findViewById(R.id.buttonProduct);
        final String user = getIntent().getStringExtra("User_Name");
        boolean vara =  getIntent().getBooleanExtra("vorulisti", false);
        txtUser.setText(user);
        Screen = (EditText) findViewById(R.id.txtAmount);
        if(vara == true)
        {
            final String Amount = getIntent().getStringExtra("Amount");
            Screen.setText(Amount);
            numberBfr = Float.parseFloat(Amount);
        }
        btnClick = new ButtonClickListener();


        final int idList[] = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnPlus, R.id.btnMinus, R.id.btnMult,  R.id.btnEquals, R.id.btnC, R.id.btnDiv, R.id.btnDel};

        for( int id:idList)
        {
            View v = (View) findViewById(id);
            v.setOnClickListener(btnClick);
        }
        Screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int id : idList) {
                    View V = (View) findViewById(id);
                    V.setVisibility(View.VISIBLE);
                }
            }
        });

        Screen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Button btnConfirm = (Button) findViewById(R.id.btnConfirm);

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
                //CalcNr(operator);
                //Screen.setText(String.valueOf(numberBfr));
                //String pay = Screen.getText().toString();
                String ScreenEqat = Screen.getText().toString();
                int pay = Calc.calculate(ScreenEqat);
                Screen.setText(String.valueOf(pay));
                Intent intent = new Intent(Amount.this, HomeActivity.class);
                intent.putExtra("Amount", pay);
                intent.putExtra("User_Name", user);
                //startActivityForResult(intent, 0);
                startActivity(intent);
            }
        });

        btnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String pay = Screen.getText().toString();
                Intent intent = new Intent(v.getContext(), ProductList.class);
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

    public void CalcNr(char op)
    {
        if(oper == true)
        {
            //String ScreenNr = Screen2.getText().toString();
            //ScreenNr += " "+ String.valueOf(numberBfr) + " "+ String.valueOf(operator) + " " + String.valueOf(numberAtr) + " = ";
            switch (op)
            {
                case '+':
                    numberBfr = numberBfr + numberAtr;
                    numberAtr = 0.0f;
                    break;
                case '-':
                    numberBfr = numberBfr - numberAtr;
                    numberAtr = 0.0f;
                    break;
                case '*':
                    numberBfr = numberBfr * numberAtr;
                    numberAtr = 0.0f;
                    break;
                case '/':
                    if(numberAtr != 0 && numberAtr != 0.0f)
                    {
                        numberBfr = numberBfr / numberAtr;
                        numberAtr = 0.0f;
                    }
                    else
                    {
                        Screen.setText("X/0 = the end of the world");
                    }

                    break;
            }
            //ScreenNr += String.valueOf(numberBfr)+ " ";
            //Screen2.setText(ScreenNr);
        }
        else
        {
            numberBfr = Float.parseFloat(Screen.getText().toString());
            numberAtr = 0.0f;
        }

    }

    private class ButtonClickListener implements View.OnClickListener
    {
        public void onClick(View V)
        {
            switch (V.getId())
            {
                case R.id.btnC:
                    Screen.setText("0");
                    //Screen2.setText("0");
                    numberBfr = 0.0f;
                    numberAtr = 0.0f;
                    oper = false;
                    operator = ' ';
                    break;
                case R.id.btnDel:
                    String str = Screen.getText().toString();
                    if (str.length() > 0) {
                        str = str.substring(0, str.length()-1);
                    }
                    Screen.setText(str);
                    break;
                case R.id.btnEquals:
                    /*
                    CalcNr(operator);
                    Screen.setText(String.valueOf(numberBfr));
                    oper = false;
                    */
                    String ScreenEqat = Screen.getText().toString();
                    int temp = Calc.calculate(ScreenEqat);
                    Screen.setText(String.valueOf(temp));
                    break;
                /*
                case R.id.btnPlus:
                    CalcNr(operator);
                    Screen.setText("");
                    operator = '+';
                    oper = true;
                    break;
                case R.id.btnMinus:
                    CalcNr(operator);
                    Screen.setText("");
                    operator = '-';
                    oper = true;
                    break;
                case R.id.btnMult:
                    CalcNr(operator);
                    Screen.setText("");
                    operator = '*';
                    oper = true;
                    break;
                case R.id.btnDiv:
                    CalcNr(operator);
                    Screen.setText("");
                    operator = '/';
                    oper = true;
                    break;
                case R.id.btnNeg:
                    String ScreenNeg = Screen.getText().toString();
                    numberAtr = Float.parseFloat(ScreenNeg);
                    numberAtr *= -1.0f;
                    Screen.setText(String.valueOf(numberAtr));
                    break;
                    */
                default:
                    String numb = ((Button) V).getText().toString();
                    String ScreenNr = Screen.getText().toString();
                    if(ScreenNr.equals("0"))
                    {
                        Screen.setText(numb);
                    }
                    else
                    {
                        ScreenNr = ScreenNr + numb;
                        //numberAtr = Float.parseFloat(ScreenNr);
                        Screen.setText(ScreenNr);
                    }

            }

        }
    }

}
