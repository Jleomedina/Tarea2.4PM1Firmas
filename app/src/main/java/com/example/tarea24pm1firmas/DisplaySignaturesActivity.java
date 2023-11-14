package com.example.tarea24pm1firmas;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DisplaySignaturesActivity extends AppCompatActivity {

    private SignaturesDataSource dataSource;
    private RecyclerView recyclerView;
    private SignaturesAdapter signaturesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_signatures);

        dataSource = new SignaturesDataSource(this);
        dataSource.open();

        recyclerView = findViewById(R.id.signaturesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Signatures> signatureList = dataSource.getAllSignatures();

        signaturesAdapter = new SignaturesAdapter(this, signatureList);
        recyclerView.setAdapter(signaturesAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataSource.close();
    }
}


