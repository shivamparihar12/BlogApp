package com.example.blogapp.data.remote

import com.example.blogapp.data.model.BlogPost
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface BlogService {
    @GET("posts")
    suspend fun getPosts(@Query("per_page") perPage: Int, @Query("page") page: Int): List<BlogPost>
}

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://blog.vrid.in/wp-json/wp/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: BlogService by lazy {
        retrofit.create(BlogService::class.java)
    }
}