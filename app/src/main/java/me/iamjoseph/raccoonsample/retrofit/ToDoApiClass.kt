package me.iamjoseph.raccoonsample.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import me.iamjoseph.raccoonsample.Constants
import me.iamjoseph.raccoon.presentation.plugins.RaccoonOkHttpPlugin
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
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
        retrofit
            .create(ToDoInterface::class.java)
    }

    suspend fun getPage(pageNumber: Int): Response? {
        return service.listRepos(
            pageNumber,
            Constants.USER_TYPE
        ).body()

    }
}