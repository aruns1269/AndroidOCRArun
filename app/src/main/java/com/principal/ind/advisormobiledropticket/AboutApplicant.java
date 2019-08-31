package com.principal.ind.advisormobiledropticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AboutApplicant extends AppCompatActivity {
    private ProposedInsured proposedInsured;
    private Button next;
    private EditText editText;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_applicant);
        Intent intent = getIntent();
        proposedInsured = intent.getParcelableExtra("PROPOSEDINSURED");
        editText = (EditText)findViewById(R.id.showState);
        editText.setText(proposedInsured.getState());
        editText1 = (EditText)findViewById(R.id.showCoveragePeriod);
        editText1.setText(proposedInsured.getCoverageAmount());
        editText2 = (EditText)findViewById(R.id.showPremium);
        editText2.setText(proposedInsured.getPremium());
        editText3 = (EditText)findViewById(R.id.showBillingFrequency);
        editText3.setText(proposedInsured.getBillingFrequency());
    }
}
