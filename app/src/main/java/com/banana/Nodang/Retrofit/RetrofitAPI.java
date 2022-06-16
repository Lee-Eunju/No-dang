package com.banana.Nodang.Retrofit;

import com.banana.Nodang.Pojo.ProductIngredientByProductName.MainI2790Response;
import com.banana.Nodang.Pojo.ProductNameByBarCode.MainC005Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitAPI {

    @GET("2b792d36aaa4472b8a5d/C005/json/1/1/BAR_CD={BAR_CD}")
    Call<MainC005Response> getProductNameByBarCode(@Path("BAR_CD") String BAR_CD);

    @GET("2b792d36aaa4472b8a5d/I2790/json/1/1/DESC_KOR={DESC_KOR}")
    Call<MainI2790Response> getProductIngredientByProductName(@Path("DESC_KOR") String DESC_KOR);
}
