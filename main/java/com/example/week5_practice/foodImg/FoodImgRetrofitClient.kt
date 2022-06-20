package com.example.week5_practice.foodImg

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FoodImgRetrofitClient {

    val fRetrofit = initRetrofit()
    private const val IMG_URL = "https://openapi.naver.com/v1/"

    private fun initRetrofit() : Retrofit =
        Retrofit.Builder()
            .baseUrl(IMG_URL)                               // 앞에 바뀌지 않는 url
            .addConverterFactory(GsonConverterFactory.create()) // json을 java class로 만들어주는 factory
            .build()                                            // 이렇게하면 비로소 retrofit 객체가 만들어짐
}