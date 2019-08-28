package com.principal.ind.advisormobiledropticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SelectPremium extends AppCompatActivity {
    private TextView validatePremium;
    private Spinner spinner;
    private Button next;
    private EditText enterPremium;
    private ProposedInsured proposedInsured;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_premium);
        Intent intent = getIntent();
        proposedInsured = intent.getParcelableExtra("PROPOSEDINSURED");
        spinner = (Spinner)findViewById(R.id.billinFreq);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(SelectPremium.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.billingFrequency));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        enterPremium = (EditText)findViewById(R.id.enterPremium);
        validatePremium = (TextView)findViewById(R.id.validatePremium);
        next = (Button)findViewById(R.id.Next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!enterPremium.getText().toString().equals("")) {
                    if(!spinner.getSelectedItem().toString().equals("Select Billing Frequency")) {
                        proposedInsured.setPremium(enterPremium.getText().toString());
                        proposedInsured.setPremium(spinner.getSelectedItem().toString());
                        Intent intent = new Intent(getApplicationContext(), SelectUnderwritingClass.class);
                        intent.putExtra("PROPOSEDINSURED",proposedInsured);
                        startActivity(intent);
                    } else {
                        validatePremium.setText("*Please Select Billing Frequency");
                    }
                } else {
                    validatePremium.setText("*Please enter Premium");
                }
            }
        });



    }
}
