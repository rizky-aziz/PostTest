package com.example.posttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Tambah_Data extends AppCompatActivity {

    EditText edtJudul, edtDeskripsi;
    Button btnTambah;

    DatabaseReference databaseUlangan;

    List<ulangan> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah__data);

        databaseUlangan = FirebaseDatabase.getInstance().getReference("item");

        edtJudul = (EditText)findViewById(R.id.edtJudul);
        edtDeskripsi = (EditText)findViewById(R.id.edtDeskripsi);
        btnTambah = (Button)findViewById(R.id.btnSimpan);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TambbahData();
            }
        });
    }
    public void TambbahData() {
        String judul = edtJudul.getText().toString().trim();
        String deskripsi = edtDeskripsi.getText().toString().trim();
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy' 'hh:mm:ss");
        String tanggal = date.format(new Date());

        if (!TextUtils.isEmpty(judul)) {
            String id = databaseUlangan.push().getKey();

            ulangan item = new ulangan(id, tanggal, judul, deskripsi);

            databaseUlangan.child(id).setValue(item);

            Toast.makeText(this,"berhasil ditambah", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Harus disisi", Toast.LENGTH_LONG).show();
        }
    }
}
