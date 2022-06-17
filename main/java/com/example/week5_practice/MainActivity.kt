package com.example.week5_practice

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week5_practice.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var id = "vptSfobFaKbqhGD92XW2"
    private var secret = "uZ3rimjFFL"
    private val NUM_PAGES = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getFoodData(id, secret, "용산구 맛집", 20, 1)

        val myLayoutManager = GridLayoutManager(this, 2)
        binding.mainListRv.layoutManager = myLayoutManager

        getFoodData(binding.editText.text.toString(), APP_KEY)


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
        weatherDataInterface.getWeatherData(clientId, clientSecret, search, display, start).enqueue(object :retrofit2.Callback<FoodResponse>{
            override fun onResponse(                    // response 가 제대로 불러왔을 때
                call: Call<FoodResponse>,
                response: Response<FoodResponse>
            ) {
                if(response.isSuccessful) {
                    val result = response.body() as FoodResponse
                    for (i in 1 until result.items.size) {
                        binding.mainListRv.
                    }
                    binding.textView.text = result.main.temp.toString()+"도"
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