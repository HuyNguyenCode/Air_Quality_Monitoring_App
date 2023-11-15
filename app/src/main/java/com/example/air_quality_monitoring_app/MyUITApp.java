package com.example.air_quality_monitoring_app;

import android.app.Application;
import android.content.Context;

public class MyUITApp extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
}
