package com.example.giuakyqlnt.LoginRegister;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.giuakyqlnt.MainActivity;
import com.example.giuakyqlnt.R;
import com.example.giuakyqlnt.SendOTP.ActivitySendOTP;
import com.google.android.material.textfield.TextInputLayout;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class ActivityLogin extends AppCompatActivity {

    EditText txtMaNT, txtPassword;
    ImageView image;
    Button callSignUp, login_btn;
    TextView tvOTP, logoText, sloganText;
    TextInputLayout username, password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        mapping();
        Effects();
//        if(SharedPrefManager.getInstance(this).isLoggedIn()){
//            finish();
//            startActivity(new Intent(this, ActivityProfile.class));
//            return;
//        }

        login_btn.setOnClickListener(new View.OnClickListener() {
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


        callSignUp.setOnClickListener(new View.OnClickListener() {
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

    public void Effects(){
        //Hocks
        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityLogin.this, ActivitySignUp.class);
                Pair[] pairs = new Pair[7];

                pairs[0] = new Pair<View, String>(image, "logo_image");
                pairs[1] = new Pair<View, String>(logoText, "logo_text");
                pairs[2] = new Pair<View, String>(sloganText, "logo_desc");
                pairs[3] = new Pair<View, String>(txtMaNT, "user_tran");
                pairs[4] = new Pair<View, String>(txtPassword, "password_tran");
                pairs[5] = new Pair<View, String>(login_btn, "button_tran");
                pairs[6] = new Pair<View, String>(callSignUp, "login_tran");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ActivityLogin.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });
    }

    private void mapping(){
        txtMaNT = findViewById(R.id.txtMaNT);
        txtPassword = findViewById(R.id.txtPassword);
        login_btn = findViewById(R.id.Login_btn);
        callSignUp = findViewById(R.id.signup_screen);
        tvOTP = findViewById(R.id.tvOTP);
        image = findViewById(R.id.logoImage);
        logoText = findViewById(R.id.logo_name);
        sloganText = findViewById(R.id.slogan_name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
    }
}
