package com.example.week5_practice

import android.content.ContentValues.TAG

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week5_practice.databinding.ActivityMainBinding
import com.example.week5_practice.foodImg.*
import com.example.week5_practice.foodImg.MainFoodImgAdapter
import com.example.week5_practice.foodList.*
// import com.example.week5_practice.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.ThreadLocalRandom
import kotlin.math.round

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var id = "vptSfobFaKbqhGD92XW2"
    private var secret = "uZ3rimjFFL"
    private var food_list_array = ArrayList<FoodList>()
    private var food_img_array = ArrayList<FoodImg>()


    override fun onCreate(savedInstanceState: Bundle?) {
        var total = 5
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        val myLayoutManager = GridLayoutManager(this, 2)
//        binding.mainListRv.layoutManager = myLayoutManager

        for (i in 1 until total) {
            getFoodData(id, secret, "용산구 맛집$i", 5, 1)
            Log.d(TAG, "사이즈 ${food_list_array.size}")
        }


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

    private fun getFoodData(
        clientId: String,
        clientSecret: String,
        search: String,
        display: Int,
        start: Int
    ) {
        //retrofit 인터페이스 가져오기
        val weatherDataInterface =
            FoodListRetrofitClient.sRetrofit.create(FoodListRetrofitInterface::class.java)
        weatherDataInterface.getFoodListData(clientId, clientSecret, search, display, start)
            .enqueue(object :
                Callback<FoodResponse> {
                override fun onResponse(                    // response 가 제대로 불러왔을 때
                    call: Call<FoodResponse>,
                    response: Response<FoodResponse>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body() as FoodResponse

                        for (i in 1 until result.items.size) {
                            var randomInteger = ThreadLocalRandom.current().nextInt(1, 100)
                            var review = randomInteger

                            var randomDouble = ThreadLocalRandom.current().nextDouble(1.0, 5.0)
                            var score = randomDouble
                            score = round(score * 100) / 100

                            var title = result.items[i].title
                            var varFoodList = FoodList(
                                result.items[i].address,
                                "${food_list_array.size + 1}. $title",
                                result.items[i].category,
                                score,
                                "(리뷰 $review)"
                            )
                            food_list_array.add(varFoodList)
                            Log.d(TAG, "추가 ${food_list_array.size}")

                           getFoodImgData(id, secret, "용산 $title", 1, "sim", "all")

                            Log.d(TAG, "사이즈확인 - onResponse() called ${food_img_array.size}")
                        }

                        val adapter = MainFoodListAdapter(food_list_array)
                        binding.mainListRv.layoutManager = GridLayoutManager(this@MainActivity, 2)
                        // binding.mainListRv.layoutManager = LinearLayoutManager(this@MainActivity)
                        binding.mainListRv.adapter = adapter
                        adapter.notifyDataSetChanged()

                        Log.d(TAG, "음식점 데이터 통신성공 - onResponse() called")
                    } else {
                        Log.d(TAG, "음식점 데이터 통신실패 - onResponse() called")
                    }
                }

                override fun onFailure(
                    call: Call<FoodResponse>,
                    t: Throwable
                ) {         // 서버통신 실패했을 때
                    Log.d(TAG, "통신실패 - onFailure() called")
                }

            })
    }

    private fun getFoodImgData(
        clientId: String,
        clientSecret: String,
        query: String,
        display: Int,
        sort: String,
        filter: String
    ) {
        val FoodImgDataInterface =
            FoodImgRetrofitClient.fRetrofit.create(FoodImgRetrofitInterface::class.java)
        FoodImgDataInterface.getFoodImgData(clientId, clientSecret, query, display, sort, filter)
            .enqueue(object :
                Callback<FoodImgResponse> {
                override fun onResponse(
                    call: Call<FoodImgResponse>,
                    response: Response<FoodImgResponse>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body() as FoodImgResponse
  //                      var url = result.item[0].link
//                        food_img_array.add(FoodImg(url))
                        for (i in 0 until display) {
                            Log.d(TAG, "확인용 url - ${result.items.size}")
                            Log.d(TAG, "확인용 url - $result.items[i].link")
                            var url = result.items[i].link
                            food_img_array.add(FoodImg(url))
                        }
                        val adapter = MainFoodImgAdapter(food_img_array)
                        binding.mainListRv.layoutManager = GridLayoutManager(this@MainActivity, 2)
                        // binding.mainListRv.layoutManager = LinearLayoutManager(this@MainActivity)
                        binding.mainListRv.adapter = adapter
                        adapter.notifyDataSetChanged()
                        Log.d(TAG, "이미지 통신성공 - onResponse() called ${food_img_array.size}")

                    } else {
                        Log.d(TAG, " 이미지 통신실패 - onResponse() called ${food_img_array.size}")
                    }
                }

                override fun onFailure(
                    call: Call<FoodImgResponse>,
                    t: Throwable
                ) {         // 서버통신 실패했을 때
                    Log.d(TAG, "통신실패 - onFailure() called")
                }

            })
    }
}



