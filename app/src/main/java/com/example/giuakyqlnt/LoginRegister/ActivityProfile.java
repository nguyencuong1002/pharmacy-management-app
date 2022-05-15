//package com.example.giuakyqlnt.LoginRegister;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.giuakyqlnt.R;
//
//public class ActivityProfile extends AppCompatActivity {
//    TextView tvMaNT, tvTenNT, tvPassword, tvDiaChi;
//    Button btnLogOut;
//    SharedPreferences sharedPreferences;
//
//    private static final String SHARED_PREF_NAME = "mypref";
//    private static final String KEY_MA_NT = "MaNT";
//    private static final String KEY_TEN_NT = "TenNT";
//    private static final String KEY_PASSWORD = "TenNT";
//    private static final String KEY_DIACHI = "DiaChi";
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_profile);
//        mapping();
//
//        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
//
//        String maNhaThuoc = sharedPreferences.getString(KEY_MA_NT, null);
//        String tenNhaThuoc = sharedPreferences.getString(KEY_TEN_NT, null);
//        String password = sharedPreferences.getString(KEY_PASSWORD, null);
//        String diaChi = sharedPreferences.getString(KEY_DIACHI, null);
//
//        if(maNhaThuoc != null || tenNhaThuoc != null ||password != null ||diaChi != null){
//            tvMaNT.setText(maNhaThuoc);
//            tvTenNT.setText(tenNhaThuoc);
//            tvPassword.setText(password);
//            tvDiaChi.setText(diaChi);
//        }
//
//        btnLogOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.clear();
//                editor.commit();
//
//                Toast.makeText(ActivityProfile.this, "Log Out Sucessfully!!", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(ActivityProfile.this, ActivityLogin.class));
//                finish();
//            }
//        });
//    }
//
//
//
//    private void mapping(){
//        tvMaNT = findViewById(R.id.tvMaNT);
//        tvTenNT = findViewById(R.id.tvTenNT);
//        tvPassword = findViewById(R.id.tvPassword);
//        tvDiaChi = findViewById(R.id.tvDiaChi);
//        btnLogOut = findViewById(R.id.btnSignUp);
//    }
//}
