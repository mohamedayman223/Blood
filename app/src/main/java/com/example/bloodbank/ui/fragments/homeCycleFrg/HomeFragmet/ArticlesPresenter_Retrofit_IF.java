package com.example.bloodbank.ui.fragments.homeCycleFrg.HomeFragmet;

import com.example.bloodbank.data.model.posts.Post;
import com.example.bloodbank.data.model.posts.PostLoading;

public interface ArticlesPresenter_Retrofit_IF {
    void getPosts(Post post, boolean isSuccess, String msg);

    void addRemoveFavPost(String msg);

}
