package com.example.air_quality_monitoring_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.air_quality_monitoring_app.controller.UserController;
import com.example.air_quality_monitoring_app.model.User;

public class APIScreen extends AppCompatActivity {

    UserController userController = UserController.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_api);
        TextView userTV = findViewById(R.id.textView2);
        userTV.setText("Hello " + userController.user.getUserEmail());
    }
}