package com.example.electrilumnyy.UserSide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.electrilumnyy.AdminSide.DataAlumni_Admin;
import com.example.electrilumnyy.AdminSide.Event_Admin;
import com.example.electrilumnyy.AdminSide.Setting_Admin;
import com.example.electrilumnyy.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class User_DataAlumni extends AppCompatActivity {

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
    }
}