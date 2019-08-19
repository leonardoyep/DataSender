package com.example.clientdatasender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etAge;
    Button btnSendData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "here", Toast.LENGTH_SHORT).show();

        etName =(EditText) findViewById(R.id.et_name);
        etAge = (EditText) findViewById(R.id.et_age);
        btnSendData = (Button) findViewById(R.id.button2);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Handle text being sent
            } else if (type.startsWith("image/")) {
                handleSendImage(intent); // Handle single image being sent
            }
        } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                handleSendMultipleImages(intent); // Handle multiple images being sent
            }
        } else {
            // Handle other intents, such as being started from the home screen
        }

        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getFingerprint = new Intent();
                String name = etName.getText().toString();
                int age = Integer.parseInt(etAge.getText().toString());
                getFingerprint.setAction(Intent.ACTION_SEND);
                Toast.makeText(MainActivity.this, "Nome e: " + name + " e a idade e: " + age, Toast.LENGTH_SHORT).show();
                getFingerprint.putExtra(Intent.EXTRA_TEXT, "Name:" + name + "|Age:" + age + ";");
                getFingerprint.setType("text/plain");
                startActivity(getFingerprint);
            }
        });
    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            Toast.makeText(MainActivity.this, "THIS IS THE TEXT: " + sharedText + " >Pegou?<", Toast.LENGTH_SHORT).show();
            // Update UI to reflect text being shared
        }
    }

    void handleSendImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            // Update UI to reflect image being shared
        }
    }

    void handleSendMultipleImages(Intent intent) {
        ArrayList<Uri> imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        if (imageUris != null) {
            // Update UI to reflect multiple images being shared
        }
    }
}
