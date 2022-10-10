package com.company_name.wallpaperx.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    private val baseUrl = "https://api.unsplash.com/"

    fun initialiseRetrofitBuilderObject(): ApiInterface {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
}
