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

import com.example.giuakyqlnt.MainActivity;
import com.example.giuakyqlnt.R;
import com.example.giuakyqlnt.SendOTP.ActivitySendOTP;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class ActivityLogin extends AppCompatActivity {

    EditText txtMaNT, txtPassword;
    Button btnLogin, btnsignup_screen;
    TextView tvSignUp, tvOTP;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mapping();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String maNT, Password;
                maNT = String.valueOf(txtMaNT.getText());
                Password = String.valueOf(txtPassword.getText());

                if(!maNT.equals("") && !Password.equals("")){
                    //Start Processcing First
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
//                            Start Write and read url
//                            Create array for parameter
                            String[] field = new String[2];
                            field[0] = "MaNT";
                            field[1] = "Password";
//                            Create array for data
                            String[] data = new String[2];
                            data[0] = maNT;
                            data[1] = Password;

                            PutData putData = new PutData(Constaint.URL_LOGIN, "POST", field, data);
                            if(putData.startPut()){
                                if(putData.onComplete()){
                                    String result = putData.getResult();
                                    if(result.equals("Login Success")){
                                        Toast.makeText(ActivityLogin.this, result, Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(ActivityLogin.this, MainActivity.class));
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(ActivityLogin.this, result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }else{
                    Toast.makeText(ActivityLogin.this, "All fields required ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnsignup_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ActivitySignUp.class));
                finish();
            }
        });

        tvOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ActivitySendOTP.class));
                finish();
            }
        });

    }

    private void mapping(){
        txtMaNT = findViewById(R.id.txtMaNT);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.Login_btn);
        btnsignup_screen = findViewById(R.id.signup_screen);
        tvOTP = findViewById(R.id.tvOTP);
    }
}
