package com.example.electrilumnyy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity  {

    Button btn_AksesLogin, btn_Register;
    EditText etNamaLengkap, etEmail, etPassword, etPasswordReenter, etTahunAngkatan, etJenisPendidikan;
    //Spinner spinRole;
    RadioGroup rbRole;
    RadioButton roleTerpilih;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //inisialisasi Edit Text
        etNamaLengkap = findViewById(R.id.et_namaLengkap);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etPasswordReenter = findViewById(R.id.et_passwordReenter);
        etTahunAngkatan = findViewById(R.id.et_thAngkatan);
        etJenisPendidikan = findViewById(R.id.et_jenisPendidikan);

        //inisialisasi Button
        btn_AksesLogin = findViewById(R.id.btnAksesLogin);
        btn_Register = findViewById(R.id.btnRegister);

        //inisialisasi RadioButton
        rbRole = findViewById(R.id.rbRole);
        //rbJenisPendidikan = findViewById(R.id.rbJenisPendidikan);


        //inisialisasi Progress Dialog
        progressDialog = new ProgressDialog(RegisterActivity.this);

        //fungsi Button Akses Register
        btn_AksesLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        //fungsi Button Register
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama_lengkap = etNamaLengkap.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String confPassword = etPasswordReenter.getText().toString();

                int role_terpilih = rbRole.getCheckedRadioButtonId();
                roleTerpilih = findViewById(role_terpilih);
                String role = roleTerpilih.getText().toString();

                String th_angkatan = etTahunAngkatan.getText().toString();
                String jenis_pendidikan = etJenisPendidikan.getText().toString();

//                int jenisPendidikan_terpilih = rbJenisPendidikan.getCheckedRadioButtonId();
//                jenisPendidikanTerpilih = findViewById(jenisPendidikan_terpilih);
//                String jenis_pendidikan = jenisPendidikanTerpilih.getText().toString();

                if (password.equals(confPassword) && !password.equals("")){
                    CreateDataToServer(nama_lengkap, email, password, role, th_angkatan, jenis_pendidikan);
                    Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(loginIntent);
                } else{
                    Toast.makeText(getApplicationContext(),"Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void CreateDataToServer(final String nama_lengkap, final String email, final String password, final String role, final String th_angkatan, final String jenis_pendidikan){
        if (checkNetworkConnection()){
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContact.SERVER_REGISTER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String resp = jsonObject.getString("server_response");
                                if (resp.equals("[{\"status\":\"OK\"}]")){
                                    Toast.makeText(getApplicationContext(), "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
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
                    params.put("nama_lengkap", nama_lengkap);
                    params.put("email", email);
                    params.put("password", password);
                    params.put("role", role);
                    params.put("th_angkatan", th_angkatan);
                    params.put("jenis_pendidikan", jenis_pendidikan);
                    return params;
                }
            };

            VolleyConnection.getInstance(RegisterActivity.this).addToRequestQue(stringRequest);

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