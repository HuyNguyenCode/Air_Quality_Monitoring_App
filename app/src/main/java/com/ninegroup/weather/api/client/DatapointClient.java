package com.ninegroup.weather.api.client;

import android.util.Log;

import com.ninegroup.weather.api.ApiService;
import com.ninegroup.weather.api.Datapoint;
import com.ninegroup.weather.api.DatapointRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatapointClient {
    private String accessToken = TokenClient.accessToken;
    private ApiService apiService = ApiClient.getClient(accessToken).create(ApiService.class);
    //private DatapointRequest body;
    public static List<Datapoint> datapointList;
    public static Boolean isDatapointRunning = true;

    public void getDatapoint(String attributeName, DatapointRequest body) {
        apiService.getDatapoint("5zI6XqkQVSfdgOrZ1MyWEf", attributeName, body)
                .enqueue(new Callback<List<Datapoint>>() {
                    @Override
                    public void onResponse(Call<List<Datapoint>> call, Response<List<Datapoint>> response) {
                        if (response.isSuccessful()) {
                            datapointList = response.body();
                            if (datapointList != null) {
                                System.out.println(datapointList);
                                System.out.println("x[2] = " + datapointList.get(2).getX());
                                System.out.println("y[2] = " + datapointList.get(2).getY());
                                Log.i("DATAPOINT API CALL", "x[2] = " + datapointList.get(2).getX());
                                Log.i("DATAPOINT API CALL", "y[2] = " + datapointList.get(2).getY());
                                //accessToken = token.getAccessToken();
                                isDatapointRunning = false;
                            }
                        } else {
                            Log.e("DATAPOINT API CALL", "API call unsuccessful! Your access token maybe expired or you don't have enough permissions.");
                            isDatapointRunning = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Datapoint>> call, Throwable t) {
                        Log.e("DATAPOINT API CALL", t.getMessage().toString());
                        isDatapointRunning = false;
                    }
                });
    }
}
