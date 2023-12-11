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
    static public String rainfall;
    static public String manufacturer;
    static public String uvIndex;
    static public String temperature;
    static public String humidity;
    static public String location;
    static public String place;
    static public String windDirection;
    static public String windSpeed;

    public void getAsset() {
        apiService.getAsset("5zI6XqkQVSfdgOrZ1MyWEf").enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {
                if (response.isSuccessful()) {
                    asset = response.body();
                    if (asset != null) {
                        Log.d("ASSET API CALL", asset.type);
                        Log.d("ASSET API CALL", asset.attributes.toString());
                        Gson gson = new Gson();
                        String json = gson.toJson(asset.attributes);
                        handleAssetResponse(json);
                    }
                } else {
                    Log.d("ASSET API CALL", "API call unsuccessful! Your access token maybe expired or you don't have enough permissions.");
                }
            }

            @Override
            public void onFailure(Call<Asset> call, Throwable t) {
                Log.d("ASSET API CALL", t.getMessage().toString());
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

        Log.d("ASSET API CALL", "rainfall: " + rainfall);
        //Log.d("ASSET API CALL", "uVIndex: " + uvIndex);
        Log.d("ASSET API CALL", "manufacturer: " + manufacturer);
        Log.d("ASSET API CALL", "temperature: " + temperature);
        Log.d("ASSET API CALL", "humidity: " + humidity);
        Log.d("ASSET API CALL", "location: " + location);
        Log.d("ASSET API CALL", "place: " + place);
        Log.d("ASSET API CALL", "windDirection: " + windDirection);
        Log.d("ASSET API CALL", "windSpeed: " + windSpeed);
    }
}
