package com.hard.code.tech.myretrofit.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hard.code.tech.myretrofit.R;
import com.hard.code.tech.myretrofit.databinding.LayoutGetPostsBinding;
import com.hard.code.tech.myretrofit.models.Posts;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    List<Posts> postsList;

    public PostAdapter(List<Posts> postsList) {
        this.postsList = postsList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutGetPostsBinding binding = DataBindingUtil.
                inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.layout_get_posts, viewGroup, false);
        return new PostViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder postViewHolder, int i) {
        Posts posts = postsList.get(i);

        postViewHolder.layoutGetPostsBinding.setPosts(posts);
    }

    @Override
    public int getItemCount() {
        return postsList == null ? 0 : postsList.size();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {
        LayoutGetPostsBinding layoutGetPostsBinding;

        public PostViewHolder(@NonNull LayoutGetPostsBinding itemView) {
            super(itemView.getRoot());
            layoutGetPostsBinding = itemView;
        }
    }


}
