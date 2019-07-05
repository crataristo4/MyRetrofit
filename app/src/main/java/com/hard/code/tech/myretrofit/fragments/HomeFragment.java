package com.hard.code.tech.myretrofit.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hard.code.tech.myretrofit.R;
import com.hard.code.tech.myretrofit.databinding.FragmentHomeBinding;
import com.hard.code.tech.myretrofit.models.Users;
import com.hard.code.tech.myretrofit.storage.SharedPrefManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private View view;
    private FragmentHomeBinding fragmentHomeBinding;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        // Inflate the layout for this fragment
        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Users users = new Users(SharedPrefManager.getInstance(getActivity()).getUsa().getEmail(),
                SharedPrefManager.getInstance(getActivity()).getUsa().getName(),
                SharedPrefManager.getInstance(getActivity()).getUsa().getSchool());

        fragmentHomeBinding.setUsers(users);


    }
}
