package com.example.giuakyqlnt.HoaDon;

import static com.example.giuakyqlnt.R.id.btnCancel;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giuakyqlnt.NhaThuoc.ActivityNhaThuoc;
import com.example.giuakyqlnt.NhaThuoc.AddNhaThuocActivity;
import com.example.giuakyqlnt.R;

public class AddHoaDonActivity extends AppCompatActivity {
    ActivityHoaDon HD;
    EditText txtSoHD, txtNgayHD, txtMaNT;
    Button btnAdd, btnCancel;
    ImageView ivBack;
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hoa_don);
        mapping();
        setEvent();
    }

    public void add(View view){
        String soHD = txtSoHD.getText().toString();
        String ngayHD = txtNgayHD.getText().toString();
        String maNT = txtMaNT.getText().toString();
        //Check data
        isAllFieldsChecked = CheckAllFields();
        if (isAllFieldsChecked) {
            //Add dữ liệu
            ContentValues values = new ContentValues();
            values.put(ActivityHoaDon.SoHD_FIELD, soHD);
            values.put(ActivityHoaDon.NgayHD_FIELD, ngayHD);
            values.put(ActivityHoaDon.MaNT_FIELD, maNT);
            ActivityHoaDon.myDatabase.insert(ActivityHoaDon.TABLE_NAME, null,values);
            startActivity(new Intent(AddHoaDonActivity.this, ActivityHoaDon.class));
        }
    }

    private boolean CheckAllFields() {
        if (txtSoHD.length() == 0) {
            txtSoHD.setError("Vui lòng không để trống!");
            return false;
        } else if (!txtSoHD.getText().toString().matches("[a-zA-Z0-9]+")) {
            txtSoHD.setError("Vui lòng chỉ nhập kí tự chữ hoặc số!");
            return false;
        }else if(HD.myDatabase.checkExistID(HD.TABLE_NAME, HD.SoHD_FIELD, txtSoHD.getText().toString())){
            txtSoHD.setError("Mã nhà thuốc đã tồn tại!");
            return false;
        }
        if (txtNgayHD.length() == 0) {
            txtNgayHD.setError("Vui lòng không để trống!");
            return false;
        }
        if (txtMaNT.length() == 0) {
            txtMaNT.setError("Vui lòng không để trống!");
            return false;
        }
        return true;
    }

    public void cancle(View view){
        startActivity(new Intent(AddHoaDonActivity.this, ActivityHoaDon.class));
    }

    public void setEvent(){
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddHoaDonActivity.this, ActivityHoaDon.class));
            }
        });
    }

    private void mapping(){
        txtSoHD = findViewById(R.id.txtSoHD);
        txtNgayHD = findViewById(R.id.txtNgayHD);
        txtMaNT = findViewById(R.id.txtMaNT);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        ivBack = findViewById(R.id.ivBack);
    }
}