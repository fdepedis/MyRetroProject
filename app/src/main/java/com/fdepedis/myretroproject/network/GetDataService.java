package com.fdepedis.myretroproject.network;

import com.fdepedis.myretroproject.model.RetroPhoto;
import com.fdepedis.myretroproject.model.RetroPost;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("/photos")
    Call<List<RetroPhoto>> getAllPhotos();

    @GET("/posts")
    Call<List<RetroPost>> getAllPosts();
}
