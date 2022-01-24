package com.example.electrilumnyy.AdminSide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.electrilumnyy.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Setting_Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_admin);

        // inisialisasi bottom navigasi
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        // fungsi button navigasi
        bottomNavigationView.setSelectedItemId(R.id.menu_setting);
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
                        startActivity(new Intent(getApplicationContext(),Event_Admin.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu_setting:

                        return true;
                }
                return false;
            }
        });
    }
}