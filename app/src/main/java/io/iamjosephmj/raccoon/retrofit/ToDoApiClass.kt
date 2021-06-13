package io.iamjosephmj.raccoon.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.iamjosephmj.raccoon.presentation.plugins.RaccoonOkHttpPlugin
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ToDoApiClass {
    private val service: ToDoInterface by lazy {

        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(RaccoonOkHttpPlugin())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
        retrofit
            .create(ToDoInterface::class.java)
    }

    suspend fun getPage(pageNumber: Int): Response? {
        return service.listRepos(pageNumber).body()

    }
}