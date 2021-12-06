package com.sokah.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val moshi =Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val rickAppCompactActivity: RickAndMortyService = retrofit.create(RickAndMortyService::class.java)

        rickAppCompactActivity.getCharacterById(5).enqueue( object : Callback<GetCharacterByIdResponse>{
            override fun onResponse(call: Call<GetCharacterByIdResponse>, response: Response<GetCharacterByIdResponse>) {
                Log.i("MainAcitivity", response.toString())
            }

            override fun onFailure(call: Call<GetCharacterByIdResponse>, t: Throwable) {
                Log.i("MainAcitivity", t.message?:"Null message")
            }


        })
    }
}