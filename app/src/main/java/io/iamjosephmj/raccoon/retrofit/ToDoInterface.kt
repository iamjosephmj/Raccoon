package io.iamjosephmj.raccoon.retrofit

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


interface ToDoInterface {

    @Headers(*["user-agent: mock"])
    @GET("todos/{page}")
    suspend fun listRepos(
        @Path("page") user: Int
    ): retrofit2.Response<Response>

}