package com.hard.code.tech.myretrofit.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hard.code.tech.myretrofit.R;
import com.hard.code.tech.myretrofit.databinding.LayoutAllUsersBinding;
import com.hard.code.tech.myretrofit.models.Users;

import java.util.List;

public class AllUsersAdapter extends RecyclerView.Adapter<AllUsersAdapter.ShowUsersViewHolder> {

    List<Users> usersList;
    Context context;

    public AllUsersAdapter(List<Users> usersList, Context context) {
        this.usersList = usersList;
        this.context = context;
    }

    @NonNull
    @Override
    public ShowUsersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutAllUsersBinding layoutAllUsersBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.layout_all_users, viewGroup, false);

        return new ShowUsersViewHolder(layoutAllUsersBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowUsersViewHolder showUsersViewHolder, int i) {

        Users users = usersList.get(i);
        showUsersViewHolder.layoutAllUsersBinding.setDisplayUsers(users);

    }

    @Override
    public int getItemCount() {
        return usersList == null ? 0 : usersList.size();
    }

    static class ShowUsersViewHolder extends RecyclerView.ViewHolder {

        LayoutAllUsersBinding layoutAllUsersBinding;

        public ShowUsersViewHolder(@NonNull LayoutAllUsersBinding layoutAllUsersBinding) {
            super(layoutAllUsersBinding.getRoot());

            this.layoutAllUsersBinding = layoutAllUsersBinding;
        }

    }
}
