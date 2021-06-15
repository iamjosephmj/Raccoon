package me.iamjoseph.raccoon.core.stub.switch

import me.iamjoseph.raccoon.exception.UrlNotFoundException
import me.iamjoseph.raccoon.presentation.request.RaccoonRequest
import me.iamjoseph.raccoon.presentation.response.RaccoonResponse
import me.iamjoseph.raccoon.service.RaccoonService

/**
 * This class is responsible for executing the request from interceptor.
 *
 * @author Joseph James.
 */
class ServiceSwitch {

    private val services: MutableList<RaccoonService> = mutableListOf()

    fun executeRequest(raccoonRequest: RaccoonRequest): RaccoonResponse {
        services.forEach { service ->
            val controller = service.fetchController(raccoonRequest)
            service.setup(controller)
            val result = service.execute(raccoonRequest, controller)
            service.tearDown(controller)
            return result
        }

        throw UrlNotFoundException()
    }

    fun addService(raccoonService: RaccoonService) {
        services.add(raccoonService)
    }
}