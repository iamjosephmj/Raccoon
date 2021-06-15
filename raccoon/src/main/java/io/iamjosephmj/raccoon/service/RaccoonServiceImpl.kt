package io.iamjosephmj.raccoon.service

import io.iamjosephmj.raccoon.annotations.Params
import io.iamjosephmj.raccoon.annotations.RaccoonEndpoint
import io.iamjosephmj.raccoon.controller.RaccoonController
import io.iamjosephmj.raccoon.core.stub.RaccoonStub
import io.iamjosephmj.raccoon.core.stub.graph.ControllerGraph
import io.iamjosephmj.raccoon.core.stub.graph.ServiceGraph
import io.iamjosephmj.raccoon.exception.ControllerParameterException
import io.iamjosephmj.raccoon.exception.EndpointNotFoundException
import io.iamjosephmj.raccoon.exception.ParameterAnnotationNotCorrectException
import io.iamjosephmj.raccoon.exception.ParseException
import io.iamjosephmj.raccoon.presentation.request.Parameters
import io.iamjosephmj.raccoon.presentation.request.RaccoonRequest
import io.iamjosephmj.raccoon.presentation.response.RaccoonResponse

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
                    if (!isControllerAvailable)
                        isControllerAvailable = raccoonRequest.endpoint == metaData.endpoint &&
                                metaData.requestType == raccoonRequest.requestType
                }
                isControllerAvailable
            }
            // Returning controllers.
            ?.forEach { controller ->
                return controller
            }

        // Exception thrown for controller not found scenario
        throw EndpointNotFoundException()
    }

    override fun execute(
        raccoonRequest: RaccoonRequest,
        controller: RaccoonController
    ): RaccoonResponse {

        controller.javaClass.methods
            // Filter based on annotation.
            .filter { method ->
                method.getAnnotation(RaccoonEndpoint::class.java) != null &&
                        method.getAnnotation(RaccoonEndpoint::class.java).endpoint == raccoonRequest.endpoint &&
                        method.getAnnotation(RaccoonEndpoint::class.java).requestType == raccoonRequest.requestType
            }.forEach { method ->

                // TODO: room to introduce request related parameters
                Thread.sleep(method.getAnnotation(RaccoonEndpoint::class.java).latency)

                val headerIndex = method.parameterAnnotations.indexOfFirst { annotationList ->
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
        // throws an exception when the endpoint is not found.
        throw EndpointNotFoundException()
    }

    private fun parseRequest(type: Class<*>, data: String): Any {
        return RaccoonStub.raccoonParser.getFromString(data, type)
    }


}