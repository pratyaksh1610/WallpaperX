package com.company_name.wallpaperx.Retrofit

import com.company_name.wallpaperx.DataClass.Photo
import com.company_name.wallpaperx.DataClass.PhotoByQuery
import com.company_name.wallpaperx.DataClass.urls
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    //all queries using the base url

    @GET("/photos/?per_page=100&client_id=3IgfYBH3YGGV2ogmkJMNrPmeWKkaujkW1fiEqIOmiKw")
    fun getPhotos(): Call<List<Photo>>

    @GET("/search/photos?&per_page=100&client_id=3IgfYBH3YGGV2ogmkJMNrPmeWKkaujkW1fiEqIOmiKw")
    fun getCat(@Query("query") query: String): Call<PhotoByQuery>
    //do not include that parameter in GET url simply pass the query with the name
    //You don't need to include query parameter in your GET Annotation

    @GET("/photos/?per_page=100&client_id=3IgfYBH3YGGV2ogmkJMNrPmeWKkaujkW1fiEqIOmiKw")
    fun getPhotosBestOfMonth(
        @Query("order_by") order_by: String = "latest",
        @Query("orientation") orientation: String = "portrait"
    ): Call<List<Photo>>


}