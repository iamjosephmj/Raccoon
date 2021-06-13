package io.iamjosephmj.raccoon.core.processor

import io.iamjosephmj.raccoon.controller.ControllerMetaData
import io.iamjosephmj.raccoon.controller.RaccoonController
import io.iamjosephmj.raccoon.core.stub.graph.ControllerGraph
import io.iamjosephmj.raccoon.service.RaccoonService

internal object ControllerProcessor {

    fun makeControllerGraph(
        serviceInstance: RaccoonService,
        controller: RaccoonController
    ) {

        @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        ControllerGraph.endpointPool[
                "${
                    serviceInstance.serviceId
                        .split(".")
                        .last()
                }-${
                    controller.javaClass.canonicalName.split(".")
                        .last()
                }"
        ] = mutableListOf()

        controller.javaClass.methods
            .filter { method ->
                method.getAnnotation(io.iamjosephmj.raccoon.annotations.RaccoonEndpoint::class.java) != null
            }.map { method ->
                val details =
                    method.getAnnotation(io.iamjosephmj.raccoon.annotations.RaccoonEndpoint::class.java)
                Pair(
                    method, ControllerMetaData(
                        endpoint = details.endpoint,
                        latency = details.latency,
                        requestType = details.requestType
                    )
                )
            }
            .forEach { pair ->
                @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
                ControllerGraph.endpointPool[
                        "${
                            serviceInstance.serviceId.split(".")
                                .last()
                        }-${
                            controller.javaClass.canonicalName.split(".")
                                .last()
                        }"
                ]?.add(
                    pair.second
                )
            }


    }
}