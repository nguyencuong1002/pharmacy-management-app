package com.example.giuakyqlnt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.giuakyqlnt.HoaDon.ActivityHoaDon;
import com.example.giuakyqlnt.NhaThuoc.ActivityNhaThuoc;

public class MainActivity extends AppCompatActivity {
    CardView cvNhaThuoc, cvThuoc, cvHoaDon, cvBanThuoc;
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


    }

    public void mapping(){
        cvNhaThuoc = findViewById(R.id.cvNhaThuoc);
        cvThuoc = findViewById(R.id.cvThuoc);
        cvHoaDon = findViewById(R.id.cvHoaDon);
        cvBanThuoc = findViewById(R.id.cvBanThuoc);
    }
}