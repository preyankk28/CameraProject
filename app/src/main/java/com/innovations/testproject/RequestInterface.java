package com.innovations.testproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {

    @GET("b/VGOL")
    Call<List<ImageModelSub>> getImagessuccess();

}
