package com.company_name.wallpaperx.Retrofit

import Photos
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    private val apiKey: String
        get() = "18175286-6ea9f30b33434ad3b0d812729"

    @GET("/api/?key=18175286-6ea9f30b33434ad3b0d812729")
    fun getPhotos() : Call<List<Photos>>

}