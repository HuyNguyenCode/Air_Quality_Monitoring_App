package com.example.air_quality_monitoring_app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomeArrayAdapter extends ArrayAdapter<AlertItem> {
    Activity context;
    int idLayout;
    ArrayList<AlertItem> arrAlert;

    public CustomeArrayAdapter( Activity context, int idLayout, ArrayList<AlertItem> arrAlert) {
        super(context, idLayout, arrAlert);
        this.context = context;
        this.idLayout = idLayout;
        this.arrAlert = arrAlert;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myFlactor = context.getLayoutInflater();
        convertView = myFlactor.inflate(idLayout, null);
        AlertItem alertItem = arrAlert.get(position);

        TextView alertTitle = convertView.findViewById(R.id.alert_title);
        alertTitle.setText(alertItem.getAlertTitle());

        TextView alertTime = convertView.findViewById(R.id.alert_time);
        alertTime.setText(alertItem.getAlertTime());
        return convertView;
    }
}