package com.example.electrilumnyy.AdminSide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.electrilumnyy.DbContact;
import com.example.electrilumnyy.MainActivity;
import com.example.electrilumnyy.R;
import com.example.electrilumnyy.RegisterActivity;
import com.example.electrilumnyy.VolleyConnection;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Event_TambahPage extends AppCompatActivity {

    Button btnBatalAkses, btnPostEvent;
    EditText et_namaEvent, et_deskripsiEvent,  et_alamatEvent;
    TextView et_tglEvent;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener setListener;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_tambah_page);

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

        //inisialisasi button
        btnBatalAkses = findViewById(R.id.btn_batalTambahEvent);
        btnPostEvent = findViewById(R.id.btn_postEvent);

        //fungsi button Batal
        btnBatalAkses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Event_TambahPage.this, Event_Admin.class);
                startActivity(i);
            }
        });

        //inisialisasi Progress Dialog
        progressDialog = new ProgressDialog(Event_TambahPage.this);

        // inisialisasi Edit Text
        et_namaEvent = findViewById(R.id.et_namaEvent);
        et_deskripsiEvent = findViewById(R.id.et_deskripsiEvent);
        et_alamatEvent = findViewById(R.id.et_alamatEvent);
        et_tglEvent = findViewById(R.id.et_tglEvent);

        myCalendar = Calendar.getInstance();

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TextView tanggal =  findViewById(R.id.et_tglEvent);
                String myFormat = "dd-MMMM-yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                tanggal.setText(sdf.format(myCalendar.getTime()));
            }
        };

        et_tglEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Event_TambahPage.this, setListener,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });

        btnPostEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama_event = et_namaEvent.getText().toString();
                String tanggal_event = et_tglEvent.getText().toString();
                String deskripsi_event = et_deskripsiEvent.getText().toString();
                String alamat_event = et_alamatEvent.getText().toString();

                if (!nama_event.equals("") && !deskripsi_event.equals("") && !alamat_event.equals("")){
                    CreateDataToServer(nama_event, tanggal_event,deskripsi_event, alamat_event);
                    Intent createEvent = new Intent(Event_TambahPage.this, Event_Admin.class);
                    startActivity(createEvent);
                } else{
                    Toast.makeText(getApplicationContext(),"Tambah Event Berhasil", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    public void CreateDataToServer(final String nama_event, final String tanggal_event, final String deskripsi_event, final String alamat_event) {
        if (checkNetworkConnection()) {
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContact.SERVER_CREATE_EVENT_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String resp = jsonObject.getString("server_response");
                                if (resp.equals("[{\"status\":\"OK\"}]")){
                                    Toast.makeText(getApplicationContext(), "Tambah Event Berhasil", Toast.LENGTH_SHORT).show();
                                } else{
                                    Toast.makeText(getApplicationContext(), resp, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("nama_event", nama_event);
                    params.put("tgl_pelaksanaan", tanggal_event);
                    params.put("deskripsi_acara", deskripsi_event);
                    params.put("lokasi_acara", alamat_event);
                    return params;
                }
            };

            VolleyConnection.getInstance(Event_TambahPage.this).addToRequestQue(stringRequest);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.cancel();
                }
            }, 2000);
        } else {
            Toast.makeText(getApplicationContext(), "Tidak ada Koneksi Internet", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkNetworkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return(networkInfo != null && networkInfo.isConnected());
    }
}