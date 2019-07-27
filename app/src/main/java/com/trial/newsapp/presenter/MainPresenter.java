package com.trial.newsapp.presenter;

import com.trial.newsapp.Constants;
import com.trial.newsapp.entity.NewsApiResponse;
import com.trial.newsapp.service.ServiceUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter {
    MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void getAllArticles(String type, String date, String sort, String apiKey) {
        view.showLoader();
        Call call = ServiceUtil.getService().fetchAllArticles(type, date, sort, Constants.API_KEY);

        call.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                view.endLoader();
                if (response.body() == null) {
                   view.showError("response not ok");
                   return;
                }
                view.displayNewsArticles(response.body());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                view.endLoader();
                view.showError(t.getLocalizedMessage());
            }
        });
    }
}
