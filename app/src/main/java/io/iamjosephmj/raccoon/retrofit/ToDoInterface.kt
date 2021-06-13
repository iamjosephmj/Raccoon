package io.iamjosephmj.raccoon.retrofit

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface ToDoInterface {

    @Headers(*["user-agent: mock"])
    @GET("todos/{page}")
    suspend fun listRepos(
        @Path("page") user: Int,
        @Query("name") username: String
    ): retrofit2.Response<Response>

}