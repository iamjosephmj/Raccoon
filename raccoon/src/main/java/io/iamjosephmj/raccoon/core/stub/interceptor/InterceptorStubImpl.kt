package io.iamjosephmj.raccoon.core.stub.interceptor

import io.iamjosephmj.raccoon.core.stub.switch.ServiceSwitch
import io.iamjosephmj.raccoon.presentation.request.RaccoonRequest

class InterceptorStubImpl(private val switch: ServiceSwitch) : InterceptorStub {

    override fun preRequestNotification(raccoonRequest: RaccoonRequest) {
        switch.initializeService(raccoonRequest)
    }

    override fun request(raccoonRequest: RaccoonRequest) = switch.executeRequest(raccoonRequest)


    override fun postRequestNotification(raccoonRequest: RaccoonRequest) {
        switch.tearDownService(raccoonRequest)
    }

}