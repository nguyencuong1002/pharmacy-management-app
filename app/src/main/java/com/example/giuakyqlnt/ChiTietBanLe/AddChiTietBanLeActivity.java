package com.example.giuakyqlnt.ChiTietBanLe;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.giuakyqlnt.HoaDon.ActivityHoaDon;
import com.example.giuakyqlnt.R;
import com.example.giuakyqlnt.Thuoc.ActivityThuoc;

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

    public boolean checkSoHD(String SoHD){
        String sql ="SELECT * FROM "+ ActivityHoaDon.TABLE_NAME +" WHERE '"+SoHD+"' = SoHD";
        Cursor cursor = ActivityChiTietBanLe.myDatabase.SelectData(sql);
        Log.d("CURSORcheckSOHD ", String.valueOf(cursor.getCount()));
        if(cursor.getCount()>0) {
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }
    public boolean checkMaThuoc(String MATHUOC){
        String sql ="SELECT * FROM "+ ActivityThuoc.TABLE_NAME +" WHERE '"+MATHUOC+"' = MATHUOC";
        Cursor cursor = ActivityChiTietBanLe.myDatabase.SelectData(sql);
        Log.d("CURSORcheckMATHUOC ", String.valueOf(cursor.getCount()));
        if(cursor.getCount()>0) {
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }
    @SuppressLint("LongLogTag")
    public boolean checkTrungMaThuoc_HoaDon(String MATHUOC, String SoHD){
        String sql ="SELECT * FROM "+ActivityChiTietBanLe.TABLE_NAME+" WHERE '"+MATHUOC+"' = MATHUOC AND '"+SoHD+"' =SoHD";
        Cursor cursor = ActivityChiTietBanLe.myDatabase.SelectData(sql);
        Log.d("CURSORCheckTrung_MATHUOC_SoHD", String.valueOf(cursor.getCount())+"");
        if(cursor.getCount()>0) {
            cursor.close();
            return false;
        }
        else {
            cursor.close();
            return true;
        }
    }
    private boolean CheckAllFields() {
        if (txtSOHD.length() == 0) {
            txtSOHD.setError("Vui lòng không để trống!");
            return false;
        }else if(!checkSoHD(txtSOHD.getText().toString())){
            txtSOHD.setError("Số Hóa đơn chưa được tạo, vui lòng chọn số Hóa đơn đã có");
            return false;
        }
        if (txtMATHUOC.length() == 0) {
            txtMATHUOC.setError("Vui lòng không để trống!");
            return false;
        }else if(!checkMaThuoc(txtMATHUOC.getText().toString())) {
            txtMATHUOC.setError("Mã thuốc chưa được tạo, vui lòng chọn Mã thuốc đã có");
            return false;
        }
        if (txtSOLUONG.length() == 0) {
            txtSOLUONG.setError("Vui lòng không để trống!");
            return false;
        } else if (!txtSOLUONG.getText().toString().matches("[0-9]+")) {
            txtSOLUONG.setError("Vui lòng chỉ nhập kí tự số!");
            return false;
        }
        if(!checkTrungMaThuoc_HoaDon(txtMATHUOC.getText().toString(),txtSOHD.getText().toString())) {
            Toast toast = Toast.makeText(AddChiTietBanLeActivity.this,"Chi tiết hóa đơn đã được tạo, vui lòng cập nhật lại! ",Toast.LENGTH_SHORT);
            // Gravity.CENTER = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;
            toast.setGravity(Gravity.CENTER, 20, 30);
            toast.show();
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
