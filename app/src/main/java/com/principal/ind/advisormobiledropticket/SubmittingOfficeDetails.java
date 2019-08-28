package com.principal.ind.advisormobiledropticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubmittingOfficeDetails extends AppCompatActivity {
    private ProposedInsured proposedInsured;
    private Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitting_office_details);
        Intent intent = getIntent();
        proposedInsured = intent.getParcelableExtra("PROPOSEDINSURED");

        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AboutApplicant.class);
                intent.putExtra("PROPOSEDINSURED",proposedInsured);
                startActivity(intent);
            }
        });

    }
}
