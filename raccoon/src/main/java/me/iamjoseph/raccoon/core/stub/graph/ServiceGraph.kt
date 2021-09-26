package me.iamjoseph.raccoon.core.stub.graph

import me.iamjoseph.raccoon.controller.RaccoonController


/**
 * Service graph bluePrint
 *
 * @author Joseph James
 */
//TODO:Need to make this into graph structure down the line.
class ServiceGraph {

    val serviceObjects: MutableMap<
            String, MutableList<List<RaccoonController>>
            > = mutableMapOf()


    fun cleanUp() {
        serviceObjects.clear()
    }
}