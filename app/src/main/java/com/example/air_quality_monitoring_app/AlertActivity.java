package com.example.air_quality_monitoring_app;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AlertActivity extends AppCompatActivity {
    Dialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.list_alert_item);
        FloatingActionButton floatingActionButton = findViewById(R.id.detailsBtn);
    }

    public void showPopup(AlertItem alertItem) {
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
        alertPopupTitle.setText(alertItem.getAlertTitle());

        listAct = new ArrayList<>();
        for (int i = 0; i < actionTitle.length; i++) {
            listAct.add(new ActionItem(actionTitle[i], actionDes[i], actionImg[i]));
        }

        myActionArrayAdapter = new CustomeActionArrayAdapter (
                AlertActivity.this,
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