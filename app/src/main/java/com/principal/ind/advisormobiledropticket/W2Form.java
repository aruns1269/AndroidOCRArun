package com.principal.ind.advisormobiledropticket;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class W2Form extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    StringBuilder sb;
    ImageView imageDisplay;
    Button finalNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_w2_form);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        Intent intent = getIntent();
//        final Insured ins1 = (Insured) intent.getSerializableExtra("insured");
        finalNextButton = findViewById(R.id.nextButton21);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
            imageDisplay = findViewById(R.id.imageCapture2);
        Button captureImageGallery = findViewById(R.id.btn_galleryImage13);
        captureImageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryIntent, "select picture"), PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == 0) {
            try {
                sb = new StringBuilder();
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imageDisplay.setImageBitmap(bitmap);
                //FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
                //FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
                TextRecognizer recognizer = new TextRecognizer.Builder(getApplicationContext()).build();
                if(!recognizer.isOperational())
                {
                    Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
                }
                else {
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<TextBlock> items = recognizer.detect(frame);
                    for (int i = 0;i<items.size();i++){
                        TextBlock myitem = items.valueAt(i);
                        sb.append(myitem.getValue());
                        sb.append("\n");
                    }
                }} catch (Exception e) {
                e.printStackTrace();
            }
        }


        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            sb = new StringBuilder();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageDisplay.setImageBitmap(bitmap);
                TextRecognizer recognizer = new TextRecognizer.Builder(getApplicationContext()).build();
                if (!recognizer.isOperational()) {
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                } else {
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<TextBlock> items = recognizer.detect(frame);
                    ArrayList allLines = new ArrayList<>();
                    Insured insured = new Insured();
                    for (int i = 0; i < items.size(); i++) {
                        TextBlock myitem = items.valueAt(i);

                        String[] tempItems = myitem.getValue().split("\n");
                        for (int z = 0; z < tempItems.length; z++) {
                            allLines.add(tempItems[z]);
                        }

                        sb.append(allLines.get(i));
                        sb.append("\n");
                    }

                    for (int i = 0 ; i < allLines.size() ; i++)
                    {

                        if (allLines.get(i).toString().matches("^\\d*-\\d*-\\d*$"))
                        {
                            sb.append(allLines.get(i).toString());
                        }
                    }
                    Toast.makeText(this,sb.toString(),Toast.LENGTH_SHORT).show();

                }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        final Insured ins1 = (Insured) data.getSerializableExtra("insured");
                movingToReviewForm();
    }

    public void movingToReviewForm(){

        finalNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ocr.class);
//                intent.putExtra("state", insured.getState());
//                intent.putExtra("fName",insured.getFirstName());
//                intent.putExtra("lName",insured.getLastName());
//                intent.putExtra("addressLine1",insured.getAddress1());
//                intent.putExtra("addresLine2",insured.getAddress2());
//                intent.putExtra("dlNumber",insured.getDrivingLicense());
//                intent.putExtra("dob",insured.getDateOfBirth());
//                intent.putExtra("height",insured.getHeight());
//                intent.putExtra("weight",insured.getWeight());
//                intent.putExtra("gender",insured.getGender());
//                intent.putExtra("expiryDate",insured.getExpiryDate());
                //intent.putExtra("insured", insured);
                startActivity(intent);
            }
        });

    }
}