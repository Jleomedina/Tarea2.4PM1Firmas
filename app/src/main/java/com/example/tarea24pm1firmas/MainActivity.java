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


        findViewById(R.id.btnSaveSignature).setOnClickListener(view -> {

            Bitmap signatureBitmap = signatureView.getBitmap();


            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            signatureBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();


            Signatures signature = new Signatures("Description Example", byteArray);
            long insertedRowId = dataSource.insertSignature(signature);

            if (insertedRowId != -1) {


                signatureView.clear();
            } else {

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


