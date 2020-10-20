package com.example.bloodbank.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.data.model.posts.PostData;
import com.example.bloodbank.helpers.HelperMe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ArticlesRecyclerAdapter extends RecyclerView.Adapter<ArticlesRecyclerAdapter.MyViewHolder> {

    View view;
    private List<PostData> postList;
    private ArticleViewsClicked articleViewsClicked;

    public ArticlesRecyclerAdapter(ArticleViewsClicked articleViewsClicked) {
        this.postList = new ArrayList<>();
        this.articleViewsClicked = articleViewsClicked;
    }

    public void setPostsList(List<PostData> postsList) {
        this.postList = postsList;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.articles_item_recycler,
                parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String imageUrl = this.postList.get(position).getThumbnailFullPath();
        if (!imageUrl.isEmpty()) {
            Picasso.get()
                    .load(this.postList.get(position).getThumbnailFullPath())
                    .into(holder.articlePicRecycler);
        }
        if (this.postList.get(position).getIsFavourite()) {
            holder.articlesFavPic.setImageResource(R.drawable.ic_fav_pressed);
        } else {
            holder.articlesFavPic.setImageResource(R.drawable.ic_fav);
        }
        holder.articleTitle.setText(this.postList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.articlePic_recycler)
        ImageView articlePicRecycler;
        @BindView(R.id.articleTitle)
        TextView articleTitle;
        @BindView(R.id.articlesFavPic)
        ImageView articlesFavPic;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            HelperMe.setUnbinder(itemView, this);
        }

        @OnClick({R.id.articlePic_recycler, R.id.articleTitle, R.id.articlesFavPic})
        public void onViewClicked(View view) {
            int pos = this.getAdapterPosition();
            switch (view.getId()) {
                case R.id.articlePic_recycler:
                    articleViewsClicked.imageClicked(pos);
                    break;
                case R.id.articleTitle:
                    articleViewsClicked.titleClicked(pos);
                    break;
                case R.id.articlesFavPic:
                    boolean process = false;
                    if (!postList.get(pos).getIsFavourite()) {
                        process = true;
                    }
                    articleViewsClicked.favIsClicked(pos, process);
                    break;
            }
        }

    }

    public interface ArticleViewsClicked {
        void favIsClicked(int position, boolean process);

        void titleClicked(int position);

        void imageClicked(int position);
    }
}
