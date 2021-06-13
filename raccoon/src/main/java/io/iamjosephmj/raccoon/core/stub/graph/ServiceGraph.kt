package io.iamjosephmj.raccoon.core.stub.graph

import io.iamjosephmj.raccoon.controller.RaccoonController


/**
 * Service graph bluePrint
 *
 * @author Joseph James
 */
//TODO:Need to make this into graph structure down the line.
internal object ServiceGraph {

    val serviceObjects: MutableMap<
            String, MutableList<RaccoonController>
            > = mutableMapOf()


    fun cleanUp() {
        serviceObjects.clear()
    }
}