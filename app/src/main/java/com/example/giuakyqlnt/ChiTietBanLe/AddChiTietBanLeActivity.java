package com.example.giuakyqlnt.ChiTietBanLe;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giuakyqlnt.R;

public class AddChiTietBanLeActivity extends AppCompatActivity {
    EditText txtSOHD, txtMATHUOC, txtSOLUONG;
    Button btnAdd, btnCancel;
    ImageView ivBack;
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chi_tiet_ban_le);
        mapping();
        setEvent();
    }

    public void add(View view){
        String SOHD = txtSOHD.getText().toString();
        String MATHUOC = txtMATHUOC.getText().toString();
        String SOLUONG = txtSOLUONG.getText().toString();
        //Check data
        isAllFieldsChecked = CheckAllFields();
        if (isAllFieldsChecked) {
            //Add dữ liệu
            ContentValues values = new ContentValues();
            values.put(ActivityChiTietBanLe.SOHD_FIELD, SOHD);
            values.put(ActivityChiTietBanLe.MATHUOC_FIELD, MATHUOC);
            values.put(ActivityChiTietBanLe.SOLUONG_FIELD, SOLUONG);
            ActivityChiTietBanLe.myDatabase.insert(ActivityChiTietBanLe.TABLE_NAME, null,values);
            startActivity(new Intent(com.example.giuakyqlnt.ChiTietBanLe.AddChiTietBanLeActivity.this, ActivityChiTietBanLe.class));
        }
    }

    private boolean CheckAllFields() {
        if (txtSOHD.length() == 0) {
            txtSOHD.setError("Vui lòng không để trống!");
            return false;
        }
        if (txtMATHUOC.length() == 0) {
            txtMATHUOC.setError("Vui lòng không để trống!");
            return false;
        }
        if (txtSOLUONG.length() == 0) {
            txtSOLUONG.setError("Vui lòng không để trống!");
            return false;
        } else if (!txtSOLUONG.getText().toString().matches("[0-9]+")) {
            txtSOLUONG.setError("Vui lòng chỉ nhập kí tự số!");
            return false;
        }
        return true;
    }

    public void cancle(View view){
        startActivity(new Intent(com.example.giuakyqlnt.ChiTietBanLe.AddChiTietBanLeActivity.this, ActivityChiTietBanLe.class));
    }

    public void setEvent(){
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddChiTietBanLeActivity.this, ActivityChiTietBanLe.class));
            }
        });
    }

    private void mapping(){
        txtSOHD = findViewById(R.id.txtSOHD);
        txtMATHUOC = findViewById(R.id.txtMATHUOC);
        txtSOLUONG = findViewById(R.id.txtSOLUONG);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        ivBack = findViewById(R.id.ivBack);

    }


}