package com.hard.code.tech.myretrofit;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.hard.code.tech.myretrofit.adapter.PostAdapter;
import com.hard.code.tech.myretrofit.databinding.ActivityPostsBinding;
import com.hard.code.tech.myretrofit.models.Posts;
import com.hard.code.tech.myretrofit.retrofit_instance.FakeDataRetrofitInstance;
import com.hard.code.tech.myretrofit.services.GetFakeDataApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsActivity extends AppCompatActivity {
    private static final String TAG = "PostsActivity";
    ActivityPostsBinding activityPostsBinding;
    PostAdapter postAdapter;
    PostDataClickHandler postDataClickHandler;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_posts);

        activityPostsBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_posts);
        loading = new ProgressDialog(this);
        postDataClickHandler = new
                PostDataClickHandler(activityPostsBinding.edtId, activityPostsBinding.edtUserId
                , activityPostsBinding.edtPostTitle,
                activityPostsBinding.edtBody);

        activityPostsBinding.setPost(postDataClickHandler);

        loadAllPosts();
    }

    private void loadAllPosts() {
        loading.setMessage("Please wait...");
        loading.show();
        activityPostsBinding.recyclerViewPosts.setHasFixedSize(true);
        activityPostsBinding.recyclerViewPosts.setLayoutManager(new LinearLayoutManager(this));

        GetFakeDataApi getFakeDataApi = FakeDataRetrofitInstance.getFakeData();
        Call<List<Posts>> listCall = getFakeDataApi.getAllPosts();

        listCall.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(@NonNull Call<List<Posts>> call, @NonNull Response<List<Posts>> response) {
                List<Posts> posts = response.body();
                if (response.isSuccessful()) {
                    loading.dismiss();
                    postAdapter = new PostAdapter(posts);
                    activityPostsBinding.recyclerViewPosts.setAdapter(postAdapter);

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<Posts>> call, @NonNull Throwable t) {
                loading.dismiss();
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

    }
}
