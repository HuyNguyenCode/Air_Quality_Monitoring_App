package com.ninegroup.weather;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ninegroup.weather.api.client.AssetClient;
import com.ninegroup.weather.api.client.TokenClient;
import com.ninegroup.weather.databinding.ActivityMainBinding;

// Implement OnMapReadyCallback.
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout file as the content view.
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        if (TokenClient.accessToken == null) {
            startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
        }
        else {
            AssetClient assetClient = new AssetClient();
            assetClient.getAsset();

            NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
            NavController navController = navHostFragment.getNavController();
            AppBarConfiguration appBarConfiguration =
                    new AppBarConfiguration.Builder(R.id.homeFragment, R.id.monitoringFragment, R.id.mapFragment, R.id.settingsFragment).build();
            BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
            NavigationUI.setupWithNavController(bottomNav, navController);
        }
    }
}
