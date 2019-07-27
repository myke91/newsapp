package com.trial.newsapp.presenter;

import com.trial.newsapp.entity.NewsApiResponse;


public interface MainContract {
    interface View {
        void showLoader();

        void displayNewsArticles(NewsApiResponse articles);

        void showError(String message);

        void endLoader();
    }

    interface Presenter {
        void getAllArticles(String type, String date, String sort, String apiKey);
    }

}
