package com.example.week5_practice

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.week5_practice.databinding.ActivityLoginBinding
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.internal.Utility
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import java.security.MessageDigest
import java.util.*


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    val callbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        FacebookSdk.sdkInitialize(getApplicationContext())
        AppEventsLogger.activateApp(this)

        binding.loginSkip.setOnClickListener {
            Log.d(TAG, "LoginActivity - onCreate() called")
         val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.loginFacebookIb.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))
            LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {

                    }

                    override fun onCancel() {

                    }

                    override fun onError(error: FacebookException?) {
                    }
                })
            }

        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    var accessToken = loginResult.accessToken
                    requestMe(accessToken)
                }

                override fun onCancel() {

                }

                override fun onError(error: FacebookException?) {
                }

            })

        val keyHash = "lvGC0B4SWYU8tNPHg/bdMjQinZQ="
        Log.d("Hash", keyHash)

        /*
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                Log.e(TAG, "로그인 실패", error)
            }
            else if (token != null) {
                Log.i(TAG, "로그인 성공 ${token.accessToken}")
            }
        } */
    }

    fun requestMe(accessToken: AccessToken) {
        val request = GraphRequest.newMeRequest(accessToken) { `object`, response ->
            try {
                //here is the data that you want

                var userEmail = `object`.getString("email")
                Log.e("TAGG", userEmail)
                var userName = `object`.getString("name")
                Log.e("TAGG", userName)
                var jobj1 = `object`.optJSONObject("picture")
                Log.e("TAGG", jobj1.toString())
                var jobj2 = jobj1.optJSONObject("data")
                Log.e("TAGG", jobj2.toString())
                var userPicture = jobj2.getString("url")
                Log.e("TAGG", userPicture)

            } catch (e: Exception) {
                e.printStackTrace()
            }
            goTomain()
        }
        val parameters = Bundle()
        parameters.putString("fields", "name,email,picture")
        request.parameters = parameters
        request.executeAsync()

    }

    private fun goTomain() {
        TODO("Not yet implemented")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }
    /*
    UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
        if (error != null) {
            Log.e(TAG, "로그인 실패", error)
        }
        else if (token != null) {
            Log.i(TAG, "로그인 성공 ${token.accessToken}")
        }
    } */

}