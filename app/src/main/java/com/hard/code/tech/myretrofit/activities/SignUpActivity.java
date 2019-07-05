package com.hard.code.tech.myretrofit.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hard.code.tech.myretrofit.R;
import com.hard.code.tech.myretrofit.clickhandler.RegisterUserClickHandler;
import com.hard.code.tech.myretrofit.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding activitySignUpBinding;
    RegisterUserClickHandler registerUserClickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activitySignUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);

        registerUserClickHandler = new RegisterUserClickHandler(this, activitySignUpBinding.emailLayout,
                activitySignUpBinding.PasswordLayout, activitySignUpBinding.fullNameLayout,
                activitySignUpBinding.schoolLayout);

        activitySignUpBinding.setRegisterUser(registerUserClickHandler);
    }
}
