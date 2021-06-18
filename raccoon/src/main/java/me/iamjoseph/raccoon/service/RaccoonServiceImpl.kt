package me.iamjoseph.raccoon.service

import me.iamjoseph.raccoon.annotations.Params
import me.iamjoseph.raccoon.annotations.RaccoonEndpoint
import me.iamjoseph.raccoon.controller.RaccoonController
import me.iamjoseph.raccoon.core.stub.RaccoonStub
import me.iamjoseph.raccoon.core.stub.graph.ControllerGraph
import me.iamjoseph.raccoon.core.stub.graph.ServiceGraph
import me.iamjoseph.raccoon.exception.ControllerParameterException
import me.iamjoseph.raccoon.exception.EndpointNotFoundException
import me.iamjoseph.raccoon.exception.ParameterAnnotationNotCorrectException
import me.iamjoseph.raccoon.exception.ParseException
import me.iamjoseph.raccoon.presentation.request.Parameters
import me.iamjoseph.raccoon.presentation.request.RaccoonRequest
import me.iamjoseph.raccoon.presentation.response.RaccoonResponse

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
    override fun fetchController(raccoonRequest: RaccoonRequest): List<RaccoonController> {

        val returnController: MutableList<RaccoonController> = mutableListOf()

        ServiceGraph.serviceObjects[serviceId]
            // Filtering controllers.
            ?.filter { controllerList ->
                var isControllerAvailable = false

                run loop@{
                    if (!isControllerAvailable) {
                        controllerList.forEach { controller ->
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
                                if (!isControllerAvailable) {
                                    isControllerAvailable =
                                        raccoonRequest.endpoint == metaData.endpoint &&
                                                metaData.requestType == raccoonRequest.requestType
                                } else {
                                    return@loop
                                }
                            }
                        }
                    }
                }

                isControllerAvailable
            }
            // Returning controllers.
            ?.forEach { controller ->
                returnController.addAll(controller)
            }

        if (returnController.isNotEmpty()) {
            return returnController
        }

        // Exception thrown for controller not found scenario
        throw EndpointNotFoundException()
    }

    override fun execute(
        raccoonRequest: RaccoonRequest,
        controllers: List<RaccoonController>
    ): RaccoonResponse {

        controllers
            .forEach { controller->
                controller.javaClass.methods
                    // Filter based on annotation.
                    .filter { method ->
                        method.getAnnotation(RaccoonEndpoint::class.java) != null &&
                                method.getAnnotation(RaccoonEndpoint::class.java).endpoint == raccoonRequest.endpoint &&
                                method.getAnnotation(RaccoonEndpoint::class.java).requestType == raccoonRequest.requestType
                    }.forEach { method ->

                        // TODO: room to introduce request related parameters
                        Thread.sleep(method.getAnnotation(RaccoonEndpoint::class.java).latency)

                        val headerIndex =
                            method.parameterAnnotations.indexOfFirst { annotationList ->
                                annotationList.indexOfFirst { annotation ->
                                    annotation is Params
                                } != -1
                            }

                        if (headerIndex != -1 && method.parameterTypes[headerIndex] != Parameters::class.java) {
                            // Raise of there is an object mismatch in the function signature of the Controller.
                            throw ParameterAnnotationNotCorrectException()
                        }
                        // If no parameters are passed except parameters
                        return try {
                            if (method.parameterTypes.count() == 1 && headerIndex == 0) {
                                method.invoke(
                                    controller,
                                    raccoonRequest.parameters
                                ) as RaccoonResponse
                            } else if (method.parameterTypes.count() == 1 && headerIndex == -1) {
                                method.invoke(
                                    controller,
                                    parseRequest(
                                        method.parameterTypes[0],
                                        raccoonRequest.requestBody.toString()
                                    )
                                ) as RaccoonResponse
                            } else if (method.parameterTypes.count() == 2 && headerIndex == 0) {
                                method.invoke(
                                    controller,
                                    raccoonRequest.parameters,
                                    parseRequest(
                                        method.parameterTypes[1],
                                        raccoonRequest.requestBody.toString()
                                    )
                                ) as RaccoonResponse
                            } else if (method.parameterTypes.count() == 2 && headerIndex == 1) {
                                method.invoke(
                                    controller,
                                    parseRequest(
                                        method.parameterTypes[0],
                                        raccoonRequest.requestBody.toString()
                                    ),
                                    raccoonRequest.parameters,
                                ) as RaccoonResponse
                            } else if (headerIndex > 2) {
                                // Location of header not correct exception
                                throw ControllerParameterException()
                            } else {
                                method.invoke(controller) as RaccoonResponse
                            }
                        } catch (ex: Exception) {
                            throw ParseException()
                        }
                    }
            }

        // throws an exception when the endpoint is not found.
        throw EndpointNotFoundException()
    }

    private fun parseRequest(type: Class<*>, data: String): Any {
        return RaccoonStub.raccoonParser.getFromString(data, type)
    }


}