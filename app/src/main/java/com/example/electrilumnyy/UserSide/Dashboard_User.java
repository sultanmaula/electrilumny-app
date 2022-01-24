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

public class Dashboard_User extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_user);

        // inisialisasi bottom navigasi
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        // fungsi button navigasi
        bottomNavigationView.setSelectedItemId(R.id.menu_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home:
                        return true;
                    case R.id.menu_dataAlumni:
                        startActivity(new Intent(getApplicationContext(), User_DataAlumni.class));
                        overridePendingTransition(0,0);
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