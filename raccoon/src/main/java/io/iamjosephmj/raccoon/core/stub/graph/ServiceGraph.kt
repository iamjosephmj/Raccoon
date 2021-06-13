package io.iamjosephmj.raccoon.core.stub.graph

import io.iamjosephmj.raccoon.controller.RaccoonController

internal object ServiceGraph {
    val serviceObjects: MutableMap<
            String, MutableList<RaccoonController>
            > = mutableMapOf()
}