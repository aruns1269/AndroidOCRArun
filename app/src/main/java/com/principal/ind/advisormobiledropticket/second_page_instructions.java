package com.principal.ind.advisormobiledropticket;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class second_page_instructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page_instructions);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView instructions = findViewById(R.id.textViewIsntructions);
        instructions.setText("Your initial quote should be generated in 3 mins with our 4 step guided process. All you need is government id proof and an income proof. \n \n Step 1: Scan your Driving License \n Step 2: Scan your W2 form \n Step 3: Enter details about nicotine \t \t                 consumption \n Step4: Review the details \n Step 5: Submit");

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        Button letsGetStarted = findViewById(R.id.button5);
        letsGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),attach.class);
                startActivity(intent);
            }
        });
    }

}
