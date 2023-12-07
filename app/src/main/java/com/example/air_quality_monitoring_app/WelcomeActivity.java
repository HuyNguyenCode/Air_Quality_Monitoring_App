package com.example.air_quality_monitoring_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.Locale;

import Authentication.LoginActivity;
import Authentication.SignupActivity;

public class WelcomeActivity extends AppCompatActivity {
    String currentLanguage = Locale.getDefault().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);



        TextView langEnBtn = (TextView) findViewById(R.id.switchLangEnBtn);
        TextView langViBtn = (TextView) findViewById(R.id.switchLangViBtn);

        if(currentLanguage.equals("vi")) {
            langViBtn.setTextColor(getResources().getColor(R.color.purple_700));
            langEnBtn.setTextColor(getResources().getColor(R.color.ashGrey));
        }
        else {
            langEnBtn.setTextColor(getResources().getColor(R.color.purple_700));
            langViBtn.setTextColor(getResources().getColor(R.color.ashGrey));
        }

        langEnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Changed app language to English",
                        Toast.LENGTH_LONG).show();
                updateViews("en");
                if(currentLanguage.equals("en")) {
                    langEnBtn.setTextColor(getResources().getColor(R.color.purple_700));
                    langViBtn.setTextColor(getResources().getColor(R.color.ashGrey));
                }
                recreate();
            }
        });

        langViBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Đã thay đổi ngôn ngữ ứng dụng thành Tiếng Việt",
                        Toast.LENGTH_LONG).show();
                updateViews("vi");
                if(currentLanguage.equals("vi")) {
                    langViBtn.setTextColor(getResources().getColor(R.color.purple_700));
                    langEnBtn.setTextColor(getResources().getColor(R.color.ashGrey));
                }
                recreate();
            }
        });

        MaterialButton btnSignup = findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this,
                        SignupActivity.class);
                startActivity(intent);
            }
        });

        MaterialButton btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this,
                        LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    private void updateViews(String languageCode) {
        Context context = LocaleHelper.setLocale(this, languageCode);
        Resources resources = context.getResources();

        TextView welcomeTitle = (TextView) findViewById(R.id.welcomeTitle);
        TextView greetingText = (TextView) findViewById(R.id.greetingText);
        TextView orText = (TextView) findViewById(R.id.or);
        MaterialButton signupBtn = (MaterialButton) findViewById(R.id.btnSignup);
        MaterialButton loginBtn = (MaterialButton) findViewById(R.id.btnLogin);
        MaterialButton loginGoogleBtn = (MaterialButton) findViewById(R.id.btnLoginGoogle);

        welcomeTitle.setText(resources.getString(R.string.welcome));
        greetingText.setText(resources.getString(R.string.app_introduction));
        orText.setText(resources.getString(R.string.or));
        signupBtn.setText(resources.getString(R.string.create_account));
        loginBtn.setText(resources.getString(R.string.login_btn));
        loginGoogleBtn.setText(resources.getString(R.string.login_google_btn));
    }
}