package com.example.air_quality_monitoring_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



import java.util.Locale;


public class LanguageActivity extends AppCompatActivity {

    TextView langEnBtn;
    TextView langViBtn;
    TextView langCHBtn;
    String currentLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_language);

        langEnBtn = findViewById(R.id.switchLangEnBttn);
        langViBtn = findViewById(R.id.switchLangViBttn);
        langCHBtn = findViewById(R.id.switchChinaBttn);

        currentLanguage = Locale.getDefault().toString();
        updateButtonColors();

        langEnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Changed app language to English",
                        Toast.LENGTH_LONG).show();
                updateViews("en");
                currentLanguage = "en";
                updateButtonColors();
                recreate();
            }
        });

        langViBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Đã thay đổi ngôn ngữ ứng dụng thành Tiếng Việt",
                        Toast.LENGTH_LONG).show();
                updateViews("vi");
                currentLanguage = "vi";
                updateButtonColors();
                recreate();
            }
        });

        langCHBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Đã thay đổi ngôn ngữ ứng dụng thành Trung Quốc",
                        Toast.LENGTH_LONG).show();
                updateViews("zh");
                currentLanguage = "zh";
                updateButtonColors();
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
    }

    private void updateButtonColors() {
        if (currentLanguage.equals("en")) {
            langEnBtn.setTextColor(getResources().getColor(R.color.purple_700));
            langViBtn.setTextColor(getResources().getColor(android.R.color.black));
            langCHBtn.setTextColor(getResources().getColor(android.R.color.black));
        }
        else if (currentLanguage.equals("vi")) {
            langViBtn.setTextColor(getResources().getColor(R.color.purple_700));
            langEnBtn.setTextColor(getResources().getColor(android.R.color.black));
            langCHBtn.setTextColor(getResources().getColor(android.R.color.black));
        }
        else if (currentLanguage.equals("zh")) {
            langCHBtn.setTextColor(getResources().getColor(R.color.purple_700));
            langEnBtn.setTextColor(getResources().getColor(android.R.color.black));
            langViBtn.setTextColor(getResources().getColor(android.R.color.black));
        }
    }
}

