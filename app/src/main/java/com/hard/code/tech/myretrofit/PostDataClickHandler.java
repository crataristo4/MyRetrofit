package com.hard.code.tech.myretrofit;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.hard.code.tech.myretrofit.models.Posts;
import com.hard.code.tech.myretrofit.retrofit_instance.FakeDataRetrofitInstance;
import com.hard.code.tech.myretrofit.services.GetFakeDataApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDataClickHandler {

    private static final String TAG = "PostDataClickHandler";

    private EditText edtId, edtUserId, edtTitle, edtPost;

    public PostDataClickHandler(EditText edtId, EditText edtUserId, EditText edtTitle, EditText edtPost) {
        this.edtUserId = edtUserId;
        this.edtTitle = edtTitle;
        this.edtPost = edtPost;
        this.edtId = edtId;
    }

    public void postElements(View view) {
        int id = Integer.parseInt(edtId.getText().toString());
        int userID = Integer.parseInt(edtUserId.getText().toString());
        String title = edtTitle.getText().toString();
        String post = edtPost.getText().toString();

        if (!edtId.getText().toString().isEmpty() &&
                !edtUserId.getText().toString().isEmpty()
                && !title.isEmpty() && !post.isEmpty()) {
            Posts posts = new Posts(id, userID, title, post);

            GetFakeDataApi getFakeDataApi = FakeDataRetrofitInstance.getFakeData();
            Call<Posts> createPost = getFakeDataApi.newPost(posts);

            createPost.enqueue(new Callback<Posts>() {
                @Override
                public void onResponse(@NonNull Call<Posts> call, @NonNull Response<Posts> response) {
                    if (response.isSuccessful()) {
                        Log.i(TAG, "Id: " + response.body().getId());
                        Log.i(TAG, "userId: " + response.body().getUserId());
                        Log.i(TAG, "title: " + response.body().getTitle());
                        Log.i(TAG, "body: " + response.body().getBody());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Posts> call, @NonNull Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getMessage());
                }
            });

        }

        if (edtId.getText().toString().isEmpty()) {
            edtId.setEnabled(true);
            edtId.setError("id cannot be empty");

        } else if (edtUserId.getText().toString().isEmpty()) {
            edtUserId.setEnabled(true);
            edtUserId.setError("user id cannot be empty");

        } else if (title.isEmpty()) {
            edtTitle.setEnabled(true);
            edtTitle.setError("Title cannot be empty");

        } else if (post.isEmpty()) {
            edtPost.setEnabled(true);
            edtPost.setError("Post cannot be empty");

        }

    }


}
