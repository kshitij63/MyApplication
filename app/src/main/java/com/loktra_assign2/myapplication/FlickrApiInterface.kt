package com.loktra_assign2.myapplication

import retrofit2.Call
import retrofit2.http.*

/**
 * Created by user on 4/30/2018.
 */
interface FlickrApiInterface {

    @GET("rest/")
    fun getAllPhotosData(@Query("method") method: String,
                         @Query("nojsoncallback") nojsoncallback: String,
                         @Query("format") format: String
                         , @Query("tags") tags: String
                         , @Query("api_key") api_key: String): Call<FlickrObject>
}