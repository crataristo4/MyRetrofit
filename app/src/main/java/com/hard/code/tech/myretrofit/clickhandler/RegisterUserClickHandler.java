package com.hard.code.tech.myretrofit.clickhandler;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.view.View;

import com.hard.code.tech.myretrofit.activities.LoginActivity;
import com.hard.code.tech.myretrofit.constants.Constants;
import com.hard.code.tech.myretrofit.models.DefaultResponse;
import com.hard.code.tech.myretrofit.retrofit_instance.MyApiRetrofitInstanceClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterUserClickHandler {

    private static final String TAG = "RegisterUser";
    private Context context;
    private TextInputLayout txtEmail, txtPassword, txtName, txtSchool;

    public RegisterUserClickHandler(Context context, TextInputLayout txtEmail, TextInputLayout txtPassword,
                                    TextInputLayout txtName, TextInputLayout txtSchool) {
        this.context = context;
        this.txtEmail = txtEmail;
        this.txtPassword = txtPassword;
        this.txtName = txtName;
        this.txtSchool = txtSchool;
    }

    private void validateInputs(final View view) {
        final ProgressDialog loading = DisplayViewUI.displayProgress(view.getContext(),
                "please wait while we register you...");

        String email = txtEmail.getEditText().getText().toString();
        String password = txtPassword.getEditText().getText().toString();
        String name = txtName.getEditText().getText().toString();
        String school = txtSchool.getEditText().getText().toString();


        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                !password.isEmpty() && password.length() >= 8 &&
                !name.isEmpty() && !school.isEmpty()) {

            txtEmail.setErrorEnabled(false);
            txtPassword.setErrorEnabled(false);
            txtName.setErrorEnabled(false);
            txtSchool.setErrorEnabled(false);

            loading.show();

            Call<DefaultResponse> call = MyApiRetrofitInstanceClient.getInstance()
                    .getApi().createUser(email, password, name, school);

            call.enqueue(new Callback<DefaultResponse>() {
                @Override
                public void onResponse(@NonNull Call<DefaultResponse> call, @NonNull Response<DefaultResponse> response) {
                    String message = null;
                    DefaultResponse defaultResponse = response.body();

                    if (response.isSuccessful() && response.code() == Constants.SUCCESS_MESSAGE) {
                        loading.dismiss();
                        clear();
                        DisplayViewUI.displayToast(view.getContext(), defaultResponse.getMessage());
                        context.startActivity(new Intent(context, LoginActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                    } else if (response.code() == Constants.USER_EXISTS) {
                        loading.dismiss();
                        DisplayViewUI.displayToast(view.getContext(), "User already exists");
                    } else if (response.code() == Constants.USER_CREATED_FAILURE) {
                        loading.dismiss();
                        DisplayViewUI.displayToast(view.getContext(), "Sorry , some error occurred");
                    }




                /*    if (message != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(message);
                            makeToast(view.getContext(), jsonObject.getString("message"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }*/


                }

                @Override
                public void onFailure(@NonNull Call<DefaultResponse> call, @NonNull Throwable t) {
                    loading.dismiss();
                    DisplayViewUI.displayToast(view.getContext(), t.getMessage());

                }
            });


        } else if (email.isEmpty()) {
            txtEmail.setErrorEnabled(true);
            txtEmail.setError("please enter your email");
        } else if (password.isEmpty()) {
            txtPassword.setErrorEnabled(true);
            txtPassword.setError("please enter your password");
        } else if (password.length() < 8) {
            txtPassword.setErrorEnabled(true);
            txtPassword.setError("password is too short");
        } else if (name.isEmpty()) {
            txtName.setErrorEnabled(true);
            txtName.setError("please enter your full name");
        } else if (school.isEmpty()) {
            txtSchool.setErrorEnabled(true);
            txtSchool.setError("please enter your school");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txtEmail.setErrorEnabled(true);
            txtEmail.setError("please check your email");
        }

    }

    public void onRegisterUserClick(View view) {
        validateInputs(view);
    }


    void clear() {
        txtEmail.getEditText().setText("");
        txtPassword.getEditText().setText("");
        txtName.getEditText().setText("");
        txtSchool.getEditText().setText("");

    }
}
