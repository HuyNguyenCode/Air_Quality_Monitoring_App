package MainFrameLayout;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.air_quality_monitoring_app.R;

import java.util.ArrayList;

public class GraphFragment extends Fragment{


    ArrayList<String> arrNoti;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frgament_graph, container, false);
    }
}