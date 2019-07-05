package com.hard.code.tech.myretrofit.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hard.code.tech.myretrofit.R;
import com.hard.code.tech.myretrofit.clickhandler.WelcomeScreenClickEvent;
import com.hard.code.tech.myretrofit.databinding.ActivityWelcomePageBinding;
import com.hard.code.tech.myretrofit.storage.SharedPrefManager;

public class WelcomePageActivity extends AppCompatActivity {

    ActivityWelcomePageBinding welcomePageBinding;
    WelcomeScreenClickEvent welcomeScreenClickEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        welcomePageBinding = DataBindingUtil.setContentView(this, R.layout.activity_welcome_page);
        welcomeScreenClickEvent = new WelcomeScreenClickEvent(this);
        welcomePageBinding.setClickHandler(welcomeScreenClickEvent);


    }


    @Override
    protected void onStart() {
        super.onStart();
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            startActivity(new Intent(this, MainActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

        }
    }
}
