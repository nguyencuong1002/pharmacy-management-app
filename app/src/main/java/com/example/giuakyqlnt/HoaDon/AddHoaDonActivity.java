package com.example.giuakyqlnt.HoaDon;

import static com.example.giuakyqlnt.R.id.btnCancel;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giuakyqlnt.NhaThuoc.ActivityNhaThuoc;
import com.example.giuakyqlnt.R;

public class AddHoaDonActivity extends AppCompatActivity {
    EditText txtSoHD, txtNgayHD, txtMaNT;
    Button btnAdd, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hoa_don);
        mapping();
    }

    public void add(View view){
        String soHD = txtSoHD.getText().toString();
        String ngayHD = txtNgayHD.getText().toString();
        String maNT = txtMaNT.getText().toString();
        //Add dữ liệu
        ContentValues values = new ContentValues();
        values.put(ActivityHoaDon.SoHD_FIELD, soHD);
        values.put(ActivityHoaDon.NgayHD_FIELD, ngayHD);
        values.put(ActivityHoaDon.MaNT_FIELD, maNT);
        ActivityHoaDon.myDatabase.insert(ActivityHoaDon.TABLE_NAME, null,values);
        startActivity(new Intent(AddHoaDonActivity.this, ActivityHoaDon.class));
    }
    public void cancle(View view){
        startActivity(new Intent(AddHoaDonActivity.this, ActivityHoaDon.class));
    }

    private void mapping(){
        txtSoHD = findViewById(R.id.txtSoHD);
        txtNgayHD = findViewById(R.id.txtNgayHD);
        txtMaNT = findViewById(R.id.txtMaNT);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
    }
}