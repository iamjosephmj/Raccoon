package io.iamjosephmj.raccoon.core.processor

import io.iamjosephmj.raccoon.controller.ControllerMetaData
import io.iamjosephmj.raccoon.controller.RaccoonController
import io.iamjosephmj.raccoon.core.stub.graph.ControllerGraph
import io.iamjosephmj.raccoon.service.RaccoonService

/**
 * This Class does the controller graph creation.
 *
 * @author Joseph James.
 */
internal object ControllerProcessor {

    /**
     * Controller Graph Creation.
     *
     * @param serviceInstance instance object of Raccoon Service class.
     * @param controller raccoonControl instance.
     */
    fun makeControllerGraph(
        serviceInstance: RaccoonService,
        controller: RaccoonController
    ) {

        // Endpoint graph-lookup initialization.
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
                // Assigning empty list
        ] = mutableListOf()

        controller.javaClass.methods
            .filter { method ->
                // Filtering based on annotation.
                method.getAnnotation(io.iamjosephmj.raccoon.annotations.RaccoonEndpoint::class.java) != null
            }.map { method ->
                val details =
                    method.getAnnotation(io.iamjosephmj.raccoon.annotations.RaccoonEndpoint::class.java)
                // Creating method-Controller metadata pair.
                Pair(
                    method, ControllerMetaData(
                        endpoint = details.endpoint,
                        latency = details.latency,
                        requestType = details.requestType
                    )
                )
            }
            .forEach { pair ->
                // Storing the pair in the endpoint pool.
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