package com.ninegroup.weather;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ninegroup.weather.api.client.AssetClient;
import com.ninegroup.weather.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private Handler handler;
    private Runnable updateUI;

    private void initVariables() {
        handler = new Handler();
        updateUI = new Runnable() {
            @Override
            public void run() {
                Log.i("UpdateUI", "UpdateUI process is running");
                //Log.d("WebView","is loading: " + WebViewClient.isRunning);

                if (!AssetClient.isAssetRunning) {
                    binding.homeTopBar.setTitle(AssetClient.place);
                    binding.temperature.setText(AssetClient.temperature + "°");
                    //binding.weatherStatusImageView.setImageDrawable();
                    //binding.uvStatus.setText(assetController.uvModel.getValue());
                    binding.humidityStatus.setText(AssetClient.humidity + "%");
                    binding.rainStatus.setText(AssetClient.rainfall + " mm");
                    binding.windStatus.setText(AssetClient.windSpeed + " km/h");
                    Log.i("UpdateUI", "UpdateUI process is stopped");
                    handler.removeCallbacks(updateUI);
                }
            }
        };
    }

    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initVariables();

        handler.postDelayed(updateUI, 200);
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.homeTopBar.setTitle(AssetClient.place);
        binding.temperature.setText(AssetClient.temperature + "°");
        //binding.weatherStatusImageView.setImageDrawable();
        //binding.uvStatus.setText(assetController.uvModel.getValue());
        binding.humidityStatus.setText(AssetClient.humidity + "%");
        binding.rainStatus.setText(AssetClient.rainfall + " mm");
        binding.windStatus.setText(AssetClient.windSpeed + " km/h");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
