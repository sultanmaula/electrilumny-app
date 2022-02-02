package com.example.electrilumnyy.AdminSide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electrilumnyy.R;

import java.util.ArrayList;

public class RecyclerAdapterEvent extends RecyclerView.Adapter<RecyclerAdapterEvent.myViewHolder> {

    private ArrayList<Data_Event> arrayList = new ArrayList<>();

    public RecyclerAdapterEvent(ArrayList<Data_Event> arrayList){
        this.arrayList = arrayList;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data_event, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        holder.nama_event.setText(arrayList.get(position).getNama_event());
        holder.deskripsi_event.setText(arrayList.get(position).getDeskripsi_event());
        holder.tgl_event.setText(arrayList.get(position).getTgl_event());
        holder.lokasi_event.setText(arrayList.get(position).getLokasi_event());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        TextView nama_event, deskripsi_event, tgl_event, lokasi_event;

        public myViewHolder(View itemView) {
            super(itemView);

            nama_event = itemView.findViewById(R.id.txt_namaEvent);
            deskripsi_event = itemView.findViewById(R.id.txt_deskripsiEvent);
            tgl_event = itemView.findViewById(R.id.txt_tglEvent);
            lokasi_event = itemView.findViewById(R.id.txt_alamatEvent);
        }
    }
}
