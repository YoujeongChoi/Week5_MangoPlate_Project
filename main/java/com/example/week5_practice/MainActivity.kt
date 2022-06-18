package com.example.week5_practice

import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week5_practice.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.ThreadLocalRandom
import kotlin.math.round

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var id = "vptSfobFaKbqhGD92XW2"
    private var secret = "uZ3rimjFFL"
    private val NUM_PAGES = 5
    private var food_list_array = ArrayList<FoodList>()

    data class FoodListArray(
        val address :String,
        val name : String,
        val category: String,
        val score:Double,
        val review :String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getFoodData(id, secret, "용산구 맛집", 20, 1)

        val myLayoutManager = GridLayoutManager(this, 2)
        binding.mainListRv.layoutManager = myLayoutManager

        getFoodData(id, secret, "용산구 맛집", 20, 1)

        val adapter = MainFoodListAdapter(food_list_array)
        // val layoutManager_food = GridLayoutManager(this, 2)
        binding.mainListRv.layoutManager = LinearLayoutManager(this)
        binding.mainListRv.adapter = adapter


//
//        class ScreenSlidePageFragment : Fragment() {
//
//            override fun onCreateView(
//                inflater: LayoutInflater,
//                container: ViewGroup?,
//                savedInstanceState: Bundle?
//            ): View = inflater.inflate(R.layout.fragment_screen_slide_page, container, false)
//        }
    }

    private fun getFoodData(clientId: String, clientSecret: String ,search: String, display: Int, start: Int) {
        //retrofit 인터페이스 가져오기
        val weatherDataInterface = RetrofitClient.sRetrofit.create(RetrofitInterface::class.java)
        weatherDataInterface.getWeatherData(clientId, clientSecret, search, display, start).enqueue(object :
            Callback<FoodResponse>{
            override fun onResponse(                    // response 가 제대로 불러왔을 때
                call: Call<FoodResponse>,
                response: Response<FoodResponse>
            ) {
                if(response.isSuccessful) {
                    val result = response.body() as FoodResponse
                    var food = arrayListOf<FoodList>()

                    for (i in 1 until result.items.size) {
                        var randomInteger = ThreadLocalRandom.current().nextInt(1, 100)
                        var review = randomInteger

                        var randomDouble = ThreadLocalRandom.current().nextDouble(1.0, 5.0)
                        var score  = randomDouble
                        score = round(score*100)/100

                        var index = i + 1
                        var title = result.items[i].title
                        var varFoodList = FoodList(result.items[i].address, "$index. $title", result.items[i].category, score, "(리뷰 $review)" )
                        food.add(varFoodList)
                    }

                    Log.d(ContentValues.TAG, "MainActivity - onResponse() called")
                } else {
                    Log.d(ContentValues.TAG, "통신실패 - onResponse() called")
                }
            }

            override fun onFailure(call: Call<FoodResponse>, t: Throwable) {         // 서버통신 실패했을 때
                Log.d(ContentValues.TAG, "통신실패 - onFailure() called")
            }

        })
    }
}



