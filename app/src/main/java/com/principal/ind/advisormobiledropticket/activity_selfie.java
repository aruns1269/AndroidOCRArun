package com.principal.ind.advisormobiledropticket;

import android.Manifest;
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
import androidx.core.content.FileProvider;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class activity_selfie extends AppCompatActivity {

    public static final String FILE_NAME = "temp.jpg";
    ImageView imageDisplay;
    String tobacco ="";
    String fName = "";
    String lName ="";
    String state ="";
    String addressLine1 ="";;
    String addresLine2 ="";
    String dlNumber ="";
    String dob ="";
    String height ="";
    String weight = "";
    String gender = "";
    String expiryDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageDisplay = findViewById(R.id.imageView22);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        ImageView selfie = findViewById(R.id.button_selfie);
        Button next = findViewById(R.id.selfie_next);
        selfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageDisplay.setImageResource(R.drawable.sell);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LastForm.class);
                intent.putExtra("tobacco",tobacco);
                intent.putExtra("state", state);
                intent.putExtra("fName",fName);
                intent.putExtra("lName",lName);
                intent.putExtra("addressLine1",addressLine1);
                intent.putExtra("addresLine2",addresLine2);
                intent.putExtra("dlNumber",dlNumber);
                intent.putExtra("dob",dob);
                intent.putExtra("height",height);
                intent.putExtra("weight",weight);
                intent.putExtra("gender",gender);
                intent.putExtra("expiryDate",expiryDate);

                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        try {
            fName = (intent.getStringExtra("fName"));
            lName =(intent.getStringExtra("lName"));
            state =(intent.getStringExtra("state"));
            addressLine1 =(intent.getStringExtra("addressLine1"));
            addresLine2 =(intent.getStringExtra("addresLine2"));
            dlNumber =(intent.getStringExtra("dlNumber"));
            dob =(intent.getStringExtra("dob"));
            height =(intent.getStringExtra("height"));
            weight =(intent.getStringExtra("weight"));
            gender =(intent.getStringExtra("gender"));
            expiryDate =(intent.getStringExtra("expiryDate"));

        }
        catch (Exception e){

        }


    }
    public void startCamera() {

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri photoUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName(), getCameraFile());
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(intent, 3);
        }

    public File getCameraFile() {
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return new File(dir, FILE_NAME);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            try {
                //sb = new StringBuilder();
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imageDisplay.setImageBitmap(bitmap);
                //FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
                //FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
//                TextRecognizer recognizer = new TextRecognizer.Builder(getApplicationContext()).build();
//                if (!recognizer.isOperational()) {
//                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
//                } else {
//                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
//                    SparseArray<TextBlock> items = recognizer.detect(frame);
//                    for (int i = 0; i < items.size(); i++) {
//                        TextBlock myitem = items.valueAt(i);
//                        sb.append(myitem.getValue());
//                        sb.append("\n");
//                    }
//                }

                /*detector.processImage(image)
                        .addOnSuccessListener(
                                new OnSuccessListener<FirebaseVisionText>() {
                                    @Override
                                    public void onSuccess(FirebaseVisionText firebaseVisionText) {
                                        processTextRecognitionResult(firebaseVisionText);
                                    }
                                }
                        );*/

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    }
