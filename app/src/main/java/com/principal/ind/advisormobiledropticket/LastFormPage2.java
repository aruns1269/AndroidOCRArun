package com.principal.ind.advisormobiledropticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LastFormPage2 extends AppCompatActivity {
    Button getQuote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_form_page2);
        getQuote = (Button)findViewById(R.id.getQuote);
        getQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Get_Quote.class);
                intent.putExtra("tobacco","");
                intent.putExtra("dob","");
                intent.putExtra("gender","");
                startActivity(intent);
            }
        });
    }
}
