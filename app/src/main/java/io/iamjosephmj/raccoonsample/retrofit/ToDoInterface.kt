package io.iamjosephmj.raccoonsample.retrofit

import io.iamjosephmj.raccoonsample.Constants
import io.iamjosephmj.raccoonsample.Constants.ENDPOINT_TODO_KEY_NAME
import io.iamjosephmj.raccoonsample.Constants.ENDPOINT_TODO_KEY_PAGE
import io.iamjosephmj.raccoonsample.retrofit.Response
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