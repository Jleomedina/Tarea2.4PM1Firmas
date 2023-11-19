package com.example.tarea24pm1firmas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

        findViewById(R.id.btnSaveSignature).setOnClickListener(view -> {
            // Get the description from the EditText
            EditText editDescription = findViewById(R.id.editDescription);
            String description = editDescription.getText().toString();

            // Get the signature from your SignatureView (replace with your actual implementation)
            Bitmap signatureBitmap = signatureView.getBitmap();

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            signatureBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            Signatures signature = new Signatures(description, byteArray);
            long insertedRowId = dataSource.insertSignature(signature);

            if (insertedRowId != -1) {
                // Signature saved successfully, clear the signature view
                signatureView.clear();
                // Optionally, you can also clear the description
                editDescription.setText("");
            } else {
                // Handle the case when saving the signature fails
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



