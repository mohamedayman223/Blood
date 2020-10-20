package com.example.bloodbank.ui.fragments.homeCycleFrg.HomeFragmet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.adapters.ArticlesRecyclerAdapter;
import com.example.bloodbank.data.model.posts.Post;
import com.example.bloodbank.data.model.posts.PostData;
import com.example.bloodbank.helpers.HelperMe;
import com.example.bloodbank.ui.fragments.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class ArticlesFrg extends BaseFragment implements ArticlesPresenter_Retrofit_IF, ArticlesRecyclerAdapter.ArticleViewsClicked {
    @BindView(R.id.mainSearchView)
    SearchView mainSearchView;
    @BindView(R.id.articlesRecyclerView)
    RecyclerView articlesRecyclerView;
    @BindView(R.id.progress_Bar)
    ProgressBar progressBar;
    private ArticlesRecyclerAdapter adapter;
    private ArticlesPresenter presenter;
    private LinearLayoutManager linearLayoutManager;
    private List<PostData> postData = new ArrayList<>();
    private static final String API_TOKEN = "Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27";
    private boolean favProcess = false;
    private int itemPosition;

    public ArticlesFrg() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_articles_frg, container, false);
        HelperMe.setUnbinder(view, this);

        this.adapter = new ArticlesRecyclerAdapter(this);
        this.linearLayoutManager = new LinearLayoutManager(getContext());
        this.presenter = ArticlesPresenter.getInstance(this);
        this.presenter.getPosts(
                API_TOKEN,
                1);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void getPosts(Post post, boolean isSuccess, String msg) {
        this.progressBar.setVisibility(View.INVISIBLE);
        if (isSuccess) {
            this.postData = post.getData().getData();
            this.setRecyclerView();
        } else {
            Toast.makeText(getContext(), "" + msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void addRemoveFavPost(String msg) {
        // changed fav list item
        this.progressBar.setVisibility(View.INVISIBLE);
        this.postData.get(itemPosition).setIsFavourite(favProcess);
        this.adapter.setPostsList(this.postData);
    }

    private void setRecyclerView() {
        articlesRecyclerView.setLayoutManager(linearLayoutManager);
        this.adapter.setPostsList(postData);
        this.articlesRecyclerView.setHasFixedSize(true);
        this.articlesRecyclerView.setAdapter(adapter);
    }

    // adapter clicked


    @Override
    public void favIsClicked(int position, boolean process) {
        this.progressBar.setVisibility(View.VISIBLE);

        this.favProcess = process;
        this.itemPosition = position;
        this.presenter.add_removeFavPost(
                postData.get(position).getId(),
                API_TOKEN
        );
    }

    @Override
    public void titleClicked(int position) {

    }

    @Override
    public void imageClicked(int position) {

    }
}
