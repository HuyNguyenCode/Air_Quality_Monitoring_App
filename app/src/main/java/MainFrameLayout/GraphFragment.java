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
import android.widget.LinearLayout;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GraphFragment extends Fragment {

    ArrayList<String> arrNoti;

    private LineChart lineChart;
    String[] items = {"Temperature", "Humidity", "CO2", "PM2.5"};
    ArrayAdapter<String> adapterAttrItems;
    ArrayAdapter<String> adapterTfItems;

    ArrayAdapter<String> adapterDateItems;
    AutoCompleteTextView autoAttrCompleteTxt, autoTfCompleteTxt, autoDateCompleteTxt;
//    TextInputLayout inputDateTxt;
    TextView textViewDate;

    String[] atrrItems = {"Temperature", "Humidity", "CO2", "PM2.5"};
    String[] tfItems = {"Day", "Week", "Month"};
//    String[] dateItem = {};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgament_graph, container, false);
        autoDateCompleteTxt = view.findViewById(R.id.auto_complete_date);
        autoDateCompleteTxt.setOnClickListener(new View.OnClickListener() {
            String datePicked = "";
            String timePicked = "";

            @Override
            public void onClick(View view) {

                final Calendar calendar = Calendar.getInstance();

                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        // Save the picked time
                        timePicked = String.format("%02d:%02d", hour, minute);

                        // Show the date picker now
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                String chosenDate = String.format("%02d/%02d/%d", day, month + 1, year);
                                datePicked = chosenDate;
                                // Set the combined date and time
                                autoDateCompleteTxt.setText(datePicked + " " + timePicked);
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                        datePickerDialog.show();
                    }
                }, 15, 30, false);
                timePickerDialog.show();
            }
        });

        autoAttrCompleteTxt = view.findViewById(R.id.auto_complete_attr);
        adapterAttrItems = new ArrayAdapter<String>(getContext(),R.layout.list_item,atrrItems);
        autoAttrCompleteTxt.setAdapter(adapterAttrItems);

        autoTfCompleteTxt = view.findViewById(R.id.auto_complete_tf);
        adapterTfItems = new ArrayAdapter<String>(getContext(),R.layout.list_item,tfItems);
        autoTfCompleteTxt.setAdapter(adapterTfItems);

        autoAttrCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getActivity().getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });

        autoTfCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getActivity().getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
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
}

