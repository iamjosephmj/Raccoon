package io.iamjosephmj.raccoon.core.stub.switch

import io.iamjosephmj.raccoon.exception.UrlNotFoundException
import io.iamjosephmj.raccoon.presentation.request.RaccoonRequest
import io.iamjosephmj.raccoon.presentation.response.RaccoonResponse
import io.iamjosephmj.raccoon.service.RaccoonService

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