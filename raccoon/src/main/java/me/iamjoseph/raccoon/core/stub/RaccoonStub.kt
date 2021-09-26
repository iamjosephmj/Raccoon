package me.iamjoseph.raccoon.core.stub

import me.iamjoseph.raccoon.core.processor.ServiceProcessor
import me.iamjoseph.raccoon.core.stub.config.RaccoonConfig
import me.iamjoseph.raccoon.core.stub.graph.ControllerGraph
import me.iamjoseph.raccoon.core.stub.graph.ServiceGraph
import me.iamjoseph.raccoon.core.stub.interceptor.InterceptorStubImpl
import me.iamjoseph.raccoon.core.stub.switch.ServiceSwitch
import me.iamjoseph.raccoon.parser.GsonPlugin
import me.iamjoseph.raccoon.parser.RaccoonParser

/**
 * This is the Entry-point for the Raccoon interceptor plugin.
 *
 * @author Joseph James.
 */
class RaccoonStub {

    val stub by lazy {
        InterceptorStubImpl(ServiceSwitch())
    }

    val serviceGraph by lazy {
        ServiceGraph()
    }

    val controllerGraph by lazy {
        ControllerGraph()
    }

    var raccoonParser: RaccoonParser = GsonPlugin()

    /**
     * Setup the Endpoint graph based on the raccoon configuration.
     */
    fun setUp(raccoonConfig: RaccoonConfig): RaccoonStub {
        raccoonParser = raccoonConfig.raccoonParserPlugin
        ServiceProcessor().makeServiceGraph(raccoonConfig, stub, this)
        return this
    }

    fun getServiceSwitch(): InterceptorStubImpl {
        return stub
    }

    /**
     * Memory cleanup
     */
    fun tearDown() {
        controllerGraph.cleanUp()
        serviceGraph.cleanUp()
    }

}