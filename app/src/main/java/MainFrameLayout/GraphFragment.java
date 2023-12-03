package MainFrameLayout;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.air_quality_monitoring_app.R;
import com.example.air_quality_monitoring_app.databinding.ActivityMainBinding;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.Entry;


import java.util.ArrayList;
import java.util.List;

public class GraphFragment extends Fragment{

    ArrayList<String> arrNoti;

    private LineChart lineChart;
    String[] items = {"Temperature", "Humidity", "CO2", "PM2.5"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    AutoCompleteTextView autoCompleteTxt;

    private Spinner spinnerSelectAttr, spinnerSelectTF;
    private TextView textView;
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgament_graph, container, false);

        spinnerSelectAttr = view.findViewById(R.id.spinnerAttr);
        String[] attrs = {"Temperature", "Humidity", "CO2", "PM2.5"};
        ArrayAdapter adapterAttr = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, attrs);
        adapterAttr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelectAttr.setAdapter(adapterAttr);

        spinnerSelectTF = view.findViewById(R.id.spinnerTF);
        String[] tf = {"Day", "Week", "Month"};
        ArrayAdapter adapterTF = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, tf);
        adapterTF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelectTF.setAdapter(adapterTF);

        button = view.findViewById(R.id.button);
        textView = view.findViewById(R.id.text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDatePicker(); // Open date picker dialog

                openTimePicker(); //Open time picker dialog
            }
        });


        //  ============================================= Graph setup =============================================
        lineChart = view.findViewById(R.id.line_chart);

        // Create a data set
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 10));
        entries.add(new Entry(1, 20));
        entries.add(new Entry(2, 30));
        entries.add(new Entry(3, 10));
        entries.add(new Entry(4, 30));
        entries.add(new Entry(5, 20));
        entries.add(new Entry(6, 25));
        entries.add(new Entry(7, 22));
        entries.add(new Entry(8, 29));

        LineDataSet dataSet = new LineDataSet(entries, "My Line Chart");

        // Set the data set on the line chart
        lineChart.setData(new LineData(dataSet));
//        dataSet.setColor("blue");

        // Draw the line chart
        lineChart.invalidate();
        return view;
    }

    private void openDatePicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), R.style.DialogTheme , new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                //Showing the picked value in the textView
                textView.setText(String.valueOf(year)+ "."+String.valueOf(month)+ "."+String.valueOf(day));

            }
        }, 2023, 01, 20);

        datePickerDialog.show();
    }


    private void openTimePicker(){

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                //Showing the picked value in the textView
                textView.setText(String.valueOf(hour)+ ":"+String.valueOf(minute));
            }
        }, 15, 30, false);

        timePickerDialog.show();
    }
}