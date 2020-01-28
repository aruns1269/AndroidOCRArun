package com.principal.ind.advisormobiledropticket;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
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

public class finalOcr extends Activity {

    Button btnCaptureImage;
    ImageView imageDisplay;
    Button btnResetImage;
    Button nextButton;
    Button w2form;
    StringBuilder sb ;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    private static final int PICK_IMAGE_W2 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_final_ocr);
        btnCaptureImage = (Button) findViewById(R.id.finalbtn_captureImage1);
        imageDisplay = (ImageView)findViewById(R.id.finalimageCapture);
        btnResetImage = (Button)findViewById(R.id.finalbtn_resetImage);
        nextButton = (Button)findViewById(R.id.finalnextButton);
        sb = new StringBuilder();
//        final DialogInterface dialog = new DialogInterface() {
//            @Override
//            public void cancel() {
//
//            }
//
//            @Override
//            public void dismiss() {
//
//            }
//        };

        btnCaptureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getApplicationContext());
//                alertDialog.setMessage("Choose a Picture");
//                alertDialog.setPositiveButton(R.string.dialouge_gallery, new DialogInterface.OnClickListener(){
//
//                    @Override
//                            public void onClick(DialogInterface dialog, int which){
//
//                        Intent galleryIntent = new Intent();
//                        galleryIntent.setType("image/*");
//                        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
//                        startActivityForResult(Intent.createChooser(galleryIntent,"select picture"),PICK_IMAGE_W2);
//                    }
//
//                });
//                alertDialog.create().show();
/*
                Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(Intent.createChooser(cameraintent,"select picture"),PICK_IMAGE_W2);*/

//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent,0);

                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryIntent,"select picture"),PICK_IMAGE_W2);

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
//        w2form.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder alertbuilder = new AlertDialog.Builder(getApplicationContext());
//                alertbuilder.setPositiveButton()
//            }
//        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            imageUri = data.getData();
            //Bitmap bitmap = (Bitmap) data.getExtras().get("data");
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
                    //Toast.makeText(this,sb.toString(),Toast.LENGTH_SHORT).show();

                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(),LastForm.class);
                    startActivity(intent);
                }
            });

        }
//




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
                TextRecognizer recognizer = new TextRecognizer.Builder(getApplicationContext()).build();
                if(!recognizer.isOperational())
                {
                    Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
                }
                else {
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<TextBlock> items = recognizer.detect(frame);
                    ArrayList allLines = new ArrayList<>();
                    Insured insured = new Insured();
                    for (int i = 0;i<items.size();i++){
                        TextBlock myitem = items.valueAt(i);

                        String[] tempItems = myitem.getValue().split("\n");
                        for (int z = 0;z<tempItems.length; z++) {
                            allLines.add(tempItems[z]);
                        }
                        sb.append(allLines.get(i));
                        sb.append("\n");
                    }
                    if(allLines.get(0).toString().contains("California") || allLines.get(0).toString().contains("CALIFORNIA")){
                        insured.setState(allLines.get(0).toString().substring(0,10));
                    } else {
                        insured.setState(allLines.get(0).toString());
                    }
                    for(int i=0;i<allLines.size();i++) {
                        if(allLines.get(i).toString().startsWith("FN")){
                            insured.setFirstName(allLines.get(i).toString().replaceAll("FN",""));
                            insured.setAddress1(allLines.get(i+1).toString());
                            insured.setAddress2(allLines.get(i+2).toString());
                        } else if(allLines.get(i).toString().startsWith("LN")){
                            insured.setLastName(allLines.get(i).toString().replaceAll("LN",""));
                        }else if (allLines.get(i).toString().contains("DRIVER")) {
                            insured.setLastName(allLines.get(i + 1).toString().replaceAll("[0-9]*", ""));
                            insured.setFirstName(allLines.get(i + 2).toString().replaceAll("[0-9]*", ""));
                            insured.setAddress1(allLines.get(i+3).toString());
                            insured.setAddress2(allLines.get(i+4).toString());
                        }
                        if (allLines.get(i).toString().contains("DOB")) {
                            insured.setDateOfBirth(allLines.get(i).toString().replaceAll("DOB", ""));
                        }
                        if (allLines.get(i).toString().contains("DL No") || allLines.get(i).toString().contains("DL")) {
                            insured.setDrivingLicense(allLines.get(i).toString().replaceAll("DL No.|DL", ""));
                        }
                        if (allLines.get(i).toString().contains("EXP")) {
                            insured.setExpiryDate(allLines.get(i).toString().replaceAll("EXP", ""));
                        }
                        if (allLines.get(i).toString().contains("SEX") || allLines.get(i).toString().contains("Sex")) {
                            String gender = allLines.get(i).toString().replaceAll("Sex", "");
                            if (gender.contains("F")) {
                                insured.setGender("Female");
                            } else {
                                insured.setGender("Male");
                            }
                        }
                        if(allLines.get(i).toString().contains("Hgt") || allLines.get(i).toString().contains("HGT")){
                            String height = allLines.get(i).toString().replaceAll("Hgt|HGT","").substring(1,5);
                            String heightInches = height.substring(0,1);
                            String heightFeet = height.substring(2);
                            String heightInInchesAndFeet = heightInches + "'" + heightFeet + "\"";
                            insured.setHeight(heightInInchesAndFeet);
                        }
                        if(allLines.get(i).toString().contains("wGT") || allLines.get(i).toString().contains("WGT")){
                            insured.setWeight(allLines.get(i).toString().replaceAll("HGT|wGT","").substring(6));
                        }
                    }

                    String firstname = insured.getFirstName();
                    String lastname = insured.getLastName();
                    String dob = insured.getDateOfBirth();
                    String dl = insured.getDrivingLicense();
                    String exp = insured.getExpiryDate();
                    String sex = insured.getGender();
                    String height = insured.getHeight();
                    String weight = insured.getWeight();
                    String state = insured.getState();
                    String address1 = insured.getAddress1();
                    String address2 = insured.getAddress2();

//                    for (int i = 0 ; i < allLines.size() ; i++)
//                    {
//
//                        if (allLines.get(i).toString().matches("^\\d*-\\d*-\\d*$"))
//                        {
//                            sb.append(allLines.get(i).toString());
//                        }
//                    }
                    //Toast.makeText(this,"firstname: "+firstname + "\nlastname: "+ lastname + "\ndob: " +dob +"\naddress1: "+address1+"\naddress2: "+address2+"\nDL no:"+dl+"\nexp: "+exp+"\ngender: "+sex+"\nheight: "+height+"\nweight: "+weight+"\nstate: "+state,Toast.LENGTH_SHORT).show();

                    //         Toast.makeText(this,sb.toString(),Toast.LENGTH_SHORT).show();
                    setReviewForm(insured);

                }

                /*FirebaseApp.initializeApp(getApplicationContext());
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
                        );*/

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /* private void processTextRecognitionResult(FirebaseVisionText firebaseVisionText) {
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
                     *//*if(elements.get(k).getText().equalsIgnoreCase("driver")) {
                        sb.append(elements.get(k).getBoundingBox().flattenToString());
                    }*//*
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
    }*/
