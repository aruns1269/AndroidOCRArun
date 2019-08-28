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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_applicant);
        Intent intent = getIntent();
        proposedInsured = intent.getParcelableExtra("PROPOSEDINSURED");
        editText = (EditText)findViewById(R.id.showState);
        editText.setText(proposedInsured.getState());
        editText = (EditText)findViewById(R.id.showCoveragePeriod);
        editText.setText(proposedInsured.getCoverageAmount());
        editText = (EditText)findViewById(R.id.showPremium);
        editText.setText(proposedInsured.getPremium());
    }
}
