package io.iamjosephmj.raccoon.service

import io.iamjosephmj.raccoon.core.stub.graph.ControllerGraph
import io.iamjosephmj.raccoon.core.stub.graph.ServiceGraph
import io.iamjosephmj.raccoon.presentation.request.RaccoonRequest

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
open class RaccoonServiceImpl : RaccoonService {

    override val serviceId: String = javaClass.canonicalName.toString().split(".")
        .last()


    override fun isAllowed(raccoonRequest: RaccoonRequest): Boolean {

        // loop through controller.
        // TODO: raise an exception if not available.
        ServiceGraph.serviceObjects[serviceId]?.forEach { controller ->
            ControllerGraph.endpointPool[
                    "${
                        serviceId.split(".")
                            .last()
                    }-${
                        controller.javaClass.canonicalName.split(".")
                            .last()
                    }"
            ]?.forEach { metadata ->
                if (raccoonRequest.endpoint in metadata.endpoint) {
                    return true
                }
            }
        }

        return false
    }


}