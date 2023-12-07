package MainFrameLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.air_quality_monitoring_app.LanguageActivity;
import com.example.air_quality_monitoring_app.LogoutActivity;
import com.example.air_quality_monitoring_app.R;
import com.example.air_quality_monitoring_app.UserActivity;

import java.util.ArrayList;

public class SettingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);


        Button btnUser = view.findViewById(R.id.user_information);
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserActivity();
            }
        });

        Button btnLanguage = view.findViewById(R.id.change_language);
        btnLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLanguageActivity();
            }
        });

        Button btnLogout = view.findViewById(R.id.Log_out);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogoutActivity();
            }
        });

        return view;
    }

    private void openUserActivity() {
        Intent intent = new Intent(getActivity(), UserActivity.class);
        startActivity(intent);
    }

    private void openLanguageActivity() {
        Intent intent = new Intent(getActivity(), LanguageActivity.class);
        startActivity(intent);
    }
    private void openLogoutActivity() {
        Intent intent = new Intent(getActivity(), LogoutActivity.class);
        startActivity(intent);
    }
}
