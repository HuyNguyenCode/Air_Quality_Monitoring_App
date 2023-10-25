package com.example.air_quality_monitoring_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private VpAdapter vpAdapter;

    ListView lvNotificaiton;

    RelativeLayout relativeLayout;

    Dialog alertDialog;
    String alertTitle[] = {"High temperature alert", "High humidity alert","High PM2.5 alert", "High CO2 alert" };
    String alertTime[] = {"19 sec ago ", "20 sec ago","22 sec ago", "50 sec ago" };

    ArrayList<AlertItem> listNoti;
    CustomeArrayAdapter myArrayAdapter;
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

        lvNotificaiton = findViewById(R.id.list_alert);
        listNoti = new ArrayList<>();
        for (int i = 0; i < alertTitle.length; i++) {
            listNoti.add(new AlertItem(alertTitle[i], alertTime[i]));
        }

        myArrayAdapter = new CustomeArrayAdapter(
                this,
                R.layout.list_alert_item,
                listNoti
        );
        lvNotificaiton.setAdapter(myArrayAdapter);

        //Add an onItemClick() listener to the ListView

        lvNotificaiton.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long
                    id) {
                Intent myintent = new Intent(HomeActivity.this,GraphActivity.class);
                myintent.putExtra("name",alertTitle[position]);
                startActivity(myintent);
            }
        });

    }
//    @Override
    public void showPopup(View view) {
        alertDialog = new Dialog(this);
        alertDialog.setContentView(R.layout.popup_alert_modal);

        int actionImg[] = {R.drawable.turnonafan, R.drawable.openawindow, R.drawable.turndownthethermostat};
        String actionTitle[] = {"Turn on a fan", "Open a window", "Turn off the AC" };
        String actionDes[] = {"The fan on to decrease the room temp", "Open windows to let air circulate","The AC off to increase the room temp"};

        ArrayList<ActionItem> listAct;
        CustomeActionArrayAdapter myActionArrayAdapter;
        ListView lvAction;

        lvAction = (ListView) alertDialog.findViewById(R.id.list_recommended_action);

        TextView alertPopupTitle= alertDialog.findViewById(R.id.alert_popup_title);
//        alertPopupTitle.setText(view.getId(alert_title));

        listAct = new ArrayList<>();
        for (int i = 0; i < actionTitle.length; i++) {
            listAct.add(new ActionItem(actionTitle[i], actionDes[i], actionImg[i]));
        }

        myActionArrayAdapter = new CustomeActionArrayAdapter (
                HomeActivity.this,
                R.layout.list_alert_details,
                listAct
        );
        lvAction.setAdapter(myActionArrayAdapter);


        TextView Skip ,Gotit;
        Skip = alertDialog.findViewById(R.id.Skip);
        Gotit = alertDialog.findViewById(R.id.Gotit);
        Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // write code anything you want
            }
        });

        alertDialog.show();
    }
}