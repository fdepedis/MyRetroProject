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
import com.fdepedis.myretroproject.network.GetDataService;
import com.fdepedis.myretroproject.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoActivity extends AppCompatActivity {

    private PhotoAdapter photoAdapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        progressDialog = new ProgressDialog(PhotoActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        /* Create handle for the RetrofitInstance interface */
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<RetroPhoto>> call = service.getAllPhotos();
        call.enqueue(new Callback<List<RetroPhoto>>() {
            @Override
            public void onResponse(Call<List<RetroPhoto>> call, Response<List<RetroPhoto>> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PhotoActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /* Method to generate List of data using RecyclerView with custom adapter */
    private void generateDataList(List<RetroPhoto> photoList) {
        recyclerView = findViewById(R.id.photoRecyclerView);
        photoAdapter = new PhotoAdapter(this, photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PhotoActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(photoAdapter);
    }
}