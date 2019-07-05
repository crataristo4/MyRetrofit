package com.hard.code.tech.myretrofit.clickhandler;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.hard.code.tech.myretrofit.activities.MainActivity;
import com.hard.code.tech.myretrofit.models.LoginResponse;
import com.hard.code.tech.myretrofit.retrofit_instance.MyApiRetrofitInstanceClient;
import com.hard.code.tech.myretrofit.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginClickEvent {
    private static final String TAG = "LoginClickEvent";
    private TextInputLayout txtEmail, txtPassword;

    public LoginClickEvent(TextInputLayout txtEmail, TextInputLayout txtPassword) {
        this.txtEmail = txtEmail;
        this.txtPassword = txtPassword;
    }


    private void validateInputs(final View view) {
        final ProgressDialog loading = DisplayViewUI
                .displayProgress(view.getContext(), "logging you in...");

        String email = txtEmail.getEditText().getText().toString();
        String password = txtPassword.getEditText().getText().toString();


        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                !password.isEmpty() && password.length() >= 8) {

            txtEmail.setErrorEnabled(false);
            txtPassword.setErrorEnabled(false);


            loading.show();

            Call<LoginResponse> call = MyApiRetrofitInstanceClient.getInstance()
                    .getApi().logInUser(email, password);

            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                    LoginResponse loginResponse = response.body();

                    if (!loginResponse.getError()) {

                        //save user
                        clear();
                        loading.dismiss();


                        SharedPrefManager.getInstance(view.getContext())
                                .saveUsers(loginResponse.getUsers());

                        Log.i(TAG, "onResponse: " + loginResponse.getUsers());

                        view.getContext().startActivity(new Intent(view.getContext(), MainActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                    } else {
                        loading.dismiss();
                        DisplayViewUI.displayToast(view.getContext(), loginResponse.getMessage());

                    }


                }

                @Override
                public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                    loading.dismiss();
                    DisplayViewUI.displayToast(view.getContext(), t.getMessage());

                }
            });


        } else if (email.isEmpty()) {
            txtEmail.setErrorEnabled(true);
            txtEmail.setError("please enter your email");
            loading.dismiss();
            return;
        } else if (password.isEmpty()) {
            txtPassword.setErrorEnabled(true);
            loading.dismiss();
            txtPassword.setError("please enter your password");
            return;
        } else if (password.length() < 8) {
            txtPassword.setErrorEnabled(true);
            txtPassword.setError("password is too short");
            loading.dismiss();
            return;
        }

    }

    public void onLoginUserClick(View view) {
        validateInputs(view);
    }


    void clear() {
        txtEmail.getEditText().setText("");
        txtPassword.getEditText().setText("");


    }


}
