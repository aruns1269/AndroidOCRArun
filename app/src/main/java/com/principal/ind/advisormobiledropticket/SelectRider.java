package com.principal.ind.advisormobiledropticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SelectRider extends AppCompatActivity {
    List<String> selectedRiders = new ArrayList<>();
    private ProposedInsured proposedInsured;
    private Button next;
    private TextView validateRider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_rider);

        Intent intent = getIntent();
        proposedInsured = intent.getParcelableExtra("PROPOSEDINSURED");
        validateRider = (TextView)findViewById(R.id.validateRider);
        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedRiders.size()==0) {
                    validateRider.setText("*Please Select atleast one Rider");
                } else {
                    proposedInsured.setRider(selectedRiders);
                    Intent intent = new Intent(getApplicationContext(),SelectPremium.class);
                    intent.putExtra("PROPOSEDINSURED",proposedInsured);
                    startActivity(intent);
                }
            }
        });

    }

    public void selectedRiders (View view) {
        boolean checked = ((CheckBox)view).isChecked();
        switch(view.getId()) {

            case R.id.rider_Accelerated:
                if(checked) {
                    selectedRiders.add("Accelerated Benefit Rider");
                } else {
                    selectedRiders.remove("Accelerated Benefit Rider");
                } break;
            case R.id.rider_Child:
                if(checked) {
                    selectedRiders.add("Child Term Rider");
                } else {
                    selectedRiders.remove("Child Term Rider");
                } break;
            case R.id.rider_Conversion:
                if(checked) {
                    selectedRiders.add("Conversion Extension Rider");
                } else {
                    selectedRiders.remove("Conversion Extension Rider");
                } break;
            case R.id.rider_Premium:
                if(checked) {
                    selectedRiders.add("Waiver of Premium Rider");
                } else {
                    selectedRiders.remove("Waiver of Premium Rider");
                } break;
        }
    }

}
