package com.fdepedis.myretroproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fdepedis.myretroproject.adapter.PhotoAdapter;
import com.fdepedis.myretroproject.model.RetroPhoto;
import com.fdepedis.myretroproject.network.GetDataService;
import com.fdepedis.myretroproject.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnPhoto;
    Button btnPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPhoto = findViewById(R.id.btnPhoto);
        btnPost = findViewById(R.id.btnPost);

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent btnIntentPhoto = new Intent(getApplicationContext(), PhotoActivity.class);
                startActivity(btnIntentPhoto);
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent btnIntentPost = new Intent(getApplicationContext(), PostActivity.class);
                startActivity(btnIntentPost);
            }
        });
    }

}
