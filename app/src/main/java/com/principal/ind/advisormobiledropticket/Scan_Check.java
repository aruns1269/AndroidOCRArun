package com.principal.ind.advisormobiledropticket;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.util.ArrayList;

public class Scan_Check extends Activity {
    private ImageView imageView;
    private Button scanCheck;
    private EditText editText;
    private static final int PICK_IMAGE = 1;
    private Uri imageUri;
    private StringBuilder sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan__check);
        imageView = (ImageView)findViewById(R.id.imageView);
        scanCheck = (Button)findViewById(R.id.scanCheck);
        editText = (EditText) findViewById(R.id.accountNumber);
        scanCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryIntent,"select picture"),PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageUri = data.getData();
        sb = new StringBuilder();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
            imageView.setImageBitmap(bitmap);
            TextRecognizer recognizer = new TextRecognizer.Builder(getApplicationContext()).build();
            if(!recognizer.isOperational())
            {
                Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
            } else {
                Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                SparseArray<TextBlock> items = recognizer.detect(frame);
                ArrayList allLines = new ArrayList<>();
                String s = "";
                for (int i = 0;i<items.size();i++){
                    TextBlock myitem = items.valueAt(i);

                    String[] tempItems = myitem.getValue().split("\n");

                    for (int z = 0;z<tempItems.length; z++) {
                        allLines.add(tempItems[z]);
                        s = s+tempItems[z];

                    }
                    String [] accountNumber = s.split(":");
                    int length = accountNumber.length;
                    if(length>1) {
                        editText.setText(accountNumber[length-1]);
                    }


                    sb.append(allLines.get(i));
                    sb.append("\n");

                }/*
                for(int i=0;i<allLines.size();i++) {
                    if(allLines.get(i).toString().contains("PAY")){
                        textView.setText(allLines.get(i).toString());
                    }
                }*/
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
