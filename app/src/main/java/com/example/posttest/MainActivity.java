package com.example.posttest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseUlangan;
    FloatingActionButton btnTambah;
    ListView listViewItem;

    List<ulangan> ulanganList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseUlangan = FirebaseDatabase.getInstance().getReference().child("item");

        ulanganList = new ArrayList<>();
        listViewItem = (ListView)findViewById(R.id.listView);
        btnTambah = (FloatingActionButton)findViewById(R.id.btnTambah);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Tambah_Data.class);
                startActivity(intent);
            }
        });


        listViewItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        alertDialogBuilder.setTitle("Choose Option");

//        String[] pictureDialogItems = {
//                "Edit",
//                "Delete"
//        };

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Edit",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        Intent intent = new Intent(MainActivity.this, Tambah_Data.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Delete",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        DeleteItem();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    private void DeleteItem() {
        DatabaseReference drItem = FirebaseDatabase.getInstance().getReference("item");

        drItem.removeValue();

        Toast.makeText(this, "Item Delete", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseUlangan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ulanganList.clear();
                for (DataSnapshot ulanganSnapShot : dataSnapshot.getChildren()) {
                    ulangan item = ulanganSnapShot.getValue(ulangan.class);

                    ulanganList.add(item);
                }

                List_Adapter adapter = new List_Adapter(MainActivity.this, ulanganList);
                listViewItem.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
