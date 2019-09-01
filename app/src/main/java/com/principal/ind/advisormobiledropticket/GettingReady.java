 package com.principal.ind.advisormobiledropticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

 public class GettingReady extends AppCompatActivity {
    private ProposedInsured proposedInsured;
    private Button addApplicant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_ready);
        Intent intent = getIntent();
        proposedInsured = intent.getParcelableExtra("PROPOSEDINSURED");
        addApplicant = (Button)findViewById(R.id.add_Applicant);
        addApplicant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AcceleratedUnderwriting.class);
                intent.putExtra("PROPOSEDINSURED",proposedInsured);
                startActivity(intent);
            }
        });
    }
}
