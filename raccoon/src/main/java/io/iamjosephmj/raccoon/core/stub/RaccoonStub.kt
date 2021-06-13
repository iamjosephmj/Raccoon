package io.iamjosephmj.raccoon.core.stub

import io.iamjosephmj.raccoon.core.processor.ServiceProcessor
import io.iamjosephmj.raccoon.core.stub.config.RaccoonConfig
import io.iamjosephmj.raccoon.core.stub.switch.ServiceSwitch

object RaccoonStub {
    private val serviceSwitch: ServiceSwitch = ServiceSwitch()

    fun init(raccoonConfig: RaccoonConfig) {
        ServiceProcessor.makeServiceGraph(raccoonConfig, serviceSwitch)
    }

    fun getServiceSwitch(): ServiceSwitch {
        return serviceSwitch
    }

}