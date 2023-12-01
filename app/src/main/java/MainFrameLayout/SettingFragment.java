package MainFrameLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.air_quality_monitoring_app.R;

import java.util.ArrayList;

public class SettingFragment extends Fragment{


    ArrayList<String> arrNoti;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setiing, container, false);
    }
}