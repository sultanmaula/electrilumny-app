package com.example.electrilumnyy.UserSide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.electrilumnyy.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class User_Event extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_event);

        // inisialisasi bottom navigasi
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        // fungsi button navigasi
        bottomNavigationView.setSelectedItemId(R.id.menu_event);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home:
                        startActivity(new Intent(getApplicationContext(), Dashboard_User.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu_dataAlumni:
                        startActivity(new Intent(getApplicationContext(), User_DataAlumni.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu_event:

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