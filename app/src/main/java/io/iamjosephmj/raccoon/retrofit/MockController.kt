package io.iamjosephmj.raccoon.retrofit

import io.iamjosephmj.raccoon.annotations.RaccoonEndpoint
import io.iamjosephmj.raccoon.controller.RaccoonController
import io.iamjosephmj.raccoon.presentation.request.RaccoonRequestType

class MockController : RaccoonController() {

    @RaccoonEndpoint(
        endpoint = "https://jsonplaceholder.typicode.com/todos/1",
        latency = 1000,
        RaccoonRequestType.GET
    )
    fun fetchToDoList() {

    }
}