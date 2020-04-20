package com.ankita.secondtask.repository;

import com.ankita.secondtask.modals.CreateResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/api/users?")
    Call<CreateResponse> doGetListResour(@Query("offset") int offset, @Query("limit") int limit);
}
