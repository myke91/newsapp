package com.trial.newsapp.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.trial.newsapp.R;
import com.trial.newsapp.entity.Article;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    private List<Article> articles;

    public ArticleAdapter(List<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_item, viewGroup, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articles.get(position);

        holder.title.setText(article.getTitle());
        holder.description.setText(article.getDescription());
        // Create glide request manager
        RequestManager requestManager = Glide.with(holder.itemView);
        RequestBuilder requestBuilder = requestManager.load(Uri.parse(article.getUrlToImage()));
        requestBuilder.into(holder.image);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView title, description;

        public ArticleViewHolder(@NonNull View view) {
            super(view);
            image = view.findViewById(R.id.imageView);
            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.description);
        }
    }

}
