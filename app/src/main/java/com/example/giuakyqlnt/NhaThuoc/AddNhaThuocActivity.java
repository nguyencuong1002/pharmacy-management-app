package com.example.giuakyqlnt.NhaThuoc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.giuakyqlnt.R;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNhaThuocActivity extends AppCompatActivity {
    EditText txtMaNT, txtTenNT, txtDiaChi;
    Button btnAdd, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nha_thuoc);
        mapping();
    }

    public void add(View view){
        String maNT = txtMaNT.getText().toString();
        String tenNT = txtTenNT.getText().toString();
        String diaChi = txtDiaChi.getText().toString();
        //Add dữ liệu
        ContentValues values = new ContentValues();
        values.put(ActivityNhaThuoc.MaNT_FIELD, maNT);
        values.put(ActivityNhaThuoc.TenNT_FIELD, tenNT);
        values.put(ActivityNhaThuoc.DiaChi_FIELD, diaChi);
        ActivityNhaThuoc.myDatabase.insert(ActivityNhaThuoc.TABLE_NAME, null,values);
        startActivity(new Intent(AddNhaThuocActivity.this, ActivityNhaThuoc.class));
    }
    public void cancle(View view){
        startActivity(new Intent(AddNhaThuocActivity.this, ActivityNhaThuoc.class));
    }

    private void mapping(){
        txtMaNT = findViewById(R.id.txtMaNT);
        txtTenNT = findViewById(R.id.txtTenNT);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
    }
}