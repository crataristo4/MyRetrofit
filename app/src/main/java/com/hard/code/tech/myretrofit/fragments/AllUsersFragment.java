package com.hard.code.tech.myretrofit.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hard.code.tech.myretrofit.R;
import com.hard.code.tech.myretrofit.adapter.AllUsersAdapter;
import com.hard.code.tech.myretrofit.clickhandler.DisplayViewUI;
import com.hard.code.tech.myretrofit.databinding.FragmentAllUsersBinding;
import com.hard.code.tech.myretrofit.models.Users;
import com.hard.code.tech.myretrofit.models.UsersResponse;
import com.hard.code.tech.myretrofit.retrofit_instance.MyApiRetrofitInstanceClient;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllUsersFragment extends Fragment {
    FragmentAllUsersBinding fragmentAllUsersBinding;
    AllUsersAdapter allUsersAdapter;

    public AllUsersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentAllUsersBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_users, container, false);

        return fragmentAllUsersBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpRecycler();

    }

    private void setUpRecycler() {

        fragmentAllUsersBinding.recyclerViewAllUsers.setHasFixedSize(true);
        fragmentAllUsersBinding.recyclerViewAllUsers.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<UsersResponse> call = MyApiRetrofitInstanceClient.getInstance().getApi().getAllUsers();

        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(@NonNull Call<UsersResponse> call, @NonNull Response<UsersResponse> response) {
                List<Users> usersList = null;
                if (response.body() != null && response.isSuccessful()) {
                    usersList = response.body().getUsers();
                    allUsersAdapter = new AllUsersAdapter(usersList, getActivity());
                    fragmentAllUsersBinding.recyclerViewAllUsers.setAdapter(allUsersAdapter);
                } else {
                    try {
                        DisplayViewUI.displayToast(getContext(), response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<UsersResponse> call, @NonNull Throwable t) {
                DisplayViewUI.displayToast(getContext(), t.getMessage());

            }
        });


    }
}
