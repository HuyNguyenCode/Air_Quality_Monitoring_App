package com.ninegroup.weather.api.client;

import android.util.Log;

import com.ninegroup.weather.api.ApiService;
import com.ninegroup.weather.api.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TokenClient {
    private ApiService apiService = ApiClient.getClientNoToken().create(ApiService.class);
    private Token token;
    public static String accessToken = null;
    public static Boolean isRunning = true;

    public void getToken(String username, String password) {
        apiService.getToken("openremote", username, password, "password")
                .enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()) {
                    token = response.body();
                    if (token != null) {
                        Log.d("TOKEN API CALL", token.getAccessToken());
                        Log.d("TOKEN API CALL", token.getTokenType());
                        accessToken = token.getAccessToken();
                        isRunning = false;
                    }
                } else {
                    Log.d("TOKEN API CALL", "API call unsuccessful! Your access token maybe expired or you don't have enough permissions.");
                    isRunning = false;
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Log.d("TOKEN API CALL", t.getMessage().toString());
                isRunning = false;
            }
        });
    }
}
