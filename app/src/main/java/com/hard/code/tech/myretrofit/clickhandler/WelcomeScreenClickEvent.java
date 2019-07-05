package com.hard.code.tech.myretrofit.clickhandler;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.hard.code.tech.myretrofit.activities.LoginActivity;
import com.hard.code.tech.myretrofit.activities.SignUpActivity;

public class WelcomeScreenClickEvent {

    private Context context;

    public WelcomeScreenClickEvent(Context context) {
        this.context = context;

    }

    //go to Login page
    public void onLogInBtnClicked(View view) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    //go to sign up page
    public void onSignUpBtnClicked(View view) {
        context.startActivity(new Intent(context, SignUpActivity.class));
    }
}
