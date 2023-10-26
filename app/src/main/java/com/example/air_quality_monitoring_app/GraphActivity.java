package com.example.air_quality_monitoring_app;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.air_quality_monitoring_app.databinding.ActivityGraphBinding;

import org.w3c.dom.Text;

public class GraphActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityGraphBinding binding;
    private TextView myText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
    }
}