package io.iamjosephmj.raccoon.core.stub.graph

import io.iamjosephmj.raccoon.controller.ControllerMetaData

/**
 * Controller graph bluePrint
 *
 * @author Joseph James
 */
//TODO:Need to make this into graph structure down the line.
internal object ControllerGraph {

    val endpointPool: MutableMap<String, MutableList<ControllerMetaData>> = mutableMapOf()

    fun cleanUp() {
        endpointPool.clear()
    }
}