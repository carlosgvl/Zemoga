package com.carlosvivas.zemoga.network

import com.carlosvivas.zemoga.models.Comments
import com.carlosvivas.zemoga.models.Post
import com.carlosvivas.zemoga.models.User
import com.carlosvivas.zemoga.util.Constants
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiInterface {

    @GET("posts")
    fun getPostList(): Observable<List<Post>>

    @GET("posts/{id}")
    fun getPost(@Path("id") id: Int): Observable<Post>

    @GET("comments")
    fun getCommentByPostId(@Query("postId") postId: Int): Observable<List<Comments>>

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Observable<User>


    companion object Factory {
        fun create(): ApiInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}