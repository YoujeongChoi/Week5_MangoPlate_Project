package com.example.week5_practice

data class FoodResponse(
    val display: Int,
    val items: List<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)