package com.principal.ind.advisormobiledropticket;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class activity_tobacco extends AppCompatActivity {

    String tobacco ="";
    String fName = "";
    String lName ="";
    String state ="";
    String addressLine1 ="";;
    String addresLine2 ="";
    String dlNumber ="";
    String dob ="";
    String height ="";
    String weight = "";
    String gender = "";
    String expiryDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tobacco);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        Intent intent = getIntent();
        try {
             fName = (intent.getStringExtra("fName"));
             lName =(intent.getStringExtra("lName"));
             state =(intent.getStringExtra("state"));
             addressLine1 =(intent.getStringExtra("addressLine1"));
             addresLine2 =(intent.getStringExtra("addresLine2"));
             dlNumber =(intent.getStringExtra("dlNumber"));
             dob =(intent.getStringExtra("dob"));
             height =(intent.getStringExtra("height"));
             weight =(intent.getStringExtra("weight"));
             gender =(intent.getStringExtra("gender"));
            expiryDate =(intent.getStringExtra("expiryDate"));

        }
        catch (Exception e){

        }

        TextView tobaccoText = findViewById(R.id.textView22);
        tobaccoText.setText("Do you currently use Tobacco or nicotine products?");


        Switch sw = findViewById(R.id.switch2);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Intent intent = new Intent(getApplicationContext(),LastForm.class);

                if(b){
                    tobacco = "Yes";
                    intent.putExtra("tobacco",tobacco);
                    intent.putExtra("state", state);
                    intent.putExtra("fName",fName);
                    intent.putExtra("lName",lName);
                    intent.putExtra("addressLine1",addressLine1);
                    intent.putExtra("addresLine2",addresLine2);
                    intent.putExtra("dlNumber",dlNumber);
                    intent.putExtra("dob",dob);
                    intent.putExtra("height",height);
                    intent.putExtra("weight",weight);
                    intent.putExtra("gender",gender);
                    intent.putExtra("expiryDate",expiryDate);
                    //intent.putExtra("insured", insured);
                }
                else{
                    tobacco = "No";
                    intent.putExtra("tobacco",tobacco);
                }
                startActivity(intent);
            }
        });
    }

}
