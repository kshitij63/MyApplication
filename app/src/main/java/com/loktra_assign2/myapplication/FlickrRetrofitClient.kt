package com.loktra_assign2.myapplication

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by user on 4/30/2018.
 */
class FlickrRetrofitClient {

    companion object {
        var retrofit: Retrofit? = null

        fun getRetrofitClient(): Retrofit {

            if (retrofit == null) {
                var logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY

                var httpClient = OkHttpClient.Builder()
                httpClient.addInterceptor(logging)

//                val gson = GsonBuilder().setLenient().create()
                retrofit = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("https://api.flickr.com/services/")
                        .client(httpClient.build())
                        .build()
            }

            return retrofit!!
        }
    }
}