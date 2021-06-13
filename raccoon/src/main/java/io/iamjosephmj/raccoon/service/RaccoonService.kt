package io.iamjosephmj.raccoon.service

import io.iamjosephmj.raccoon.controller.RaccoonController
import io.iamjosephmj.raccoon.presentation.request.RaccoonRequest
import io.iamjosephmj.raccoon.presentation.response.RaccoonResponse

/**
 * Raccoon Service base interface.
 *
 * @author Joseph James.
 */
interface RaccoonService {

    // ID of the specific service.
    val serviceId: String

    // getting the controller from the request.
    fun fetchController(raccoonRequest: RaccoonRequest): RaccoonController

    // Request-Response handshake.
    fun execute(raccoonRequest: RaccoonRequest, controller: RaccoonController): RaccoonResponse

    //Controller setup
    fun setup(controller: RaccoonController) {
        controller.setup()
    }

    // Controller cleanup
    fun tearDown(controller: RaccoonController) {
        controller.tearDown()
    }
}