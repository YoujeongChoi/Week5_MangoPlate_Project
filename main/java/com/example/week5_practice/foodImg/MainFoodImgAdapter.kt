package com.example.week5_practice.foodImg

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.week5_practice.MainActivity
import com.example.week5_practice.R
import com.example.week5_practice.databinding.MainFoodListItemBinding
import java.io.File

class MainFoodImgAdapter(val category :ArrayList<FoodImg>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
            RecyclerView.ViewHolder =
        MainFoodImgHolder(
            MainFoodListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                true
            )
        )


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val binding = (holder as MainFoodImgHolder).binding

        Glide.with(binding.foodListRl)
            .load(category)
            // .placeholder(R.drawable.img_file_place_holder)
            .error(R.drawable.food_example)
            .fallback(R.drawable.food_example)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(binding.foodListIv)


    }

    override fun getItemCount(): Int = category.size
}