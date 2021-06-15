package io.iamjosephmj.raccoonsample.helper

import io.iamjosephmj.raccoonsample.Constants.BASE_URL
import io.iamjosephmj.raccoon.annotations.Params
import io.iamjosephmj.raccoon.annotations.RaccoonEndpoint
import io.iamjosephmj.raccoon.controller.RaccoonController
import io.iamjosephmj.raccoon.presentation.request.Parameters
import io.iamjosephmj.raccoon.presentation.request.RaccoonRequestType
import io.iamjosephmj.raccoon.presentation.response.RaccoonResponse
import io.iamjosephmj.raccoonsample.retrofit.Response
import io.iamjosephmj.raccoon.util.Utils.buildRaccoonResponse

class MockController : RaccoonController() {

    override fun setup() {
        // Do the DI related stuff
    }

    @RaccoonEndpoint(
        endpoint = "${BASE_URL}todos/1",
        latency = 100,
        RaccoonRequestType.GET
    )
    fun fetchToDoList(@Params headers: Parameters): RaccoonResponse {
        return Response(
            id = 10,
            userId = 1231,
            completed = true,
            title = "mock response"
        ).buildRaccoonResponse(statusCode = 200)
    }


    override fun tearDown() {
        // clean up memory.
    }
}