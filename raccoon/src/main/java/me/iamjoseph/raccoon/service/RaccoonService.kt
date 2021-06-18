package me.iamjoseph.raccoon.service

import me.iamjoseph.raccoon.controller.RaccoonController
import me.iamjoseph.raccoon.presentation.request.RaccoonRequest
import me.iamjoseph.raccoon.presentation.response.RaccoonResponse

/**
 * Raccoon Service base interface.
 *
 * @author Joseph James.
 */
interface RaccoonService {

    // ID of the specific service.
    val serviceId: String

    // getting the controller from the request.
    fun fetchController(raccoonRequest: RaccoonRequest): List<RaccoonController>

    // Request-Response handshake.
    fun execute(raccoonRequest: RaccoonRequest, controller: List<RaccoonController>): RaccoonResponse

    //Controller setup
    fun setup(controller: List<RaccoonController>) {
        controller.forEach {
            it.setup()
        }
    }

    // Controller cleanup
    fun tearDown(controller: List<RaccoonController>) {
        controller.forEach {
            it.tearDown()
        }
    }
}