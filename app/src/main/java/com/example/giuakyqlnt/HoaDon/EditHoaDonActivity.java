package com.example.giuakyqlnt.HoaDon;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giuakyqlnt.HoaDon.ActivityHoaDon;
import com.example.giuakyqlnt.HoaDon.HoaDon;
import com.example.giuakyqlnt.R;

public class EditHoaDonActivity extends AppCompatActivity {
    TextView tvSoHD;
    EditText txtNgayHD, txtMaNT;
    Button btnAdd, btnCancel;
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hoa_don);
        mapping();
        getData();
    }

    public void getData(){
        HoaDon hoaDon = (HoaDon) getIntent().getSerializableExtra("hoadon");
        Log.d("AAA", hoaDon + "is ok");
        tvSoHD.setText(hoaDon.getSoHD());
        //txtBookID.setEnabled(false);
        txtNgayHD.setText(hoaDon.getNgayHD());
        txtMaNT.setText(hoaDon.getMaNT());
    }

    public void save(View view){
        String soHD = tvSoHD.getText().toString();
        String ngayHD = txtNgayHD.getText().toString();
        String maNT = txtMaNT.getText().toString();
        //Check data
        isAllFieldsChecked = CheckAllFields();
        if (isAllFieldsChecked) {
            ContentValues values = new ContentValues();
            values.put(ActivityHoaDon.NgayHD_FIELD, ngayHD);
            values.put(ActivityHoaDon.MaNT_FIELD, maNT);
            String whereClause = ""+ActivityHoaDon.SoHD_FIELD+" = ?";
            String[] whereArgs = {soHD};
            ActivityHoaDon.myDatabase.update(ActivityHoaDon.TABLE_NAME, values, whereClause, whereArgs);
            startActivity(new Intent(EditHoaDonActivity.this, ActivityHoaDon.class));
        }
        //Update dữ liệu
    }

    private boolean CheckAllFields() {
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
        startActivity(new Intent(EditHoaDonActivity.this, ActivityHoaDon.class));
    }

    private void mapping(){
        tvSoHD = findViewById(R.id.tvSoHD);
        txtNgayHD = findViewById(R.id.txtNgayHD);
        txtMaNT = findViewById(R.id.txtMaNT);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
    }
}