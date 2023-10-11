package com.example.air_quality_monitoring_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private VpAdapter vpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);


        vpAdapter = new VpAdapter(this);
        viewPager.setAdapter(vpAdapter);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Humidity");
                    break;
                case 1:
                    tab.setText("PM2.5");
                    break;
                case 2:
                    tab.setText("CO2");
                    break;
                case 3:
                    tab.setText("Temp");
                    break;
            }
        }).attach();
        
        
        //set-up listview
        String alertTitle[] = {"High pollution alert", "High humidity alert","High temperature alert", "High CO2 alert" };
        String alertTime[] = {"19 sec ago ", "20 sec ago","22 sec ago", "50 sec ago" };

        ArrayList<AlertItem> listNoti;
        CustomeArrayAdapter myArrayAdapter;
        ListView lvNotificaiton;

        lvNotificaiton = (ListView) findViewById(R.id.list_co2_alert);
        listNoti = new ArrayList<>();
        for (int i = 0; i < alertTitle.length; i++) {
            listNoti.add(new AlertItem(alertTitle[i], alertTime[i]));
        }

        myArrayAdapter = new CustomeArrayAdapter(
                HomeActivity.this,
                R.layout.list_alert_item,
                listNoti
        );
        lvNotificaiton.setAdapter(myArrayAdapter);
    }
}