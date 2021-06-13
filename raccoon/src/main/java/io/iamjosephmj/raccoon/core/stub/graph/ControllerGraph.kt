package io.iamjosephmj.raccoon.core.stub.graph

import io.iamjosephmj.raccoon.controller.ControllerMetaData

object ControllerGraph {

    val endpointPool: MutableMap<String, MutableList<ControllerMetaData>> = mutableMapOf()

}