package com.trial.newsapp;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.trial.newsapp.adapter.ArticleAdapter;
import com.trial.newsapp.entity.Article;
import com.trial.newsapp.entity.NewsApiResponse;
import com.trial.newsapp.presenter.MainContract;
import com.trial.newsapp.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    ProgressBar progressBar;
    RecyclerView recyclerView;
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.articles_list);

        presenter = new MainPresenter(this);
        presenter.getAllArticles("bitcoin", "2019-06-02", "publishedAt", Constants.API_KEY);
    }


    @Override
    public void displayNewsArticles(NewsApiResponse response) {
        Log.w("NewsApp", Thread.currentThread()+ " in displayNewsArticles");
        recyclerView.setAdapter(new ArticleAdapter(response.getArticles()));
    }

    @Override
    public void showError(String message) {
        new AlertDialog.Builder(this).setMessage(message).show();
    }

    @Override
    public void showLoader() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void endLoader() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }
}
