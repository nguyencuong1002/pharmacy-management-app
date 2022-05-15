package com.example.giuakyqlnt;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.example.giuakyqlnt.ChiTietBanLe.ActivityChiTietBanLe;
import com.example.giuakyqlnt.HoaDon.ActivityHoaDon;
import com.example.giuakyqlnt.NhaThuoc.ActivityNhaThuoc;
import com.example.giuakyqlnt.Thuoc.ActivityThuoc;
import com.google.android.material.bottomnavigation.BottomNavigationView;

//import com.example.giuakyqlnt.LoginRegister.ActivityProfile;

public class MainActivity extends AppCompatActivity {
    CardView cvNhaThuoc, cvThuoc, cvHoaDon, cvBanThuoc;
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        cvNhaThuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ActivityNhaThuoc.class));
            }
        });

        cvHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ActivityHoaDon.class));
            }
        });
        cvThuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ActivityThuoc.class));
            }
        });
        cvBanThuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ActivityChiTietBanLe.class));
            }
        });

        navigationView.setSelectedItemId(R.id.action_home);
//        Bottom Navigation
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        return true;

                    case R.id.action_map:
                        startActivity(new Intent(MainActivity.this, ActivityMap.class));
                        break;

                    case R.id.action_chart:
                        startActivity(new Intent(MainActivity.this, ActivityThongTinBanLe.class));
                        break;

                    case R.id.action_setting:
                        startActivity(new Intent(MainActivity.this, ActivityThuoc.class));
                        break;
                }
                return true;
            }
        });
    }

    public void mapping(){
        cvNhaThuoc = findViewById(R.id.cvNhaThuoc);
        cvThuoc = findViewById(R.id.cvThuoc);
        cvHoaDon = findViewById(R.id.cvHoaDon);
        cvBanThuoc = findViewById(R.id.cvBanThuoc);
        navigationView = findViewById(R.id.bottom_nav);
    }
}
