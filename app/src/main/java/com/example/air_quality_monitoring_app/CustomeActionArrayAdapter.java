package com.example.air_quality_monitoring_app;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomeActionArrayAdapter extends ArrayAdapter<ActionItem> {
    Activity context;
    int idLayout;
    ArrayList<ActionItem> arrAction;

    public CustomeActionArrayAdapter(Activity context, int idLayout, ArrayList<ActionItem> arrAction) {
        super(context, idLayout, arrAction);
        this.context = context;
        this.idLayout = idLayout;
        this.arrAction = arrAction;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myFlactor = context.getLayoutInflater();
        convertView = myFlactor.inflate(idLayout, null);
        ActionItem actionItem = arrAction.get(position);

        TextView actionTitle = convertView.findViewById(R.id.action_title);
        actionTitle.setText(actionItem.getActionTitle());

        TextView actionDes = convertView.findViewById(R.id.action_des);
        actionDes.setText(actionItem.getActionDes());

        ImageView actionImg = convertView.findViewById(R.id.action_img);
        actionImg.setImageResource(actionItem.getActionImg());
        return convertView;
    }
}