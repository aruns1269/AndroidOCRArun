package com.principal.ind.advisormobiledropticket;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
//import com.google.firebase.ml.vision.label.FirebaseVisionImageLabeler;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ocr extends AppCompatActivity {

    Button btnCaptureImage;
    ImageView imageDisplay;
    Button btnResetImage;
    Button nextButton;
    StringBuilder sb ;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btnCaptureImage = (Button) findViewById(R.id.btn_captureImage1);
        imageDisplay = (ImageView)findViewById(R.id.imageCapture);
        btnResetImage = (Button)findViewById(R.id.btn_resetImage);
        nextButton = (Button)findViewById(R.id.nextButton);
        sb = new StringBuilder();

        btnCaptureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });
        btnResetImage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
            @Override
            public void onClick(View view) {
                //imageDisplay.setImageResource(android.R.color.transparent);
                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryIntent,"select picture"),PICK_IMAGE);

            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(getApplicationContext(), review_form.class);
               // intent.putExtra("data",sb.toString());
               // startActivity(intent);

            }
        });
        // ProposedInsured proposedInsured = getIntent().getParcelableExtra("proposedInsured");

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0) {
            try {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imageDisplay.setImageBitmap(bitmap);
                FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
                FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();


                detector.processImage(image)
                        .addOnSuccessListener(
                                new OnSuccessListener<FirebaseVisionText>() {
                                    @Override
                                    public void onSuccess(FirebaseVisionText firebaseVisionText) {
                                        processTextRecognitionResult(firebaseVisionText);
                                    }
                                }
                        );

            } catch (Exception e) {
                e.printStackTrace();
            }
        }





            /*TextRecognizer recognizer = new TextRecognizer.Builder(getApplicationContext()).build();

            if (!recognizer.isOperational()) {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            } else {
                Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                SparseArray<TextBlock> items = recognizer.detect(frame);
                for (int i = 0; i < items.size(); i++) {
                    TextBlock myItem = items.valueAt(i);
                    sb.append(myItem.getValue());
                    sb.append("\n");
                }
                Toast.makeText(this, sb.toString() + "hellooooo" + items.size(), Toast.LENGTH_SHORT).show();
            }*/


        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            sb = new StringBuilder();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                imageDisplay.setImageBitmap(bitmap);
                FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
                FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();


                detector.processImage(image)
                        .addOnSuccessListener(
                                new OnSuccessListener<FirebaseVisionText>() {
                                    @Override
                                    public void onSuccess(FirebaseVisionText firebaseVisionText) {
                                        processTextRecognitionResult(firebaseVisionText);
                                    }
                                }
                        );

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void processTextRecognitionResult(FirebaseVisionText firebaseVisionText) {
        String nameLine1 = "";
        String nameLine2 = "";
        String dlNumber = "";
        String dateOfBirth = "";
        String addresss ="";
        String state ="";
        List<FirebaseVisionText.Line> allLines = new ArrayList<FirebaseVisionText.Line>();
        sb = new StringBuilder();
        //sb.append("hello");
        List<FirebaseVisionText.TextBlock> blocks = firebaseVisionText.getTextBlocks();
        if(blocks.size()==0) {
            Toast.makeText(this, "nothing", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i = 0;i<blocks.size();i++) {
            List<FirebaseVisionText.Line> lines = blocks.get(i).getLines();

            //sb.append(blocks.get(i).getText()+" next ");
            if(blocks.get(i).getText().contains("DL"))
            {
                addresss = blocks.get(i-1).getText();
            }
            for (int j = 0;j<lines.size();j++) {
                List<FirebaseVisionText.Element> elements = lines.get(j).getElements();
                allLines.add(lines.get(j));

                for (int k = 0;k<elements.size();k++) {
                    //sb.append(elements.get(k).getText());
                    /*if(elements.get(k).getText().equalsIgnoreCase("driver")) {
                        sb.append(elements.get(k).getBoundingBox().flattenToString());
                    }*/
                }

            }
            //sb.append(" next ");
            //sb.append(lines.get(0).getText()+" imp "+lines.size());
        }
        for (int z = 0; z < allLines.size() ; z++) {
            //    sb.append(allLines.get(z).getText()+" next ");
            if (allLines.get(z).getText().contains("DRIVER")) {
                nameLine1 = allLines.get(z + 1).getText().replaceAll("[0-9]*","");
                nameLine2 = allLines.get(z + 2).getText().replaceAll("[0-9]*","");

            }
            if (allLines.get(z).getText().contains("DL")) {
                dlNumber = allLines.get(z).getText().split("\\s")[allLines.get(z).getText().split("\\s").length-1];
            }
            if (allLines.get(z).getText().contains("DOB"))

                dateOfBirth = allLines.get(z).getText().replace("DOB" , "");
        }
        state = allLines.get(0).getText();
        sb.append(state+" "+nameLine1+ " " + nameLine2+ " " + dlNumber+ "" +dateOfBirth+" "+addresss);
        Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
    }
}
