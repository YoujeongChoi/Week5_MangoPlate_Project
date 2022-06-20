package com.example.week5_practice.foodImg

import com.example.week5_practice.foodList.FoodResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface FoodImgRetrofitInterface {

    @GET("search/image")        // 어떤 http 메소드인지 정의
    fun getFoodImgData(@Header("X-Naver-Client-Id")clientId:String,
                       @Header("X-Naver-Client-Secret")clientSecret:String,
                       @Query("query")query:String,
                       @Query("display")display:Int,
                       @Query("sort")sort:String,
                       @Query("filter")filter:String) : Call<FoodImgResponse>         // 보내는 방식 기입
    // Querystring을 Query라고 한다
}