package io.iamjosephmj.raccoon.helper

import io.iamjosephmj.raccoon.annotations.RaccoonEndpoint
import io.iamjosephmj.raccoon.controller.RaccoonController
import io.iamjosephmj.raccoon.presentation.request.RaccoonRequestType
import io.iamjosephmj.raccoon.presentation.response.RaccoonResponse
import io.iamjosephmj.raccoon.retrofit.Response
import io.iamjosephmj.raccoon.util.GsonUtils.buildRaccoonResponse

class MockController : RaccoonController() {

    @RaccoonEndpoint(
        endpoint = "https://jsonplaceholder.typicode.com/todos/1",
        latency = 1000,
        RaccoonRequestType.GET
    )
    fun fetchToDoList(): RaccoonResponse {
        return Response(
            id = 10,
            userId = 1231,
            completed = true,
            title = "278973"
        ).buildRaccoonResponse(statusCode = 200)
    }
}