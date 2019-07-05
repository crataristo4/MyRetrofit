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
import com.hard.code.tech.myretrofit.clickhandler.SettingsFragmentClickEvent;
import com.hard.code.tech.myretrofit.databinding.FragmentSettingsBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    private FragmentSettingsBinding fragmentSettingsBinding;
    private SettingsFragmentClickEvent settingsFragmentClickEvent;
    private SettingsFragmentClickEvent settingsFragmentClickEvent1;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentSettingsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);

        return fragmentSettingsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        settingsFragmentClickEvent = new SettingsFragmentClickEvent(fragmentSettingsBinding.txtChangeEmailLayout,
                fragmentSettingsBinding.txtChangeNameLayout, fragmentSettingsBinding.txtChangeSchoolLayout);

        settingsFragmentClickEvent1 = new SettingsFragmentClickEvent(fragmentSettingsBinding.txtCurrentPassLayout,
                fragmentSettingsBinding.txtNewPassLayout);

        fragmentSettingsBinding.setSaveUser(settingsFragmentClickEvent);
        fragmentSettingsBinding.setSettings(settingsFragmentClickEvent1);

    }
}
