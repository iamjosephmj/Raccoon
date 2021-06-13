package io.iamjosephmj.raccoon.service

import io.iamjosephmj.raccoon.annotations.RaccoonEndpoint
import io.iamjosephmj.raccoon.controller.RaccoonController
import io.iamjosephmj.raccoon.core.stub.graph.ControllerGraph
import io.iamjosephmj.raccoon.core.stub.graph.ServiceGraph
import io.iamjosephmj.raccoon.exception.ControllerNotFoundException
import io.iamjosephmj.raccoon.exception.EndpointNotFoundException
import io.iamjosephmj.raccoon.presentation.request.RaccoonRequest
import io.iamjosephmj.raccoon.presentation.response.RaccoonResponse
import io.iamjosephmj.raccoon.util.GsonUtils

/**
 * This class is build for managing the {@see RaccoonService}
 *
 * @author Joseph James.
 */
open class RaccoonServiceImpl : RaccoonService {

    // Service name initialization.
    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override val serviceId: String = javaClass.canonicalName.toString().split(".")
        .last()

    /**
     * This method fetches the controller object from the ServiceGraph class.
     */
    override fun fetchController(raccoonRequest: RaccoonRequest): RaccoonController {
        ServiceGraph.serviceObjects[serviceId]
            // Filtering controllers.
            ?.filter { controller ->
                var isControllerAvailable = false
                @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
                ControllerGraph.endpointPool[
                        "${
                            serviceId.split(".")
                                .last()
                        }-${
                            controller.javaClass.canonicalName.split(".")
                                .last()
                        }"
                ]?.forEach { metaData ->
                    isControllerAvailable = raccoonRequest.endpoint == metaData.endpoint &&
                            metaData.requestType == raccoonRequest.requestType
                }
                isControllerAvailable
            }
            // Returning controllers.
            ?.forEach { controller ->
                return controller
            }

        // Exception thrown for controller not found scenerio
        throw ControllerNotFoundException()
    }

    override fun execute(
        raccoonRequest: RaccoonRequest,
        controller: RaccoonController
    ): RaccoonResponse {

        controller.javaClass.methods
            // Filter based on annotation.
            .filter { method ->
                method.getAnnotation(RaccoonEndpoint::class.java) != null
            }.forEach { method ->

                // TODO: room to introduce request related parameters
                Thread.sleep(method.getAnnotation(RaccoonEndpoint::class.java).latency)

                return if (method.parameterTypes.isNotEmpty()) {
                    method.invoke(
                        controller,
                        parseRequest(
                            method.parameterTypes[0],
                            raccoonRequest.requestBody.toString()
                        )
                    ) as RaccoonResponse
                } else {
                    method.invoke(controller) as RaccoonResponse
                }
            }
        // throws an exception when the endpoint is not found.
        throw EndpointNotFoundException()
    }

    private fun parseRequest(type: Class<*>, data: String): Any {
        return GsonUtils.gson.fromJson(data, type)
    }


}