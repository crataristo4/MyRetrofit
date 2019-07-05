package com.hard.code.tech.myretrofit.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.hard.code.tech.myretrofit.models.Users;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "my_shared_pref";
    protected static SharedPrefManager instance;
    private Context context;

    private SharedPrefManager(Context context) {
        this.context = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefManager(context);
        }

        return instance;
    }

    //save user details
    public void saveUsers(Users users) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id", users.getId());
        editor.putString("name", users.getName());
        editor.putString("email", users.getEmail());
        editor.putString("school", users.getSchool());

        editor.apply();
    }


    //check if user is already logged in
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, context.MODE_PRIVATE);

        return sharedPreferences.getInt("id", -1) != -1;
    }


    //get user logged in
    public Users getUsa() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, context.MODE_PRIVATE);

        return new Users(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("name", null),
                sharedPreferences.getString("school", null)
        );
    }


    //clear content when the user logs out
    public void clear() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
