package com.example.com.myapplication;

import com.example.com.myapplication.entity.LoginRequest;
import com.example.com.myapplication.entity.LoginResponse;
import com.example.com.myapplication.entity.RegisterRequest;
import com.example.com.myapplication.entity.RegisterResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gaojjx on 28/10/2017.
 */

public interface Api {
    @GET
    Observable<LoginResponse> login(@Body LoginRequest request);

    @GET
    Observable<RegisterResponse> register(@Body RegisterRequest registerRequest);

    @GET("v2/movie/top250")
    Observable<Response<ResponseBody>> getTop250(@Query("start") int start, @Query("count") int count);
}
