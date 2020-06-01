package com.fdepedis.myretroproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.fdepedis.myretroproject.adapter.PhotoAdapter;
import com.fdepedis.myretroproject.adapter.PostAdapter;
import com.fdepedis.myretroproject.model.RetroPhoto;
import com.fdepedis.myretroproject.model.RetroPost;
import com.fdepedis.myretroproject.network.GetDataService;
import com.fdepedis.myretroproject.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    private PostAdapter postAdapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        progressDialog = new ProgressDialog(PostActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        /* Create handle for the RetrofitInstance interface */
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<RetroPost>> call = service.getAllPosts();
        call.enqueue(new Callback<List<RetroPost>>() {
            @Override
            public void onResponse(Call<List<RetroPost>> call, Response<List<RetroPost>> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<RetroPost>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PostActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /* Method to generate List of data using RecyclerView with custom adapter */
    private void generateDataList(List<RetroPost> postList) {
        recyclerView = findViewById(R.id.postRecyclerView);
        postAdapter = new PostAdapter(this, postList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PostActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(postAdapter);
    }
}