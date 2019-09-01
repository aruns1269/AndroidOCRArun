package com.principal.ind.advisormobiledropticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AcceleratedUnderwriting extends AppCompatActivity {
    private ProposedInsured proposedInsured;
    private RadioGroup radioGroup;
    private RadioGroup radioGroup1;
    private RadioGroup radioGroup2;
    private RadioButton radioButton;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private TextView validateAcceleratedUnderwriting;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerated_underwriting);
        Intent intent = getIntent();
        proposedInsured = intent.getParcelableExtra("PROPOSEDINSURED");
        radioGroup = (RadioGroup)findViewById(R.id.accelerated_underwriting);
        radioGroup1 = (RadioGroup)findViewById(R.id.order_exam_requirements);
        radioGroup2 = (RadioGroup)findViewById(R.id.paramed_Vendor);
        validateAcceleratedUnderwriting = (TextView)findViewById(R.id.validateAcceleratedUnderwriting);
        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                if(radioButton != null) {
                    radioButton1 = findViewById(radioGroup1.getCheckedRadioButtonId());
                    if(radioButton1 != null) {
                        radioButton2 = findViewById(radioGroup2.getCheckedRadioButtonId());
                        if(radioButton2 != null) {
                            boolean acceleratedUnderwriting = false;
                            boolean orderRequirements = false;
                            if(radioButton.getText().toString().equalsIgnoreCase("yes")) {
                                acceleratedUnderwriting = true;
                            }
                            if(radioButton1.getText().toString().equalsIgnoreCase("Yes")){
                                orderRequirements = true;
                            }

                            proposedInsured.setAcceleratedUnderWritingRequired(acceleratedUnderwriting);
                            proposedInsured.setOrderExamRequirements(orderRequirements);

                            Intent intent = new Intent(getApplicationContext(),ReviewDetails.class);
                            intent.putExtra("PROPOSEDINSURED",proposedInsured);
                            startActivity(intent);
                        } else {
                            validateAcceleratedUnderwriting.setText("*Please select a Paramed Vendor");
                        }
                    } else {
                        validateAcceleratedUnderwriting.setText("*Please select Yes/No for ordering Exam Requirements");
                    }
                } else {
                     validateAcceleratedUnderwriting.setText("*Please select Yes/No for Accelerated Underwriting Program");
                }
            }
        });

    }
}
