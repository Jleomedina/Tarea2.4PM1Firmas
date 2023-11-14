package com.example.tarea24pm1firmas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    private SignaturesDataSource dataSource;
    private SignatureView signatureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSource = new SignaturesDataSource(this);
        dataSource.open();

        signatureView = findViewById(R.id.signatureView);

        // Example: Set up a click listener for the "Save Signature" button
        findViewById(R.id.btnSaveSignature).setOnClickListener(view -> {
            // Get the bitmap of the signature
            Bitmap signatureBitmap = signatureView.getBitmap();

            // Convert the bitmap to a byte array (for storing in the database)
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            signatureBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            // Example: Save a signature when the button is clicked
            Signatures signature = new Signatures("Description Example", byteArray);
            long insertedRowId = dataSource.insertSignature(signature);

            if (insertedRowId != -1) {
                // Signature saved successfully
                // You can add a Toast or other feedback here

                // Clear the SignatureView after saving
                signatureView.clear();
            } else {
                // Error saving signature
                // You can add a Toast or other error handling here
            }
        });


        findViewById(R.id.btnViewSignatures).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DisplaySignaturesActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataSource.close();
    }
}


