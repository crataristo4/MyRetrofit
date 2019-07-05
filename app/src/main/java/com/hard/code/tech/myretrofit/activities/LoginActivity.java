package com.hard.code.tech.myretrofit.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hard.code.tech.myretrofit.R;
import com.hard.code.tech.myretrofit.clickhandler.LoginClickEvent;
import com.hard.code.tech.myretrofit.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding activityLoginBinding;
    LoginClickEvent loginClickEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginClickEvent = new LoginClickEvent(activityLoginBinding.txtEmailLayout, activityLoginBinding.txtPasswordLayout);
        activityLoginBinding.setLogin(loginClickEvent);
    }


    @Override
    protected void onStart() {
        super.onStart();
   /* if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            startActivity(new Intent(this, SplashScreenActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

        }*/

    }
}
