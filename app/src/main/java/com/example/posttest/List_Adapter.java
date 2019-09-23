package com.example.posttest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class List_Adapter extends ArrayAdapter<ulangan> {
    private Activity context;
    private List<ulangan> ulanganList;


    public List_Adapter(Activity context, List<ulangan> ulanganList) {
        super(context, R.layout.activity_list_item, ulanganList);
        this.context = context;
        this.ulanganList = ulanganList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.activity_list_item, null, true);

        TextView textViewTanggal = (TextView) listViewItem.findViewById(R.id.txtTanggal);
        TextView textViewJudul = (TextView) listViewItem.findViewById(R.id.txtJudul2);
        TextView textViewDeskripsi = (TextView) listViewItem.findViewById(R.id.txtDeskripsi2);

        ulangan post = ulanganList.get(position);

        textViewTanggal.setText(post.getTanggal());
        textViewJudul.setText(post.getJudul());
        textViewDeskripsi.setText(post.getDeskripsi());

        return listViewItem;
    }
}
