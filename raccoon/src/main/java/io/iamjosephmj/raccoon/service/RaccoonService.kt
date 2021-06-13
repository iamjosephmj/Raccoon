package io.iamjosephmj.raccoon.service

import io.iamjosephmj.raccoon.controller.RaccoonController
import io.iamjosephmj.raccoon.presentation.request.RaccoonRequest
import io.iamjosephmj.raccoon.presentation.response.RaccoonResponse

interface RaccoonService {

    val serviceId: String

    fun fetchController(raccoonRequest: RaccoonRequest): RaccoonController

    fun execute(raccoonRequest: RaccoonRequest, controller: RaccoonController): RaccoonResponse

    fun setup(controller: RaccoonController) {
        controller.setup()
    }

    fun tearDown(controller: RaccoonController) {
        controller.tearDown()
    }
}