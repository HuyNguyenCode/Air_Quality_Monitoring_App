package MainFrameLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.air_quality_monitoring_app.LanguageActivity;
import com.example.air_quality_monitoring_app.LocaleHelper;
import com.example.air_quality_monitoring_app.LogoutActivity;
import com.example.air_quality_monitoring_app.R;
import com.example.air_quality_monitoring_app.UserActivity;
import com.example.air_quality_monitoring_app.WelcomeActivity;
import com.example.air_quality_monitoring_app.ui.login.LoginActivity;

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
    private void updateLanguage(String languageCode) {
        LocaleHelper.setLocale(getActivity(), languageCode);
        getActivity().recreate();
    }
    private void openLogoutActivity() {
        // Tạo Intent để quay lại màn hình đăng nhập (LoginActivity)
        Intent intent = new Intent(getActivity(), WelcomeActivity.class);
        // Đặt cờ để xóa ngăn xếp của Activity và không cho phép người dùng quay lại màn hình trước đó
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
