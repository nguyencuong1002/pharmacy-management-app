package com.example.giuakyqlnt.Thuoc;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giuakyqlnt.R;

public class AddThuocActivity extends AppCompatActivity {
    EditText txtMATHUOC, txtTENTHUOC, txtDVT, txtDONGIA;
    Button btnAdd, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_thuoc);
        mapping();
    }

    public void add(View view){
        String MATHUOC = txtMATHUOC.getText().toString();
        String TENTHUOC = txtTENTHUOC.getText().toString();
        String DVT = txtDVT.getText().toString();
        Float DONGIA = Float.parseFloat(txtDONGIA.getText().toString());
        //Add dữ liệu
        ContentValues values = new ContentValues();
        values.put(ActivityThuoc.MATHUOC_FIELD, MATHUOC);
        values.put(ActivityThuoc.TENTHUOC_FIELD, TENTHUOC);
        values.put(ActivityThuoc.DVT_FIELD, DVT);
        values.put(ActivityThuoc.DONGIA_FIELD, DONGIA);
        ActivityThuoc.myDatabase.insert(ActivityThuoc.TABLE_NAME, null,values);
        startActivity(new Intent(AddThuocActivity.this, ActivityThuoc.class));
    }
    public void cancle(View view){
        startActivity(new Intent(AddThuocActivity.this, ActivityThuoc.class));
    }

    private void mapping(){
        txtMATHUOC = findViewById(R.id.txtMATHUOC);
        txtTENTHUOC = findViewById(R.id.txtTENTHUOC);
        txtDVT = findViewById(R.id.txtDVT);
        txtDONGIA = findViewById(R.id.txtDONGIA);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
    }
}
