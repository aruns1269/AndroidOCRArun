package com.principal.ind.advisormobiledropticket;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LastForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_form);
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
                Intent intent = new Intent(getApplicationContext(),last_form2.class);
                startActivity(intent);
            }
        });
    }

}
