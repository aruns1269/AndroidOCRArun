package com.principal.ind.advisormobiledropticket;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Get_Quote extends AppCompatActivity {
    Spinner policy;
    Spinner biling;
    int coverage = 50000;
    double prem = 8;
    TextView seekBarPosition;
    TextView covLength;
    TextView covAmount;
    TextView premiumTag;
    TextView monPrem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get__quote);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String dob = intent.getStringExtra("dob");
        String sex = intent.getStringExtra("gender");
        String tobacco = intent.getStringExtra("tobacco");
        String health = "";
        Integer age = 65 ;
        policy = (Spinner) findViewById(R.id.spinner);
        biling = (Spinner) findViewById(R.id.spinner2);
       // calculatedPremium = (TextView)findViewById(R.id.textForcalculatedpremium);
        covAmount = (TextView)findViewById(R.id.covAmount);
        covLength = (TextView)findViewById(R.id.coverageLength);
        covLength.setText("10 Years");
        monPrem = (TextView)findViewById(R.id.monthlyPremium);
        premiumTag = (TextView)findViewById(R.id.premiumTag);
        seekBarPosition = (TextView)findViewById(R.id.seekbar_position);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Get_Quote.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.policy));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(Get_Quote.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.billing));

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        policy.setAdapter(adapter);
        biling.setAdapter(adapter1);

        if(tobacco.contains("No")) {
            health = "non-smoker in great health";
        }else{
            health = "smoker in good health";
        }
    //    DateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
       /* DateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
        int d1 = Integer.parseInt(formatter2.format(dob));
        int d2 = Integer.parseInt(formatter2.format(new Date()));
        age = d2-d1;
*/


        TextView tv = findViewById(R.id.text_estimated);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        seekBarPosition.setText("$50k");
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i==0) {
                    seekBarPosition.setText("$50k");
                    covAmount.setText("$50k");
                } if(i>0) {
                    i=i+50;
                    coverage = i*1000;
                    seekBarPosition.setText("$"+ String.valueOf(i)+"k");
                    covAmount.setText("$"+ String.valueOf(i)+"k");
                    double coverageUnits = coverage/1000;
                    double premiumAmountTemp = coverageUnits * 2;
                    double premiumAmount = premiumAmountTemp/12;
                    //calculatedPremium.setText("$"+Math.round(premiumAmount));
                    monPrem.setText("$"+Math.round(premiumAmount));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        double coverageUnits = coverage/1000;
        final double premiumAmountTemp = coverageUnits * 4;
        double premiumAmount = premiumAmountTemp/12;
        //calculatedPremium.setText("$"+Math.round(premiumAmount));
        monPrem.setText("$"+Math.round(premiumAmount));
        biling.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                double units = coverage/1000;
                double premium = units*2;
                if(i==0) {
                    //MOnthly calculated Premium

                    premium = premium/12;
                    //calculatedPremium.setText("$"+Math.round(premium));
                    monPrem.setText("$"+Math.round(premium));
                    prem = Math.round(premium);
                    premiumTag.setText("Monthly\nPremium");
                }
                if(i==1) {
                    //Quaterly calculated Premium
                    premium = premium/4;
                    monPrem.setText("$"+Math.round(premium));
                    prem = Math.round(premium);
                    premiumTag.setText("Quaterly\nPremium");
                }
                if(i==2) {
                    //Half Yearly calculated Premium
                    premium = premium/2;
                    monPrem.setText("$"+Math.round(premium));
                    prem = Math.round(premium);
                    premiumTag.setText("Semi-Annual\nPremium");
                }
                if(i==3) {
                    // Yearly calculated Premium
                    premium = premium;
                    monPrem.setText("$"+Math.round(premium));
                    prem = Math.round(premium);
                    premiumTag.setText("Annual\nPremium");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        policy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0) {
                    //MOnthly calculated Premium
                    //calculatedPremium.setText("$"+Math.round(premium))
                   // monPrem.setText("$"+Math.round(premium));
                    covLength.setText("10 Years");
                }
                if(i==1) {
                    //Quaterly calculated Premium
                    //prem = 2 * prem;
                    //monPrem.setText("$"+Math.round(prem));
                    covLength.setText("20 Years");
                }
                if(i==2) {
                    //prem = 3 * prem;
                    //monPrem.setText("$"+Math.round(prem));
                    covLength.setText("30 Years");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

}
