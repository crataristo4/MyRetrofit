package com.hard.code.tech.myretrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.hard.code.tech.myretrofit.adapter.CountryAdapter;
import com.hard.code.tech.myretrofit.databinding.ActivityStartBinding;
import com.hard.code.tech.myretrofit.models.CountryInfo;
import com.hard.code.tech.myretrofit.models.CountryResult;
import com.hard.code.tech.myretrofit.retrofit_instance.GetCountryRetrofitInstance;
import com.hard.code.tech.myretrofit.services.GetCountryDataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartActivity extends AppCompatActivity {
    private static final String TAG = "StartActivity";
    ArrayList<CountryResult> countryResults;
    ActivityStartBinding activityStartBinding;
    private ProgressDialog loading;
    private CountryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityStartBinding = DataBindingUtil.setContentView(this, R.layout.activity_start);
        setSupportActionBar(activityStartBinding.toolbar);


        loading = new ProgressDialog(this);


        getAllCountries();


    }

    private Object getAllCountries() {

        loading.setMessage("loading please wait");
        loading.show();

        GetCountryDataService instance = GetCountryRetrofitInstance.getDataService();
        Call<CountryInfo> countryInfoCall = instance.getCountries();

        countryInfoCall.enqueue(new Callback<CountryInfo>() {
            @Override
            public void onResponse(@NonNull Call<CountryInfo> call, @NonNull Response<CountryInfo> response) {
                CountryInfo countryInfo = response.body();

                if (countryInfo != null && countryInfo.getRestResponse() != null) {
                    countryResults = (ArrayList<CountryResult>) countryInfo.getRestResponse().getResult();

//                    for (CountryResult result : countryResults) {
//                        Log.i(TAG, "onResponse: " + result.getName());
//                    }
                    loading.dismiss();
                    displayData();

                }
            }

            private void displayData() {

                activityStartBinding.content.recyclerViewCountries.setHasFixedSize(true);
                activityStartBinding.content.recyclerViewCountries.setLayoutManager(new LinearLayoutManager(StartActivity.this));
                countryAdapter = new CountryAdapter(countryResults);
                activityStartBinding.content.recyclerViewCountries.setAdapter(countryAdapter);

            }

            @Override
            public void onFailure(@NonNull Call<CountryInfo> call, @NonNull Throwable t) {
                t.printStackTrace();
                makeToast(t.getMessage());
                loading.dismiss();
            }
        });
        return countryResults;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_albums) {
            startActivity(new Intent(StartActivity.this, AlbumActivity.class));
        } else if (item.getItemId() == R.id.action_hero) {
            startActivity(new Intent(StartActivity.this, HeroActivity.class));
        } else if ((item.getItemId() == R.id.action_posts)) {
            startActivity(new Intent(StartActivity.this, PostsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    void makeToast(String s) {
        Toast toast = Toast.makeText(this, s, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
