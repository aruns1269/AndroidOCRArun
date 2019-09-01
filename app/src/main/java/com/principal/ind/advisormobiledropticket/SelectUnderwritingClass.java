package com.principal.ind.advisormobiledropticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SelectUnderwritingClass extends AppCompatActivity {
    private Spinner underWritingClass;
    private Spinner rating;
    private Button next;
    private TextView validateUnderwritingClass;
    private RadioGroup tobaccoGroup;
    private RadioButton selectedRadioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_underwriting_class);

        Intent intent = getIntent();
        final ProposedInsured proposedInsured = intent.getParcelableExtra("PROPOSEDINSURED");
        underWritingClass = (Spinner)findViewById(R.id.underwritingClass);
        rating = (Spinner)findViewById(R.id.rating);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(SelectUnderwritingClass.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.underwritingClass));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        underWritingClass.setAdapter(arrayAdapter);
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(SelectUnderwritingClass.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.rating));
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rating.setAdapter(arrayAdapter1);
        next = (Button)findViewById(R.id.next);
        validateUnderwritingClass = (TextView)findViewById(R.id.validateUnderwritingClass);
        tobaccoGroup = (RadioGroup)findViewById(R.id.tobaccoGroup);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String underwritingClass = underWritingClass.getSelectedItem().toString();
                String ratin = rating.getSelectedItem().toString();
                boolean tobacco = false;
                if(underwritingClass.equalsIgnoreCase("Select UnderWriting Class")) {
                    validateUnderwritingClass.setText("*Please Select Underwriting Class");
                } else {
                    if(ratin.equalsIgnoreCase("Select Rating")) {
                        validateUnderwritingClass.setText("*Please Select Rating");
                    } else {
                        selectedRadioButton = findViewById(tobaccoGroup.getCheckedRadioButtonId());
                        if(selectedRadioButton != null) {
                            proposedInsured.setUnderwritingClass(underwritingClass);
                            proposedInsured.setRating(ratin);
                            if(selectedRadioButton.getText().toString().equalsIgnoreCase("Tobacco")) {
                                tobacco= true;
                            }
                            proposedInsured.setTobacco(tobacco);

                            Intent intent = new Intent(getApplicationContext(), GettingReady.class);
                            intent.putExtra("PROPOSEDINSURED", proposedInsured);
                            startActivity(intent);
                        } else {
                            validateUnderwritingClass.setText("Please Select Tobacco Group");
                        }
                    }
                }
            }
        });
    }
}
