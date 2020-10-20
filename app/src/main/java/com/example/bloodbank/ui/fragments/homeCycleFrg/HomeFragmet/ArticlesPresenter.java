package com.example.bloodbank.ui.fragments.homeCycleFrg.HomeFragmet;

import com.example.bloodbank.data.model.posts.Post;
import com.example.bloodbank.data.retrofit.RetrofitFunctions;

public class ArticlesPresenter implements ArticlesPresenter_Retrofit_IF {

    private static ArticlesPresenter INSTANCE;
    private ArticlesPresenter_Retrofit_IF articlesInterface;

    private ArticlesPresenter(ArticlesPresenter_Retrofit_IF articlesInterface) {
        this.articlesInterface = articlesInterface;
    }

    public static ArticlesPresenter getInstance(ArticlesPresenter_Retrofit_IF articlesInterface) {
        if (INSTANCE == null) {
            INSTANCE = new ArticlesPresenter(articlesInterface);
        }
        return INSTANCE;
    }

    public void getPosts(String tokinApi, int pages) {
        RetrofitFunctions.getPosts(tokinApi, pages, this);
    }

    public void add_removeFavPost(int post_id, String api_token) {
        RetrofitFunctions.add_removeFavPostRetrofit(post_id, api_token, this);
    }

    @Override
    public void getPosts(Post post, boolean isSuccess, String msg) {
        articlesInterface.getPosts(post, isSuccess, msg);
    }

    @Override
    public void addRemoveFavPost(String msg) {
        articlesInterface.addRemoveFavPost(msg);
    }


}
