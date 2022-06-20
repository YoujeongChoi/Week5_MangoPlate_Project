package com.example.week5_practice.foodList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week5_practice.databinding.MainFoodListItemBinding

class MainFoodListAdapter(val category :ArrayList<FoodList>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
            RecyclerView.ViewHolder =
        MainFoodListHolder(
            MainFoodListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MainFoodListHolder).binding
        binding.foodListAddress.text = category.get(position).address.toString()
        binding.foodListName.text = category.get(position).name.toString()
        binding.foodListScore.text = category.get(position).score.toString()
        binding.foodListCategory.text = category.get(position).category.toString()
        binding.foodListReview.text = category.get(position).review.toString()


    }

    override fun getItemCount(): Int = category.size
}