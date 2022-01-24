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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.electrilumnyy.AdminSide.Dashboard_Admin;
import com.example.electrilumnyy.UserSide.Dashboard_User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btn_AksesRegister, btn_Login;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inisialisasi editText
        etEmail = findViewById(R.id.etL_email);
        etPassword = findViewById(R.id.etL_password);

        // inisialisasi button
        btn_AksesRegister = findViewById(R.id.btnAksesRegister);
        btn_Login = findViewById(R.id.btnLogin);

        //inisialisasi Progress Dialog
        progressDialog = new ProgressDialog(MainActivity.this);

        // fungsi Button Akses Register
        btn_AksesRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        // fungsi Button Login
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                checkLogin(email,password);
            }
        });
    }

    public void checkLogin(final String email, final String password){
        if (checkNetworkConnection()){
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContact.SERVER_LOGIN_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String resp = jsonObject.getString("server_response");
                                if (resp.equals("[{\"status\":\"OK\",\"role\":\"admin\"}]")){
                                        Toast.makeText(getApplicationContext(), "Login Berhasil Sebagai Admin", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(MainActivity.this, Dashboard_Admin.class);
                                        startActivity(i);

                                } else if (resp.equals("[{\"status\":\"OK\",\"role\":\"user\"}]")){
                                    Toast.makeText(getApplicationContext(), "Login Berhasil Sebagai User", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(MainActivity.this, Dashboard_User.class);
                                    startActivity(i);
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
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("email", email);
                    params.put("password", password);
                    return params;
                }
            };

            VolleyConnection.getInstance(MainActivity.this).addToRequestQue(stringRequest);

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