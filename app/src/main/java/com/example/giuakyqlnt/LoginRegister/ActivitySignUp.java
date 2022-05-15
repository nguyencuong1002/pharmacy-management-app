package com.example.giuakyqlnt.LoginRegister;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.giuakyqlnt.R;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class ActivitySignUp extends AppCompatActivity {

    EditText txtMaNT, txtTenNT, txtPassword, txtDiaChi;
    Button btnSignUp;
    TextView tvLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mapping();

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ActivityLogin.class));
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String maNT, tenNT, Password, diaChi;
                maNT = String.valueOf(txtMaNT.getText());
                tenNT = String.valueOf(txtTenNT.getText());
                Password = String.valueOf(txtPassword.getText());
                diaChi = String.valueOf(txtDiaChi.getText());

                if(!maNT.equals("") && !tenNT.equals("") && !Password.equals("") && !diaChi.equals("")){
                    //Start Processcing First
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
//                            Start Write and read url
//                            Create array for parameter
                            String[] field = new String[4];
                            field[0] = "MaNT";
                            field[1] = "TenNT";
                            field[2] = "Password";
                            field[3] = "DiaChi";
//                            Create array for data
                            String[] data = new String[4];
                            data[0] = maNT;
                            data[1] = tenNT;
                            data[2] = Password;
                            data[3] = diaChi;

                            PutData putData = new PutData("http://192.168.1.9/LoginRegister/signup.php", "POST", field, data);
                            if(putData.startPut()){
                                if(putData.onComplete()){
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success")){
                                        Toast.makeText(ActivitySignUp.this, result, Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(ActivitySignUp.this, ActivityLogin.class));
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(ActivitySignUp.this, result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }else{
                    Toast.makeText(ActivitySignUp.this, "All fields required ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void mapping(){
        txtMaNT = findViewById(R.id.txtMaNT);
        txtTenNT = findViewById(R.id.txtTenNT);
        txtPassword = findViewById(R.id.txtPassword);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvLogin = findViewById(R.id.tvLogin);
    }
}
