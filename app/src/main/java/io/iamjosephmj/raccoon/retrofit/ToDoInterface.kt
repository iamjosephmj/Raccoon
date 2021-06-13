package io.iamjosephmj.raccoon.retrofit

import retrofit2.http.GET
import retrofit2.http.Path


interface ToDoInterface {
    @GET("todos/{page}")
    suspend fun listRepos(
        @Path("page") user: Int
    ): retrofit2.Response<Response>

}