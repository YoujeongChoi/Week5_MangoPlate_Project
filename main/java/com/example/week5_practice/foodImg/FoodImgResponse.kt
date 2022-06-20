package com.example.week5_practice.foodImg

data class FoodImgResponse(
    val display: Int,
    val items: MutableList<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)