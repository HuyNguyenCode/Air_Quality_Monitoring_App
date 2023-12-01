package MainFrameLayout;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.air_quality_monitoring_app.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class IndicatorFragment extends Fragment{

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private VpAdapter vpAdapter;
    ArrayList<String> arrNoti;
    private Dialog alertDialog;

    private ListView lvNotificaiton;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_indicator, container, false);

        tabLayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewpager);

        vpAdapter = new VpAdapter(getActivity());
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

        lvNotificaiton = (ListView) view.findViewById(R.id.list_alert);
        listNoti = new ArrayList<>();
        for (int i = 0; i < alertTitle.length; i++) {
            listNoti.add(new AlertItem(alertTitle[i], alertTime[i]));
        }

        myArrayAdapter = new CustomeArrayAdapter(
                getActivity(),
                R.layout.list_alert_item,
                listNoti
        );
        lvNotificaiton.setAdapter(myArrayAdapter);
        lvNotificaiton.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertItem alertItem = listNoti.get(position);
                Log.d("test","OnClick trigger");
                showPopup(alertItem);
            }
        });
        return view;
    }
    public void showPopup(AlertItem alertItem) {
        alertDialog = new Dialog(getActivity());
        alertDialog.setContentView(R.layout.popup_alert_modal);

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

        }
        else if (alertItem.getAlertTitle().contains("Humidity")) {
            actionImg.add(R.drawable.identifythesourceofthehumidity);
            actionImg.add(R.drawable.preventmoldgrowth);
            actionImg.add(R.drawable.reducethehumidityintheair);

            actionTitle.add("Identify the source of the humidity");
            actionTitle.add("Reduce the humidity in the air");
            actionTitle.add("Prevent mold growth");

            actionDes.add("Once you know the source, you can take steps to address it");
            actionDes.add("using a dehumidifier, running a fan to let in fresh air");
            actionDes.add("Cleaning up spills, leaks and keeping surfaces dry");
        }
        else if (alertItem.getAlertTitle().contains("CO2")) {
            actionImg.add(R.drawable.openawindow);
            actionImg.add(R.drawable.checkgasappliances);

            actionTitle.add("Open windows and doors");
            actionTitle.add("Check gas appliances");

            actionDes.add("Open windows and doors to ventilate the area");
            actionDes.add("Turn off any gas appliances that may be producing CO2");
        }
        else {
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
                getActivity(),
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