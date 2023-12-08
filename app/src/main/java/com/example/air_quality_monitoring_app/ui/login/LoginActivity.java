package com.example.air_quality_monitoring_app.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.air_quality_monitoring_app.HomeActivity;
import com.example.air_quality_monitoring_app.R;
import Authentication.SignupActivity;

import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail, edtPass;
    Button LoginBtn;
    TextView Login_to_Signup, Forgot_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        init();
        LoginBtn = findViewById(R.id.btnLogin);

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this,
                        HomeActivity.class);
                startActivity(intent);
            }
        });

        Login_to_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        Forgot_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Forgot Password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void init() {
        edtEmail = (EditText) findViewById(R.id.username);
        edtPass = (EditText) findViewById(R.id.password);

        LoginBtn = (MaterialButton) findViewById(R.id.btnSignup);
        Login_to_Signup = (TextView) findViewById(R.id.login_to_signup);
        Forgot_Password = (TextView) findViewById(R.id.forgot_password);

    }
}