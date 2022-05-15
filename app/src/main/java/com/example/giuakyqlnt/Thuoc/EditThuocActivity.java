package com.example.giuakyqlnt.Thuoc;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.giuakyqlnt.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class EditThuocActivity extends AppCompatActivity {
    TextView tvMATHUOC;
    EditText txtTENTHUOC, txtDVT, txtDONGIA;
    Button btnAdd, btnCancel;
    ImageView ivBack, imgTHUOC;
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_thuoc);
        mapping();
        getData();
        setEvent();
    }

    public void getData(){
        Thuoc Thuoc = (Thuoc) getIntent().getSerializableExtra("item");
        tvMATHUOC.setText(Thuoc.getMATHUOC());
        txtTENTHUOC.setText(Thuoc.getTENTHUOC());
        txtDVT.setText(Thuoc.getDVT());
        txtDONGIA.setText(String.valueOf(Thuoc.getDONGIA()));
        Bitmap bitmap = BitmapFactory.decodeByteArray(Thuoc.getIMGTHUOC() , 0, Thuoc.getIMGTHUOC().length);
        imgTHUOC.setImageBitmap(bitmap);
    }

    public void upImg(View view){
        ActivityCompat.requestPermissions(
                EditThuocActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                888
        );
    }

    public void save(View view){
        String MATHUOC = tvMATHUOC.getText().toString();
        String TENTHUOC = txtTENTHUOC.getText().toString();
        String DVT = txtDVT.getText().toString();
        String DONGIA = txtDONGIA.getText().toString();
        byte[] IMGTHUOC = imageViewToByte(imgTHUOC);
        //Check data
        isAllFieldsChecked = CheckAllFields();
        if (isAllFieldsChecked) {
            //Update dữ liệu
            ContentValues values = new ContentValues();
            values.put(ActivityThuoc.TENTHUOC_FIELD, TENTHUOC);
            values.put(ActivityThuoc.DVT_FIELD, DVT);
            values.put(ActivityThuoc.DONGIA_FIELD, DONGIA);
            values.put(ActivityThuoc.IMGTHUOC_FIELD, IMGTHUOC);
            String whereClause = ""+ActivityThuoc.MATHUOC_FIELD+" = ?";
            String[] whereArgs = {MATHUOC};
            ActivityThuoc.myDatabase.update(ActivityThuoc.TABLE_NAME, values, whereClause, whereArgs);
            startActivity(new Intent(EditThuocActivity.this, ActivityThuoc.class));
        }
    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    private boolean CheckAllFields() {
        if (txtTENTHUOC.length() == 0) {
            txtTENTHUOC.setError("Vui lòng không để trống!");
            return false;
        }
        if (txtDVT.length() == 0) {
            txtDVT.setError("Vui lòng không để trống!");
            return false;
        }
        if (txtDONGIA.length() == 0) {
            txtDONGIA.setError("Vui lòng không để trống!");
            return false;
        }
        return true;
    }

    public void cancle(View view){
        startActivity(new Intent(EditThuocActivity.this, ActivityThuoc.class));
    }

    public void setEvent(){
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditThuocActivity.this, ActivityThuoc.class));
            }
        });
    }

    private void mapping(){
        tvMATHUOC = findViewById(R.id.tvMATHUOC);
        txtTENTHUOC = findViewById(R.id.txtTENTHUOC);
        txtDVT = findViewById(R.id.txtDVT);
        txtDONGIA = findViewById(R.id.txtDONGIA);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        imgTHUOC = findViewById(R.id.imgTHUOC);
        ivBack = findViewById(R.id.ivBack);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {

        if(requestCode == 888){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 888);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 888 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgTHUOC.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
