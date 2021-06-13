package io.iamjosephmj.raccoon.core.stub

import io.iamjosephmj.raccoon.core.processor.ServiceProcessor
import io.iamjosephmj.raccoon.core.stub.config.RaccoonConfig
import io.iamjosephmj.raccoon.core.stub.interceptor.InterceptorStubImpl
import io.iamjosephmj.raccoon.core.stub.switch.ServiceSwitch

object RaccoonStub {

    val stub by lazy {
        InterceptorStubImpl(ServiceSwitch())
    }

    fun init(raccoonConfig: RaccoonConfig) {
        ServiceProcessor.makeServiceGraph(raccoonConfig, stub)
    }

    fun getServiceSwitch(): InterceptorStubImpl {
        return stub
    }

}