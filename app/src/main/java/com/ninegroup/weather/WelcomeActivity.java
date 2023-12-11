package com.ninegroup.weather;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.ninegroup.weather.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends AppCompatActivity {
    private ActivityWelcomeBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout file as the content view.
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_welcome);
        NavController navController = navHostFragment.getNavController();
        //AppBarConfiguration appBarConfiguration =
                //new AppBarConfiguration.Builder(R.id.homeFragment, R.id.monitoringFragment, R.id.mapFragment, R.id.settingsFragment).build();
        //BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        //NavigationUI.setupWithNavController(bottomNav, navController);
    }
}
