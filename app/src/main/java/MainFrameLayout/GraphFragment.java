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

public class GraphFragment extends Fragment{

    ArrayList<String> arrNoti;

    private LineChart lineChart;
    String[] items = {"Temperature", "Humidity", "CO2", "PM2.5"};
    ArrayAdapter<String> adapterAttrItems;
    ArrayAdapter<String> adapterTfItems;
    AutoCompleteTextView autoAttrCompleteTxt, autoTfCompleteTxt;
    TextInputLayout inputDateTxt;
    TextView textViewDate;

    String[] atrrItems = {"Temperature", "Humidity", "CO2", "PM2.5"};
    String[] tfItems = {"Day", "Week", "Month"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgament_graph, container, false);


        inputDateTxt = view.findViewById(R.id.input_date_txt);
        textViewDate = view.findViewById(R.id.text_view_date);
        inputDateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDatePicker(); // Open date picker dialog

//                openTimePicker(); //Open time picker dialog
            }
        });


        autoAttrCompleteTxt = view.findViewById(R.id.auto_complete_attr);
        autoTfCompleteTxt = view.findViewById(R.id.auto_complete_tf);

        adapterAttrItems = new ArrayAdapter<String>(getContext(),R.layout.list_item,atrrItems);
        autoAttrCompleteTxt.setAdapter(adapterAttrItems);
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

    private void openDatePicker(){
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), R.style.DialogTheme , new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                //Showing the picked value in the textView
//                autoCompleteTVDate.setText(String.valueOf(year)+ "."+String.valueOf(month)+ "."+String.valueOf(day));
                textViewDate.setText(String.format("%d/%02d/%02d", year, month + 1, day));
            }
//        }, 2023, 01, 20);
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

    }


    private void openTimePicker(){

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                //Showing the picked value in the textView
                textViewDate.setText(String.valueOf(hour)+ ":"+String.valueOf(minute));
            }
        }, 15, 30, false);

        timePickerDialog.show();
    }
}