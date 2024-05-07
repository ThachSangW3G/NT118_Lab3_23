package com.example.lab3_23;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SinhVienAdapter extends RecyclerView.Adapter<SinhVienViewHolder> {

    private  List<SinhVien> listSV;
    private OnItemClickListener listener;

    public SinhVienAdapter(List<SinhVien> listSV, OnItemClickListener listener) {
        this.listSV = listSV;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SinhVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SinhVienViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SinhVienViewHolder holder, int position) {
        holder.itemSV.setText(listSV.get(position).getMssv() + " - " + listSV.get(position).getHoten() + " - " + listSV.get(position).getLop());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(listSV.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSV.size();
    }



    public void addStudent(SinhVien sinhvien) {
        listSV.add(sinhvien);
        notifyItemInserted(listSV.size() - 1);
    }

    public void clearStudent(){
        listSV.clear();
        listSV.forEach(sinhVien -> {
            int index = listSV.indexOf(sinhVien);
            if (index != -1) {
                listSV.remove(index);
                notifyItemRemoved(index);
            }
        });
    }

    public void updateStudent(SinhVien sinhVien) {
        int index = listSV.indexOf(sinhVien);
        if (index != -1) {
            listSV.set(index, sinhVien);
            notifyItemChanged(index);
        }
    }

    public void deleteStudent(SinhVien sinhVien) {
        int index = listSV.indexOf(sinhVien);
        if (index != -1) {
            listSV.remove(index);
            notifyItemRemoved(index);
        }
    }
}
