package com.ninegroup.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.ninegroup.weather.api.Datapoint;
import com.ninegroup.weather.api.client.DatapointClient;
import com.ninegroup.weather.databinding.FragmentMonitoringBinding;

import java.util.ArrayList;
import java.util.List;

public class MonitoringFragment extends Fragment {
    private FragmentMonitoringBinding binding;
    //private List<Datapoint> datapointList;

//    public void setData(ChartData data) {
//    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentMonitoringBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Datapoint> datapoints = DatapointClient.datapointList;
        List<Entry> entries = new ArrayList<Entry>();
        for (Datapoint data : datapoints) {
            // turn your data into Entry objects
            entries.add(new Entry(data.getX(), data.getY()));
        }
        LineDataSet dataSet = new LineDataSet(entries, "Temperature"); // add entries to dataset
        //dataSet.setColor(...);
        //dataSet.setValueTextColor(...); // styling, ...
        LineData lineData = new LineData(dataSet);
        binding.lineChart.setData(lineData);
        binding.lineChart.invalidate(); // refresh
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
