package me.iamjoseph.raccoon.core.stub.graph

import me.iamjoseph.raccoon.controller.ControllerMetaData

/**
 * Controller graph bluePrint
 *
 * @author Joseph James
 */
//TODO:Need to make this into graph structure down the line.
class ControllerGraph {

    val endpointPool: MutableMap<String, MutableList<ControllerMetaData>> = mutableMapOf()

    fun cleanUp() {
        endpointPool.clear()
    }
}