package com.example.charl.bandapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.content.Intent;


import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends Activity {
    double cost_Blink = 79.99;
    double cost_Haken = 69.99;
    double cost_Radio = 199.99;
    int numberTix;
    double totalCost;
    CheckBox peanut_checkbox;
    CheckBox chips_checkbox;
    CheckBox popcorn_checkbox;
    CheckBox iceCream_checkbox;
    CheckBox snickers_checkbox;
    String groupChoice;
    ArrayList<String> snackList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        peanut_checkbox = (CheckBox)findViewById(R.id.chkPeanuts);
        chips_checkbox = (CheckBox)findViewById(R.id.chkChips);
        popcorn_checkbox = (CheckBox)findViewById(R.id.chkPop);
        iceCream_checkbox = (CheckBox)findViewById(R.id.chkIceCream);
        snickers_checkbox = (CheckBox)findViewById(R.id.chkSnickers);
    }

    public void sendInfo(View v) {

        Intent intent = new Intent(this, DisplayTotalActivity.class);
        Bundle extras = new Bundle();

        Spinner group = (Spinner) findViewById(R.id.spinner_band);
        EditText tickets = (EditText) findViewById(R.id.txtTickets);
        EditText firstName = (EditText) findViewById(R.id.txtFirstName);
        EditText lastName = (EditText) findViewById(R.id.txtLastName);
        EditText emailAdd = (EditText) findViewById(R.id.txtEmail);

        String finalFirst = firstName.getText().toString();
        String finalLast = lastName.getText().toString();
        String finalEmail = emailAdd.getText().toString();
        groupChoice = group.getSelectedItem().toString();
        numberTix = Integer.parseInt(tickets.getText().toString());

        if (groupChoice.equals("Blink 182")) {
            totalCost = cost_Blink * numberTix;
        } else if (groupChoice.equals("Haken")) {
            totalCost = cost_Haken * numberTix;
        } else if (groupChoice.equals("Radiohead")) {
            totalCost = cost_Radio * numberTix;
        }

        if (peanut_checkbox.isChecked()) {
            snackList.add("Peanuts");
        }

        if(chips_checkbox.isChecked()){
            snackList.add("Chips");
        }

        if(popcorn_checkbox.isChecked()){
            snackList.add("Popcorn");
        }

        if(iceCream_checkbox.isChecked()){
            snackList.add("Ice Cream");
        }

        if(snickers_checkbox.isChecked()){
            snackList.add("Snickers");
        }

        DecimalFormat currency = new DecimalFormat("$###,###.##");
        DecimalFormat zip = new DecimalFormat("#####");

        RadioGroup payment_method = (RadioGroup) findViewById(R.id.payments);
        int rgIndex = payment_method.getCheckedRadioButtonId();
        RadioButton payment_chosen = (RadioButton) findViewById(rgIndex);
        String paymentChoice = payment_chosen.getText().toString();
        extras.putString("BandName", groupChoice);
        extras.putString("Total", currency.format(totalCost));
        extras.putString("FirstName", finalFirst);
        extras.putString("LastName", finalLast);
        extras.putString("emailAdd", finalEmail);
        extras.putString("payMethod", paymentChoice);
        extras.putStringArrayList("Snacks", snackList);
        intent.putExtras(extras);
        startActivity(intent);


    }

}

