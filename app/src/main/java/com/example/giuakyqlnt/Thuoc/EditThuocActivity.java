package com.example.giuakyqlnt.Thuoc;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.giuakyqlnt.R;

public class EditThuocActivity extends AppCompatActivity {
    TextView tvMATHUOC;
    EditText txtTENTHUOC, txtDVT, txtDONGIA;
    Button btnAdd, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_thuoc);
        mapping();
        getData();
    }

    public void getData(){
        Thuoc Thuoc = (Thuoc) getIntent().getSerializableExtra("item");
        tvMATHUOC.setText(Thuoc.getMATHUOC());
        //txtBookID.setEnabled(false);
        txtTENTHUOC.setText(Thuoc.getTENTHUOC());
        txtDVT.setText(Thuoc.getDVT());
        txtDONGIA.setText(Thuoc.getDONGIA());
    }

    public void save(View view){
        //Book book = (Book) getIntent().getSerializableExtra("item");
        //String id = Integer.toString(book.getId());
        String MATHUOC = tvMATHUOC.getText().toString();
        String TENTHUOC = txtTENTHUOC.getText().toString();
        String DVT = txtDVT.getText().toString();
        String DONGIA = txtDONGIA.getText().toString();
        //Update dữ liệu
        ContentValues values = new ContentValues();
        //values.put(QLSach2Activity.BookID_FIELD, bookID);
        values.put(ActivityThuoc.TENTHUOC_FIELD, TENTHUOC);
        values.put(ActivityThuoc.DVT_FIELD, DVT);
        values.put(ActivityThuoc.DONGIA_FIELD, DONGIA);
        String whereClause = ""+ActivityThuoc.MATHUOC_FIELD+" = ?";
        String[] whereArgs = {MATHUOC};
        ActivityThuoc.myDatabase.update(ActivityThuoc.TABLE_NAME, values, whereClause, whereArgs);
        startActivity(new Intent(EditThuocActivity.this, ActivityThuoc.class));
    }
    public void cancle(View view){
        startActivity(new Intent(EditThuocActivity.this, ActivityThuoc.class));
    }

    private void mapping(){
        tvMATHUOC = findViewById(R.id.tvMATHUOC);
        txtTENTHUOC = findViewById(R.id.txtTENTHUOC);
        txtDVT = findViewById(R.id.txtDVT);
        txtDONGIA = findViewById(R.id.txtDONGIA);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
    }
}
