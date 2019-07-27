package com.trial.newsapp.service;

import com.trial.newsapp.entity.NewsApiResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("everything")
    public Call<NewsApiResponse> fetchAllArticles(@Query("q") String type, @Query("from") String date, @Query("sortBy") String sortBy, @Query("apiKey") String apiKey);
}
