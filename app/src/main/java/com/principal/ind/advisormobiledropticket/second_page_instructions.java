package com.principal.ind.advisormobiledropticket;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class second_page_instructions extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page_instructions);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Switch aSwitch = (Switch)findViewById(R.id.switch3);

        TextView instructions = findViewById(R.id.textViewIsntructions);
        instructions.setText("Your initial quote should be generated in 3 mins with our 4 step guided process. All you need is government id card and income proof. \n \n Step 1: Scan Driving License \n Step 2: Scan income proof \n Step 3: Take selfie \n Step4: Review & Confirm");

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
                if(aSwitch.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), finalOcr.class);
                    startActivity(intent);
                }
            }
        });
    }

}
