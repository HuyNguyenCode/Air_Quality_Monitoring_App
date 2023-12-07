package com.example.air_quality_monitoring_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.Locale;


public class LanguageActivity extends AppCompatActivity {

    TextView langEnBtn;
    TextView langViBtn;
    String currentLanguage = Locale.getDefault().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_language);

        langEnBtn = findViewById(R.id.switchLangEnBttn);
        langViBtn = findViewById(R.id.switchLangViBttn);


        langEnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Changed app language to English",
                        Toast.LENGTH_LONG).show();
                updateViews("en");
                if(currentLanguage.equals("en")) {
                    langViBtn.setTextColor(getResources().getColor(R.color.black));
                    langEnBtn.setTextColor(getResources().getColor(R.color.black));
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
                    langViBtn.setTextColor(getResources().getColor(R.color.black));
                    langEnBtn.setTextColor(getResources().getColor(R.color.black));
                }
                recreate();
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

        TextView welcomeTitle = findViewById(R.id.welcomeTitle);
        TextView greetingText = findViewById(R.id.greetingText);
        TextView orText = findViewById(R.id.or);
        MaterialButton signupBtn = findViewById(R.id.btnSignup);
        MaterialButton loginBtn = findViewById(R.id.btnLogin);
        MaterialButton loginGoogleBtn = findViewById(R.id.btnLoginGoogle);

        welcomeTitle.setText(resources.getString(R.string.welcome));
        greetingText.setText(resources.getString(R.string.app_introduction));
        orText.setText(resources.getString(R.string.or));
        signupBtn.setText(resources.getString(R.string.create_account));
        loginBtn.setText(resources.getString(R.string.login_btn));
        loginGoogleBtn.setText(resources.getString(R.string.login_google_btn));
    }
}
