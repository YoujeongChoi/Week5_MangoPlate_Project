package com.example.week5_practice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val sRetrofit = initRetrofit()
    private const val FOOD_URL = "https://openapi.naver.com/v1/search/local.json?"

    private fun initRetrofit() : Retrofit =
        Retrofit.Builder()
            .baseUrl(FOOD_URL)                               // 앞에 바뀌지 않는 url
            .addConverterFactory(GsonConverterFactory.create()) // json을 java class로 만들어주는 factory
            .build()                                            // 이렇게하면 비로소 retrofit 객체가 만들어짐
}