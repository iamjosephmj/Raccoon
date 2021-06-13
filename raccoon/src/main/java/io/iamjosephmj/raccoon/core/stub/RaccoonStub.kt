package io.iamjosephmj.raccoon.core.stub

import io.iamjosephmj.raccoon.core.processor.ServiceProcessor
import io.iamjosephmj.raccoon.core.stub.config.RaccoonConfig
import io.iamjosephmj.raccoon.core.stub.graph.ControllerGraph
import io.iamjosephmj.raccoon.core.stub.graph.ServiceGraph
import io.iamjosephmj.raccoon.core.stub.interceptor.InterceptorStubImpl
import io.iamjosephmj.raccoon.core.stub.switch.ServiceSwitch

/**
 * This is the Entry-point for the Raccoon interceptor plugin.
 *
 * @author Joseph James.
 */
object RaccoonStub {

    val stub by lazy {
        InterceptorStubImpl(ServiceSwitch())
    }

    /**
     * Setup the Endpoint graph based on the raccoon configuration.
     */
    fun setUp(raccoonConfig: RaccoonConfig) {
        ServiceProcessor.makeServiceGraph(raccoonConfig, stub)
    }

    fun getServiceSwitch(): InterceptorStubImpl {
        return stub
    }

    /**
     * Memory cleanup
     */
    fun teatDown() {
        ControllerGraph.cleanUp()
        ServiceGraph.cleanUp()
    }

}