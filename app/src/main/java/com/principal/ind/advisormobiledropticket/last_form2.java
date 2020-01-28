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

public class last_form2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_form2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button getquote = findViewById(R.id.btn_get_quote);
        getquote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LastFormPage2.class);
                startActivity(intent);
            }
        });

        TextInputEditText tda = findViewById(R.id.ssn);
        tda.setText("123-45-789");

        TextInputEditText tdb = findViewById(R.id.income);
        tdb.setText("19279.5");

    }
}
