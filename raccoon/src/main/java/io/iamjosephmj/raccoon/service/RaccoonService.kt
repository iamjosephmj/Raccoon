package io.iamjosephmj.raccoon.service

import io.iamjosephmj.raccoon.presentation.request.RaccoonRequest
import io.iamjosephmj.raccoon.presentation.response.RaccoonResponse

interface RaccoonService {

    val serviceId: String

    fun isAllowed(raccoonRequest: RaccoonRequest): Boolean

    fun execute(raccoonRequest: RaccoonRequest): RaccoonResponse {
        // TODO: process the request
        return RaccoonResponse(
            200,
            "{\n" +
                    "  \"userId\": 10,\n" +
                    "  \"id\": 1,\n" +
                    "  \"title\": \"delectus aut autem\",\n" +
                    "  \"completed\": true\n" +
                    "}"
        )
    }

    fun setup() {
        // TODO: initialize the controller class.
    }

    fun tearDown() {
        // TODO: Teardown the service
    }
}