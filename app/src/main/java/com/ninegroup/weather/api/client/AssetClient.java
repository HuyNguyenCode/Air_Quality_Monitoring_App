package com.ninegroup.weather.api.client;

import android.util.Log;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import com.ninegroup.weather.api.ApiService;
import com.ninegroup.weather.api.Asset;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssetClient {
    private final String accessToken = TokenClient.accessToken;
    private final ApiService apiService = ApiClient.getClient(accessToken).create(ApiService.class);
    private Asset asset;
    public static String rainfall;
    public static String manufacturer;
    public static String uvIndex;
    public static String temperature;
    public static String humidity;
    public static String location;
    public static String place;
    public static String windDirection;
    public static String windSpeed;
    public static Boolean isAssetRunning = true;
    public static Boolean isSuccess = false;

    public void getAsset() {
        apiService.getAsset("5zI6XqkQVSfdgOrZ1MyWEf").enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {
                if (response.isSuccessful()) {
                    asset = response.body();
                    if (asset != null) {
                        Log.i("ASSET API CALL", asset.type);
                        Log.i("ASSET API CALL", asset.attributes.toString());
                        Gson gson = new Gson();
                        String json = gson.toJson(asset.attributes);
                        handleAssetResponse(json);
                        isSuccess = true;
                        isAssetRunning = false;
                    }
                } else {
                    Log.e("ASSET API CALL", "API call unsuccessful! Your access token maybe expired or you don't have enough permissions.");
                    isSuccess = false;
                    isAssetRunning = false;
                }
            }

            @Override
            public void onFailure(Call<Asset> call, Throwable t) {
                Log.e("ASSET API CALL", t.getMessage().toString());
                isSuccess = false;
                isAssetRunning = false;
            }
        });
    }

    public void handleAssetResponse(String data) {
        rainfall = JsonPath.read(data, "$.rainfall.value").toString();
        //uvIndex = JsonPath.read(data, "$.uVIndex.value").toString();
        manufacturer = JsonPath.read(data, "$.manufacturer.value").toString();
        temperature = JsonPath.read(data, "$.temperature.value").toString();
        humidity = JsonPath.read(data, "$.humidity.value").toString();
        location = JsonPath.read(data, "$.location.value").toString();
        place = JsonPath.read(data, "$.place.value").toString();
        windDirection = JsonPath.read(data, "$.windDirection.value").toString();
        windSpeed = JsonPath.read(data, "$.windSpeed.value").toString();

        Log.i("ASSET API CALL", "rainfall: " + rainfall);
        //Log.i("ASSET API CALL", "uVIndex: " + uvIndex);
        Log.i("ASSET API CALL", "manufacturer: " + manufacturer);
        Log.i("ASSET API CALL", "temperature: " + temperature);
        Log.i("ASSET API CALL", "humidity: " + humidity);
        Log.i("ASSET API CALL", "location: " + location);
        Log.i("ASSET API CALL", "place: " + place);
        Log.i("ASSET API CALL", "windDirection: " + windDirection);
        Log.i("ASSET API CALL", "windSpeed: " + windSpeed);
    }
}
