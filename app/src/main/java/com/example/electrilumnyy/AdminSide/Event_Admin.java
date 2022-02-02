package com.example.electrilumnyy.AdminSide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.electrilumnyy.DbContact;
import com.example.electrilumnyy.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Event_Admin extends AppCompatActivity {

    Button btnTambahEvent;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdapterEvent adapter;
    ArrayList<Data_Event> arrayList = new ArrayList<>();
    String DATA_JSON_STRING, data_json_string;
    ProgressDialog progressDialog;
    int countData = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_admin);

        // inisialisasi bottom navigasi
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        // fungsi button navigasi
        bottomNavigationView.setSelectedItemId(R.id.menu_event);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home:
                        startActivity(new Intent(getApplicationContext(),Dashboard_Admin.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu_dataAlumni:
                        startActivity(new Intent(getApplicationContext(),DataAlumni_Admin.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu_event:

                        return true;
                    case R.id.menu_setting:
                        startActivity(new Intent(getApplicationContext(),Setting_Admin.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        //inisialisasi Button
        btnTambahEvent = findViewById(R.id.btn_tambahEvent);

        // aksi button tambah akses
        btnTambahEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Event_Admin.this, Event_TambahPage.class);
                startActivity(i);
            }
        });

        //inisialisasi RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.list_data_event);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapterEvent(arrayList);
        recyclerView.setAdapter(adapter);
        progressDialog = new ProgressDialog(Event_Admin.this);

        //Read Data
        getJSON();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                readDataEvent();
            }
        }, 1000);
    }

    public void readDataEvent(){
        if(checkNetworkConnection()){
            arrayList.clear();
            try {
                JSONObject object = new JSONObject(data_json_string);
                JSONArray serverResponse = object.getJSONArray("server_response");
                String nama_event, deskripsi_event, tgl_event, lokasi_event;

                while (countData < serverResponse.length()){
                    JSONObject jsonObject = serverResponse.getJSONObject(countData);
                    nama_event = jsonObject.getString("nama_event");
                    deskripsi_event = jsonObject.getString("deskripsi_acara");
                    tgl_event = jsonObject.getString("tgl_pelaksanaan");
                    lokasi_event = jsonObject.getString("lokasi_acara");

                    arrayList.add(new Data_Event(nama_event, deskripsi_event, tgl_event, lokasi_event));
                    countData++;
                }
                countData = 0;

                adapter.notifyDataSetChanged();
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
    }

    public void getJSON(){
        new backgroundTask().execute();
    }

    public class backgroundTask extends AsyncTask<Void, Void, String> {

        String json_url;

        @Override
        protected void onPreExecute() {
            json_url = DbContact.SERVER_READ_EVENT_URL;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                while((DATA_JSON_STRING = bufferedReader.readLine()) != null){
                    stringBuilder.append(DATA_JSON_STRING + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            data_json_string = result;
        }
    }

    public boolean checkNetworkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return(networkInfo != null && networkInfo.isConnected());
    }
}