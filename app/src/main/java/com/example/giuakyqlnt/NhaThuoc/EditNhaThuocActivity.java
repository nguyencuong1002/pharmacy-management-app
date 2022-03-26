package com.example.giuakyqlnt.NhaThuoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.giuakyqlnt.R;

public class EditNhaThuocActivity extends AppCompatActivity {
    TextView tvMaNT;
    EditText txtTenNT, txtDiaChi;
    Button btnAdd, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_nha_thuoc);
        mapping();
        getData();
    }

    public void getData(){
        NhaThuoc nhaThuoc = (NhaThuoc) getIntent().getSerializableExtra("item");
        tvMaNT.setText(nhaThuoc.getMaNT());
        //txtBookID.setEnabled(false);
        txtTenNT.setText(nhaThuoc.getTenNT());
        txtDiaChi.setText(nhaThuoc.getDiaChi());
    }

    public void save(View view){
        //Book book = (Book) getIntent().getSerializableExtra("item");
        //String id = Integer.toString(book.getId());
        String maNT = tvMaNT.getText().toString();
        String tenNT = txtTenNT.getText().toString();
        String diaChi = txtDiaChi.getText().toString();
        //Update dữ liệu
        ContentValues values = new ContentValues();
        //values.put(QLSach2Activity.BookID_FIELD, bookID);
        values.put(ActivityNhaThuoc.TenNT_FIELD, tenNT);
        values.put(ActivityNhaThuoc.DiaChi_FIELD, diaChi);
        String whereClause = ""+ActivityNhaThuoc.MaNT_FIELD+" = ?";
        String[] whereArgs = {maNT};
        ActivityNhaThuoc.myDatabase.update(ActivityNhaThuoc.TABLE_NAME, values, whereClause, whereArgs);
        startActivity(new Intent(EditNhaThuocActivity.this, ActivityNhaThuoc.class));
    }
    public void cancle(View view){
        startActivity(new Intent(EditNhaThuocActivity.this, ActivityNhaThuoc.class));
    }

    private void mapping(){
        tvMaNT = findViewById(R.id.tvMaNT);
        txtTenNT = findViewById(R.id.txtTenNT);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
    }
}