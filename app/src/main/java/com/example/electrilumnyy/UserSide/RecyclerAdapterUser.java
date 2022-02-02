package com.example.electrilumnyy.UserSide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.electrilumnyy.AdminSide.Data;
import com.example.electrilumnyy.AdminSide.RecyclerAdapter;
import com.example.electrilumnyy.R;

import java.util.ArrayList;

public class RecyclerAdapterUser extends RecyclerView.Adapter<RecyclerAdapterUser.myViewHolder> {

    private ArrayList<ListDataAlumni> arrayList = new ArrayList<>();

    public RecyclerAdapterUser(ArrayList<ListDataAlumni> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data_admin, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        holder.nama_lengkap.setText(arrayList.get(position).getNama_lengkap());
        holder.email.setText(arrayList.get(position).getEmail());
        holder.th_angkatan.setText(arrayList.get(position).getTh_angkatan());
        holder.jenis_pendidikan.setText(arrayList.get(position).getJenis_pendidikan());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        TextView nama_lengkap, email, th_angkatan, jenis_pendidikan;

        public myViewHolder(View itemView) {
            super(itemView);

            nama_lengkap = itemView.findViewById(R.id.txt_nama);
            email = itemView.findViewById(R.id.txt_email);
            th_angkatan = itemView.findViewById(R.id.txt_thAngkatan);
            jenis_pendidikan = itemView.findViewById(R.id.txt_jenisPendidikan);
        }
    }
}
