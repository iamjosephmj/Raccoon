package me.iamjoseph.raccoon.core.stub.interceptor

import me.iamjoseph.raccoon.core.stub.switch.ServiceSwitch
import me.iamjoseph.raccoon.presentation.request.RaccoonRequest
import me.iamjoseph.raccoon.service.RaccoonService

class InterceptorStubImpl(private val switch: ServiceSwitch) : InterceptorStub {

    override fun execute(raccoonRequest: RaccoonRequest) = switch.executeRequest(raccoonRequest)

    override fun addService(raccoonService: RaccoonService) {
        switch.addService(raccoonService = raccoonService)
    }

}