package com.example.air_quality_monitoring_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.app.ActionBar.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;
import android.widget.Button;

import com.example.air_quality_monitoring_app.ui.login.LoginActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

//        ImageSwitcher sw;
//        sw = (ImageSwitcher) findViewById(R.id.imageSwitcher);
//        sw.setFactory(new ViewSwitcher.ViewFactory() {
//            @Override
//            public View makeView() {
//                ImageView myView = new ImageView(getApplicationContext());
//                myView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//                myView.setLayoutParams(new
//                        ImageSwitcher.LayoutParams(LayoutParams.WRAP_CONTENT,
//                        LayoutParams.WRAP_CONTENT));
//                return myView;
//            }
//        });

        Button langEnBtn = (Button) findViewById(R.id.switchLangEnBtn);
        Button langViBtn = (Button) findViewById(R.id.switchLangViBtn);

        langEnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Changed app language to English",
                        Toast.LENGTH_LONG).show();
                updateViews("en");
                recreate();
            }
        });

        langViBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Đã thay đổi ngôn ngữ ứng dụng thành Tiếng Việt",
                        Toast.LENGTH_LONG).show();
                updateViews("vi");
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