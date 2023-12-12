package com.example.air_quality_monitoring_app.api;

import com.example.air_quality_monitoring_app.api.Token;
import com.example.air_quality_monitoring_app.api.UserAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import kotlin.jvm.Synchronized;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://uiot.ixxc.dev/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    static ApiService apiParam(String token ) {

        return new Retrofit.Builder()
                .baseUrl("https://uiot.ixxc.dev/api/master/user/user")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ApiService.class);

    }

    @GET("https://uiot.ixxc.dev/api/master/user/user")
    Call<UserAPI>  getUser(@Header ("Authorization") String token);

    @PUT("https://uiot.ixxc.dev/api/master/user/{realm}/reset-password/{id}")
    Call<UserAPI> resetPassword(@Header ("Authorization") String token, @Path("realm") String realm, @Path("id") String id, @Body Param param);

    @FormUrlEncoded
    @POST("auth/realms/master/protocol/openid-connect/token")
    Call<Token> getToken(@Field("client_id") String client_id,
                         @Field("username") String username,
                         @Field("password") String password,
                         @Field("grant_type") String grant_type
    );


//    @GET("https://uiot.ixxc.dev/api/master/asset/datapoint/export?attributeRefs=%5B%7B%22id%22%3A%225zI6XqkQVSfdgOrZ1MyWEf%22%2C%22name%22%3A%22temperature%22%7D%5D/{fromTimestamp}/{toTimestamp}")
//    Call<UserAPI>  getUser(@Header ("Authorization") String token);



}
