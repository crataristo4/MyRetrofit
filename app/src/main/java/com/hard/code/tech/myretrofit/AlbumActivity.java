package com.hard.code.tech.myretrofit;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.widget.Toast;

import com.hard.code.tech.myretrofit.adapter.FakeAlbumAdapter;
import com.hard.code.tech.myretrofit.databinding.ActivityAlbumBinding;
import com.hard.code.tech.myretrofit.models.Albums;
import com.hard.code.tech.myretrofit.retrofit_instance.FakeDataRetrofitInstance;
import com.hard.code.tech.myretrofit.services.GetFakeDataApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumActivity extends AppCompatActivity {

    private static final String TAG = "AlbumActivity";
    //    private RecyclerView recyclerView;
    private FakeAlbumAdapter fakeAlbumAdapter;
    private ProgressDialog loading;
    private ActivityAlbumBinding activityAlbumBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_album);
        activityAlbumBinding = DataBindingUtil.setContentView(this, R.layout.activity_album);

        loading = new ProgressDialog(this);
        loading.setMessage("loading please wait");
        loading.show();

        activityAlbumBinding.recyclerViewAlbum.setHasFixedSize(true);
        activityAlbumBinding.recyclerViewAlbum.setLayoutManager(new LinearLayoutManager(this));

//        recyclerView = findViewById(R.id.recyclerViewAlbum);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    /*    Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/

//        API api = retrofit.create(API.class);


        GetFakeDataApi getAlbumApi = FakeDataRetrofitInstance.getFakeData();
        Call<List<Albums>> call1 = getAlbumApi.getAllAlbums();

        call1.enqueue(new Callback<List<Albums>>() {
            @Override
            public void onResponse(@NonNull Call<List<Albums>> call,
                                   @NonNull Response<List<Albums>> response) {
                List<Albums> albumsList = response.body();
                assert albumsList != null;
                String[] title = new String[albumsList.size()];


                if (response.body() != null && response.isSuccessful()) {
                    loading.dismiss();
                    fakeAlbumAdapter = new FakeAlbumAdapter(AlbumActivity.this, albumsList);
                    activityAlbumBinding.recyclerViewAlbum.setAdapter(fakeAlbumAdapter);
                }


            }

            @Override
            public void onFailure(@NonNull Call<List<Albums>> call, @NonNull Throwable t) {
                makeToast("Error " + t.getMessage());
                loading.dismiss();
            }
        });


    }

    void makeToast(String s) {
        Toast toast = Toast.makeText(this, s, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
