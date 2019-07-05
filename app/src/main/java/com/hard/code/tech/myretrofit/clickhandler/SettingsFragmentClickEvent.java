package com.hard.code.tech.myretrofit.clickhandler;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.hard.code.tech.myretrofit.activities.SplashScreenActivity;
import com.hard.code.tech.myretrofit.models.DefaultResponse;
import com.hard.code.tech.myretrofit.models.LoginResponse;
import com.hard.code.tech.myretrofit.models.Users;
import com.hard.code.tech.myretrofit.retrofit_instance.MyApiRetrofitInstanceClient;
import com.hard.code.tech.myretrofit.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsFragmentClickEvent {
    private static final String TAG = "SettingsFragmentClickEv";

    private Context context;
    private TextInputLayout txtEmail, txtName, txtSchool;
    private TextInputLayout txtCurrentPass, txtNewPass;

    public SettingsFragmentClickEvent() {
    }

    public SettingsFragmentClickEvent(Context context) {
        this.context = context;

    }

    public SettingsFragmentClickEvent(TextInputLayout txtEmail, TextInputLayout txtName, TextInputLayout txtSchool) {
        this.txtEmail = txtEmail;
        this.txtName = txtName;
        this.txtSchool = txtSchool;
    }

    public SettingsFragmentClickEvent(TextInputLayout txtCurrentPass, TextInputLayout txtNewPass) {
        this.txtCurrentPass = txtCurrentPass;
        this.txtNewPass = txtNewPass;
    }

    private void validateEditProfileInputs(final View view) {
        final ProgressDialog loading = DisplayViewUI.displayProgress(view.getContext(), "please wait saving your profile...");

        String email = txtEmail.getEditText().getText().toString();
        String name = txtName.getEditText().getText().toString();
        String school = txtSchool.getEditText().getText().toString();

        final Users user = SharedPrefManager.getInstance(view.getContext()).getUsa();


        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                !name.isEmpty() && !school.isEmpty()) {

            loading.show();

            txtEmail.setErrorEnabled(false);
            txtName.setErrorEnabled(false);
            txtSchool.setErrorEnabled(false);


            Call<LoginResponse> call = MyApiRetrofitInstanceClient.getInstance()
                    .getApi().updateUser(user.getId(), email, name, school);


            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                    loading.dismiss();
                    LoginResponse loginResponse = response.body();
                    if (!loginResponse.getError()) {
                        DisplayViewUI.displayToast(view.getContext(), response.body().getMessage());
                        Log.i(TAG, "TOAST: " + response.body().getMessage());
                        //    SharedPrefManager.getInstance(view.getContext()).saveUsers(loginResponse.getUsers());

                    }


                    //  SharedPrefManager.getInstance(context).saveUsers(response.body().getUsers());
                    //  Log.i(TAG, "onResponse: " + response.body().getUsers());


                }

                @Override
                public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                    loading.dismiss();
                    Log.e(TAG, "onFailure: " + t.getMessage());
                    // DisplayViewUI.displayToast(view.getContext(), t.getLocalizedMessage());
                }
            });


        } else if (email.isEmpty()) {
            txtEmail.setErrorEnabled(true);
            txtEmail.setError("please enter your email");
            return;
        } else if (name.isEmpty()) {
            txtName.setErrorEnabled(true);
            txtName.setError("please enter your full name");
            return;
        } else if (school.isEmpty()) {
            txtSchool.setErrorEnabled(true);
            txtSchool.setError("please enter your school");
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txtEmail.setErrorEnabled(true);
            txtEmail.setError("please check your email");
            return;
        }

    }

    public void onSaveBtnClicked(View view) {
        validateEditProfileInputs(view);
    }

    public void onLogOutClicked(final View view) {
        SharedPrefManager.getInstance(view.getContext()).clear();

        DisplayViewUI.displayAlertDialog(view.getContext(), "Log out", "Are you sure you want to log out?"
                , "YES", "NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == -1) {
                            view.getContext().startActivity(new Intent(view.getContext(), SplashScreenActivity.class)
                                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                        } else if (which == -2) {
                            dialog.dismiss();
                        }

                    }
                });


    }


    void validateInputPass(final View view) {
        final ProgressDialog loading = DisplayViewUI.displayProgress(view.getContext(), "updating your password...");

        String currentPass = txtCurrentPass.getEditText().getText().toString();
        String newPass = txtNewPass.getEditText().getText().toString();

        Users user = SharedPrefManager.getInstance(context).getUsa();


        if (!currentPass.isEmpty() && !newPass.isEmpty()) {

            loading.show();

            Call<DefaultResponse> call = MyApiRetrofitInstanceClient.getInstance()
                    .getApi().updatePass(user.getEmail(), currentPass, newPass);

            call.enqueue(new Callback<DefaultResponse>() {
                @Override
                public void onResponse(@NonNull Call<DefaultResponse> call, @NonNull Response<DefaultResponse> response) {

                    loading.dismiss();
                    DisplayViewUI.displayToast(view.getContext(), response.body().getMessage());
                }

                @Override
                public void onFailure(@NonNull Call<DefaultResponse> call, @NonNull Throwable t) {
                    loading.dismiss();
                }
            });

        } else if (currentPass.isEmpty()) {
            txtCurrentPass.setErrorEnabled(true);
            txtCurrentPass.setError("enter your previous password");
            return;

        } else if (newPass.isEmpty()) {
            txtNewPass.setErrorEnabled(true);
            txtNewPass.setError("enter your new password");
            return;
        }

    }


    public void onPasswordChanged(View view) {
        validateInputPass(view);
    }

    public void onDeleteAccountPressed(final View view) {
        DisplayViewUI.displayAlertDialog(view.getContext(), "Delete Account", "Are you sure you want to delete your account?", "YES", "NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == -1) {
                    deleteAccount(view);
                } else if (which == -2) {
                    dialog.dismiss();
                }
            }
        });
    }

    private void deleteAccount(final View view) {
        final ProgressDialog loading = DisplayViewUI.displayProgress(view.getContext(), "Deleting your account");
        loading.show();
        Users users = SharedPrefManager.getInstance(context).getUsa();

        Call<DefaultResponse> call = MyApiRetrofitInstanceClient.getInstance()
                .getApi().deleteUser(users.getId());

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(@NonNull Call<DefaultResponse> call, @NonNull Response<DefaultResponse> response) {
                DisplayViewUI.displayToast(view.getContext(), response.body().getMessage());

                assert response.body() != null;
                if (!response.body().isError()) {
                    loading.dismiss();
                    SharedPrefManager.getInstance(context).clear();
                    view.getContext().startActivity(new Intent(view.getContext(), SplashScreenActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                }


            }

            @Override
            public void onFailure(@NonNull Call<DefaultResponse> call, @NonNull Throwable t) {
                loading.dismiss();
            }
        });
    }

}
