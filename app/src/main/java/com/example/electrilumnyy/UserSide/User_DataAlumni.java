package com.example.electrilumnyy.UserSide;

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

import com.example.electrilumnyy.AdminSide.Data;
import com.example.electrilumnyy.AdminSide.DataAlumni_Admin;
import com.example.electrilumnyy.AdminSide.Event_Admin;
import com.example.electrilumnyy.AdminSide.RecyclerAdapter;
import com.example.electrilumnyy.AdminSide.Setting_Admin;
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

public class User_DataAlumni extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdapterUser adapter;
    ArrayList<ListDataAlumni> arrayList = new ArrayList<>();
    String DATA_JSON_STRING, data_json_string;
    ProgressDialog progressDialog;
    int countData = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_alumni);

        // inisialisasi bottom navigasi
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        // fungsi button navigasi
        bottomNavigationView.setSelectedItemId(R.id.menu_dataAlumni);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home:
                        startActivity(new Intent(getApplicationContext(), Dashboard_User.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu_dataAlumni:

                        return true;
                    case R.id.menu_event:
                        startActivity(new Intent(getApplicationContext(), User_Event.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu_setting:
                        startActivity(new Intent(getApplicationContext(), User_Setting.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        // inisialisasi recycler View
        recyclerView = (RecyclerView) findViewById(R.id.list_data_admin);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapterUser(arrayList);
        recyclerView.setAdapter(adapter);
        progressDialog = new ProgressDialog(User_DataAlumni.this);

        //Read Data
        getJSON();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                readDataAlumni();
            }
        }, 1000);

    }

    public void readDataAlumni(){
        if(checkNetworkConnection()){
            arrayList.clear();
            try {
                JSONObject object = new JSONObject(data_json_string);
                JSONArray serverResponse = object.getJSONArray("server_response");
                String nama_lengkap, email, th_angkatan, jenis_pendidikan;

                while (countData < serverResponse.length()){
                    JSONObject jsonObject = serverResponse.getJSONObject(countData);
                    nama_lengkap = jsonObject.getString("nama_lengkap");
                    email = jsonObject.getString("email");
                    th_angkatan = jsonObject.getString("th_angkatan");
                    jenis_pendidikan = jsonObject.getString("jenis_pendidikan");

                    arrayList.add(new ListDataAlumni(nama_lengkap, email, th_angkatan, jenis_pendidikan));
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
            json_url = DbContact.SERVER_READDATAALUMNI_URL;
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
