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
    boolean isAllFieldsChecked = false;

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
        //Check data
        isAllFieldsChecked = CheckAllFields();
        if (isAllFieldsChecked) {
            //Add dữ liệu
        ContentValues values = new ContentValues();
        values.put(ActivityNhaThuoc.MaNT_FIELD, maNT);
        values.put(ActivityNhaThuoc.TenNT_FIELD, tenNT);
        values.put(ActivityNhaThuoc.DiaChi_FIELD, diaChi);
        ActivityNhaThuoc.myDatabase.insert(ActivityNhaThuoc.TABLE_NAME, null,values);
        startActivity(new Intent(AddNhaThuocActivity.this, ActivityNhaThuoc.class));
        }
    }
    private boolean CheckAllFields() {
        if (txtMaNT.length() == 0) {
            txtMaNT.setError("Vui lòng không để trống!");
            return false;
        } else if (!txtMaNT.getText().toString().matches("[a-zA-Z0-9]+")) {
            txtMaNT.setError("Vui lòng chỉ nhập kí tự chữ hoặc số!");
            return false;
        }
        if (txtDiaChi.length() == 0) {
            txtDiaChi.setError("Vui lòng không để trống!");
            return false;
        }
        if (txtTenNT.length() == 0) {
            txtTenNT.setError("Vui lòng không để trống!");
            return false;
        }
        return true;
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
