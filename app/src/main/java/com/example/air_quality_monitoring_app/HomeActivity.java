package com.example.air_quality_monitoring_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    String alertTitle[] = {"High Temperature alert", "High Humidity alert","High PM2.5 alert", "High CO2 alert" };
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
//                Intent myintent = new Intent(HomeActivity.this,GraphActivity.class);
//                myintent.putExtra("name",alertTitle[position]);
//                startActivity(myintent);
                AlertItem alertItem = listNoti.get(position);
                showPopup(alertItem);
            }
        });

    }
//    @Override
    public void showPopup(AlertItem alertItem) {
        alertDialog = new Dialog(this);
        alertDialog.setContentView(R.layout.popup_alert_modal);
//        int actionImg[];
        ArrayList <Integer> actionImg;
        actionImg = new ArrayList<Integer>();

        ArrayList <String> actionTitle;
        actionTitle = new ArrayList<String>();

        ArrayList <String> actionDes;
        actionDes = new ArrayList<String>();

        if (alertItem.getAlertTitle().contains("Temperature")) {
            actionImg.add(R.drawable.turnonafan);
            actionImg.add(R.drawable.openawindow);
            actionImg.add(R.drawable.turndownthethermostat);

            actionTitle.add("Turn on a fan");
            actionTitle.add("Open a window");
            actionTitle.add("Turn off the AC");

            actionDes.add("The fan on to decrease the room temp");
            actionDes.add("Open windows to let air circulate");
            actionDes.add("The AC off to increase the room temp");

        } else if (alertItem.getAlertTitle().contains("Humidity")) {
            actionImg.add(R.drawable.identifythesourceofthehumidity);
            actionImg.add(R.drawable.preventmoldgrowth);
            actionImg.add(R.drawable.reducethehumidityintheair);

            actionTitle.add("Identify the source of the humidity");
            actionTitle.add("Reduce the humidity in the air");
            actionTitle.add("Prevent mold growth");

            actionDes.add("Once you know the source, you can take steps to address it");
            actionDes.add("using a dehumidifier, running a fan to let in fresh air");
            actionDes.add("Cleaning up spills, leaks and keeping surfaces dry");
        } else if (alertItem.getAlertTitle().contains("CO2")) {
            actionImg.add(R.drawable.openawindow);
            actionImg.add(R.drawable.checkgasappliances);

            actionTitle.add("Open windows and doors");
            actionTitle.add("Check gas appliances");

            actionDes.add("Open windows and doors to ventilate the area");
            actionDes.add("Turn off any gas appliances that may be producing CO2");
        } else {
            actionImg.add(R.drawable.avoidstrenuousactivity);
            actionImg.add(R.drawable.wearamask);
            actionImg.add(R.drawable.useairpurifier);

            actionTitle.add("Avoid strenuous activity");
            actionTitle.add("Wear a mask if you must go outside");
            actionTitle.add("Use an air purifier");

            actionDes.add("Strenuous activity can increase your breathing rate and draw more PM2.5 particles into your lungs");
            actionDes.add("Wear a mask to limit pm2.5 dust from entering your body");
            actionDes.add("Air purifier can help to remove PM2.5 particles");
        }

        ArrayList<ActionItem> listAct;
        CustomeActionArrayAdapter myActionArrayAdapter;
        ListView lvAction;

        lvAction = (ListView) alertDialog.findViewById(R.id.list_recommended_action);

        TextView alertPopupTitle= alertDialog.findViewById(R.id.alert_popup_title);
        alertPopupTitle.setText(alertItem.getAlertTitle());

        listAct = new ArrayList<>();
        for (int i = 0; i < actionTitle.size(); i++) {
            listAct.add(new ActionItem(actionTitle.get(i), actionDes.get(i), actionImg.get(i)));
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