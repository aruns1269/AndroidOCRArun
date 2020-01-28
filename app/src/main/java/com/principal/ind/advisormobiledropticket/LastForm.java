package com.principal.ind.advisormobiledropticket;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class LastForm extends AppCompatActivity {

    private Spinner spinner;
    Spinner term_length;
    Spinner billingFrequency;
    private static final String[] tobacco = {"Yes","No"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_form);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        billingFrequency = findViewById(R.id.billingFrequency);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(LastForm.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.billing));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        billingFrequency.setAdapter(adapter);

        term_length = findViewById(R.id.term_length);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(LastForm.this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.policy));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        term_length.setAdapter(adapter1);

   /*     spinner = findViewById(R.id.tobaccoList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(LastForm.this,android.R.layout.simple_spinner_dropdown_item, tobacco);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        Intent intent = getIntent();

        TextInputEditText td1 = findViewById(R.id.firstName);
        TextInputEditText td2 = findViewById(R.id.lastNmae);
        TextInputEditText td3 = findViewById(R.id.state);
        TextInputEditText td4 = findViewById(R.id.addrline1);
        TextInputEditText td5 = findViewById(R.id.addrline2);
        TextInputEditText td6 = findViewById(R.id.dlnumber);
        TextInputEditText td7 = findViewById(R.id.DOB);
        TextInputEditText td8 = findViewById(R.id.height);
        TextInputEditText td9 = findViewById(R.id.weight);
        TextInputEditText td10 = findViewById(R.id.sex);
        TextInputEditText td11 = findViewById(R.id.ssn);

     //   TextInputEditText td11 = findViewById(R.id.expid);

        td1.setText(intent.getStringExtra("fName"));
        td2.setText(intent.getStringExtra("lName"));
        td3.setText(intent.getStringExtra("state"));
        td4.setText(intent.getStringExtra("addressLine1"));
        td5.setText(intent.getStringExtra("addresLine2"));
        td6.setText(intent.getStringExtra("dlNumber"));
        td7.setText(intent.getStringExtra("dob"));
        td8.setText(intent.getStringExtra("height"));
        td9.setText(intent.getStringExtra("weight"));
        td10.setText(intent.getStringExtra("gender"));
        td11.setText("*****1234");
   //     td11.setText(intent.getStringExtra("expiryDate"));
        //EditText ed4 = findViewById(R.id.editText4);
//        EditText ed5 = findViewById(R.id.editText5);
//        EditText ed6 = findViewById(R.id.editText6);
//        EditText ed7 = findViewById(R.id.editText7);
//        EditText ed8 = findViewById(R.id.editText8);
//        EditText ed9 = findViewById(R.id.editText9);
//        EditText ed10 = findViewById(R.id.editText10);
//        EditText ed11 = findViewById(R.id.editText11);
//
//        EditText ed12 = findViewById(R.id.edtest);
//        ed12.setText(intent.getStringExtra("fName"));

       // ed4.setText(intent.getStringExtra("fName"));
//        ed5.setText(intent.getStringExtra("lName"));
//        ed6.setText(intent.getStringExtra("addressLine1"));
//        ed7.setText(intent.getStringExtra("addresLine2"));
//        ed8.setText(intent.getStringExtra("state"));
//        ed9.setText(intent.getStringExtra("dlNumber"));
//        ed10.setText(intent.getStringExtra("dob"));

        Button save_and_next= findViewById(R.id.button_save_next);

        save_and_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText td11 = findViewById(R.id.tobacco_inlastform);
                TextInputEditText td7 = findViewById(R.id.DOB);
                TextInputEditText td10 = findViewById(R.id.sex);
                Intent intent = new Intent(getApplicationContext(),Get_Quote.class);
                intent.putExtra("tobacco",td11.getText().toString());
                intent.putExtra("dob",td7.getText().toString());
                intent.putExtra("gender",td10.getText().toString());
                startActivity(intent);
            }
        });
    }

}
