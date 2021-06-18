package me.iamjoseph.raccoon.core.processor

import me.iamjoseph.raccoon.controller.ControllerMetaData
import me.iamjoseph.raccoon.controller.RaccoonController
import me.iamjoseph.raccoon.core.stub.graph.ControllerGraph
import me.iamjoseph.raccoon.annotations.RaccoonEndpoint
import me.iamjoseph.raccoon.service.RaccoonService

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
     * @param controllers raccoonControl instance.
     */
    fun makeControllerGraph(
        serviceInstance: RaccoonService,
        controllers: List<RaccoonController>
    ) {


        controllers.forEach {
            // Endpoint graph-lookup initialization.
            @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
            ControllerGraph.endpointPool[
                    "${
                        serviceInstance.serviceId
                            .split(".")
                            .last()
                    }-${
                        it.javaClass.canonicalName.split(".")
                            .last()
                    }"
                    // Assigning empty list
            ] = mutableListOf()
        }

        controllers.forEach { controller->

            controller.javaClass.methods
                .filter { method ->
                    // Filtering based on annotation.
                    method.getAnnotation(RaccoonEndpoint::class.java) != null
                }.map { method ->
                    val details =
                        method.getAnnotation(RaccoonEndpoint::class.java)
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
}