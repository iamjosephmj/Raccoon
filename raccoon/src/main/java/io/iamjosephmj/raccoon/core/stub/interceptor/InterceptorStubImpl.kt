package io.iamjosephmj.raccoon.core.stub.interceptor

import io.iamjosephmj.raccoon.core.stub.switch.ServiceSwitch
import io.iamjosephmj.raccoon.presentation.request.RaccoonRequest
import io.iamjosephmj.raccoon.service.RaccoonService

class InterceptorStubImpl(private val switch: ServiceSwitch) : InterceptorStub {

    override fun execute(raccoonRequest: RaccoonRequest) = switch.executeRequest(raccoonRequest)

    override fun addService(raccoonService: RaccoonService) {
        switch.addService(raccoonService = raccoonService)
    }

}