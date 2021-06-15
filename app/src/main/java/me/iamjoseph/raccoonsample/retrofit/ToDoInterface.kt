package me.iamjoseph.raccoonsample.retrofit

import me.iamjoseph.raccoonsample.Constants
import me.iamjoseph.raccoonsample.Constants.ENDPOINT_TODO_KEY_NAME
import me.iamjoseph.raccoonsample.Constants.ENDPOINT_TODO_KEY_PAGE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface ToDoInterface {

    @Headers("user-agent: mock")
    @GET(Constants.ENDPOINT_TODO)
    suspend fun listRepos(
        @Path(ENDPOINT_TODO_KEY_PAGE) user: Int,
        @Query(ENDPOINT_TODO_KEY_NAME) userType: String
    ): retrofit2.Response<Response>

}