package com.example.tarea24pm1firmas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SignaturesDataSource {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public SignaturesDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertSignature(Signatures signature) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_DESCRIPTION, signature.getDescription());
        values.put(DatabaseHelper.COLUMN_SIGNATURE, signature.getDigitalSignature());
        return database.insert(DatabaseHelper.TABLE_NAME, null, values);
    }

    public List<Signatures> getAllSignatures() {
        List<Signatures> signatureList = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String description = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRIPTION));
                byte[] signature = cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COLUMN_SIGNATURE));

                Signatures signatures = new Signatures(description, signature);
                signatureList.add(signatures);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return signatureList;
    }
}

