package com.hard.code.tech.myretrofit.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.hard.code.tech.myretrofit.R;
import com.hard.code.tech.myretrofit.databinding.ActivityMainBinding;
import com.hard.code.tech.myretrofit.fragments.AllUsersFragment;
import com.hard.code.tech.myretrofit.fragments.HomeFragment;
import com.hard.code.tech.myretrofit.fragments.SettingsFragment;
import com.hard.code.tech.myretrofit.storage.SharedPrefManager;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    FragmentManager fm = getSupportFragmentManager();
    Fragment homeFragment = new HomeFragment();
    Fragment allUsersFragment = new AllUsersFragment();
    Fragment settingsFragment = new SettingsFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.action_allUsers:
                        fragment = allUsersFragment;
                        break;
                    case R.id.action_home:
                        fragment = homeFragment;
                        break;
                    case R.id.action_settings:
                        fragment = settingsFragment;
                        break;
                }
                assert fragment != null;
                fm.beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right,
                                R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right)
                        .replace(R.id.frameLayout, fragment)
                        //.addToBackStack(null)
                        .commit();
                return true;
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            startActivity(new Intent(this, SplashScreenActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

        }


    }
}
