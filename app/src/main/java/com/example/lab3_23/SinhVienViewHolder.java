package com.example.lab3_23;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SinhVienViewHolder extends RecyclerView.ViewHolder {

    TextView itemSV;
    RelativeLayout cardView;
    public SinhVienViewHolder(@NonNull View itemView) {
        super(itemView);
        itemSV = itemView.findViewById(R.id.itemSV);
        cardView = itemView.findViewById(R.id.cardView);
    }
}
