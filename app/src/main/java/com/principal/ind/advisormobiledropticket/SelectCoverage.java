package com.principal.ind.advisormobiledropticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SelectCoverage extends AppCompatActivity {
    private RadioGroup selectCoverage;
    private ProposedInsured proposedInsured;
    private RadioButton radioButton;
    private Button next;
    private TextView validateCoverage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_coverage);
        Intent intent = getIntent();
        proposedInsured = intent.getParcelableExtra("PROPOSEDINSURED");
        selectCoverage = (RadioGroup)findViewById(R.id.selectCoverage);
        next = (Button)findViewById(R.id.Next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButton = findViewById(selectCoverage.getCheckedRadioButtonId());

                if(radioButton !=null) {
                    proposedInsured.setCoverageAmount(radioButton.getText().toString());
                    Intent intent = new Intent(getApplicationContext(),SelectRider.class);
                    intent.putExtra("PROPOSEDINSURED", proposedInsured);
                    startActivity(intent);
                } else {
                    validateCoverage = (TextView)findViewById(R.id.validateCoverage);
                    validateCoverage.setText("* Please select a Coverage Period");
                }

            }
        });
    }
}
