package me.iamjoseph.raccoonsample.includetest.helpers

import me.iamjoseph.raccoon.annotations.ControllerModule
import me.iamjoseph.raccoon.annotations.Params
import me.iamjoseph.raccoon.annotations.RaccoonEndpoint
import me.iamjoseph.raccoon.controller.RaccoonController
import me.iamjoseph.raccoon.presentation.request.Parameters
import me.iamjoseph.raccoon.presentation.request.RaccoonRequestType
import me.iamjoseph.raccoon.presentation.response.RaccoonResponse
import me.iamjoseph.raccoon.util.Utils.buildRaccoonResponse
import me.iamjoseph.raccoonsample.Constants
import me.iamjoseph.raccoonsample.retrofit.Response

@ControllerModule
class MockController2 : RaccoonController() {

    override fun setup() {

    }

    @RaccoonEndpoint(
        endpoint = "${Constants.BASE_URL}todos/1",
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

    }
}