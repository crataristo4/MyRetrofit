package com.hard.code.tech.myretrofit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.hard.code.tech.myretrofit.models.Hero;
import com.hard.code.tech.myretrofit.retrofit_instance.RetrofitInstance;
import com.hard.code.tech.myretrofit.services.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);

        final ListView listView = findViewById(R.id.listViewHero);

        API api = RetrofitInstance.getData();
        Call<List<Hero>> call = api.getAllMarvel();

        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(@NonNull Call<List<Hero>> call, @NonNull Response<List<Hero>> response) {
                List<Hero> heroes = response.body();

                assert heroes != null;
                String[] heroNames = new String[heroes.size()];

                //loop through the list and display all the names in the list view
                for (int i = 0; i < heroes.size(); i++) {
                    heroNames[i] = heroes.get(i).getName();
                }

                listView.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, heroNames));
//display items in a log for testing
                //*assert heroes != null;
           /*     for (Hero h : heroes) {
                    Log.i(TAG, "name: " + h.getName());
                    Log.i(TAG, "real Name: " + h.getRealname());
                    Log.i(TAG, "image: " + h.getImageurl());

                }*/
            }

            @Override
            public void onFailure(@NonNull Call<List<Hero>> call, @NonNull Throwable t) {
                makeToast("Error " + t.getMessage());
            }
        });

    }


    void makeToast(String s) {
        Toast toast = Toast.makeText(this, s, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
