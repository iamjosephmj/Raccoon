package io.iamjosephmj.raccoon.core.stub.switch

import io.iamjosephmj.raccoon.exception.UrlNotFoundException
import io.iamjosephmj.raccoon.presentation.request.RaccoonRequest
import io.iamjosephmj.raccoon.presentation.response.RaccoonResponse
import io.iamjosephmj.raccoon.service.RaccoonService

class ServiceSwitch {

    private val services: MutableList<RaccoonService> = mutableListOf()

    fun executeRequest(raccoonRequest: RaccoonRequest): RaccoonResponse {
        services.forEach { service ->
            if (service.isAllowed(raccoonRequest)) {
                return service.execute(raccoonRequest)
            }
        }

        throw UrlNotFoundException()
    }

    fun addService(raccoonService: RaccoonService) {
        services.add(raccoonService)
    }

    fun initializeService(raccoonRequest: RaccoonRequest) {
        services.forEach { service ->
            if (service.isAllowed(raccoonRequest)) {
                return service.setup()
            }
        }

        throw UrlNotFoundException()
    }

    fun tearDownService(raccoonRequest: RaccoonRequest) {
        services.forEach { service ->
            if (service.isAllowed(raccoonRequest)) {
                return service.tearDown()
            }
        }

        throw UrlNotFoundException()
    }

}