//    public void findFields(ArrayList items) {
//        String nameLine1 = "";
//        String nameLine2 = "";
//        String dlNumber = "";
//        String dateOfBirth = "";
//        String addresss ="";
//        String expiryDate = "";
//        int size = 0;
//        String state =items.get(0).toString();
//        for (int i = 0;i<items.size();i++){
//            //TextBlock myitem = items.valueAt(i);
//            if (items.get(i).toString().contains("DRIVER")) {
//                nameLine1 = items.get(i+1).toString().replaceAll("[0-9]*","");
//                //size = nameLine1.split("\n").length;
//                nameLine2 = items.get(i+2).toString().replaceAll("[0-9]*","");
//                addresss = items.get(i+3).toString()+items.get(i+4).toString();
//
//            }
//
//            if (items.get(i).toString().contains("DL")) {
//                dlNumber = items.get(i).toString().split("\\s")[items.get(i).toString().split("\\s").length-1];
//            }
//
//            if (items.get(i).toString().contains("DOB")) {
//
//                dateOfBirth = items.get(i).toString().replace("DOB", "");
//            }
//            if (items.get(i).toString().contains("EXP")) {
//
//                expiryDate = items.get(i).toString().split("EXP")[1];
//            }
//
//        }
////        sb.append(dateOfBirth+"\n"+expiryDate);
//        //sb.append(dateOfBirth+"\n"+state+"\n"+nameLine1+ "\n"+nameLine2 + "\n"+addresss +"\n"+dlNumber +"\n"+expiryDate);
//  //      sb.append("\n");
//        Toast.makeText(this,sb.toString(),Toast.LENGTH_SHORT).show();
//
//    }
    public void setReviewForm(final Insured insured){

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),activity_selfie.class);
                // Intent intent = new Intent(getApplicationContext(),W2Form.class);
                intent.putExtra("state", insured.getState());
                intent.putExtra("fName",insured.getFirstName());
                intent.putExtra("lName",insured.getLastName());
                intent.putExtra("addressLine1",insured.getAddress1());
                intent.putExtra("addresLine2",insured.getAddress2());
                intent.putExtra("dlNumber",insured.getDrivingLicense());
                intent.putExtra("dob",insured.getDateOfBirth());
                intent.putExtra("height",insured.getHeight());
                intent.putExtra("weight",insured.getWeight());
                intent.putExtra("gender",insured.getGender());
                intent.putExtra("expiryDate",insured.getExpiryDate());
                intent.putExtra("insured", insured);
                //  intent.putExtra("")
                startActivity(intent);
            }
        });

    }

}
