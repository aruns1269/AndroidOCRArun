package com.principal.ind.advisormobiledropticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class SelectState extends AppCompatActivity {
    private Spinner selectState;
    private TextView validateState;
    private Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_state);
        selectState = (Spinner)findViewById(R.id.selectState);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(SelectState.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.states));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectState.setAdapter(adapter);
        validateState = (TextView)findViewById(R.id.validateState);
        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dropDownValue = selectState.getSelectedItem().toString();
                if(dropDownValue.equalsIgnoreCase("Select State")) {
                    validateState.setText("* Please Select a State");
                } else {
                    ProposedInsured proposedInsured = new ProposedInsured();
                    proposedInsured.setState(dropDownValue);
                    Intent intent = new Intent(getApplicationContext(), SelectCoverage.class);
                    intent.putExtra("PROPOSEDINSURED", proposedInsured);
                    startActivity(intent);
                }
            }
        });

    }
}
