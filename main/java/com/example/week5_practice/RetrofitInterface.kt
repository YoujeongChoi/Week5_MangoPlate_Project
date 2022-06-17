package com.example.week5_practice

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("food")        // 어떤 http 메소드인지 정의
    fun getWeatherData(@Header("X-Naver-Client-Id")clientId:String,
                       @Header("X-Naver-Client-Secret")clientSecret:String,
                       @Query("query")query:String,
                       @Query("display")display:Int,
                       @Query("start")start:Int) : Call<FoodResponse>         // 보내는 방식 기입
    // Querystring을 Query라고 한다
}