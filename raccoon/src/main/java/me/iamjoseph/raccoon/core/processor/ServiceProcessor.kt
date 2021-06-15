package me.iamjoseph.raccoon.core.processor

import me.iamjoseph.raccoon.annotations.RaccoonController
import me.iamjoseph.raccoon.core.stub.config.RaccoonConfig
import me.iamjoseph.raccoon.core.stub.graph.ServiceGraph
import me.iamjoseph.raccoon.core.stub.interceptor.InterceptorStubImpl
import me.iamjoseph.raccoon.service.RaccoonService
import kotlin.reflect.KClass

/**
 * This Class does the service graph creation.
 *
 * @author Joseph James.
 */
object ServiceProcessor {

    /**
     * Service graph creation.
     *
     * @param raccoonConfig: {@see RaccoonConfig} has the details of the Services that needs to be
     * used.
     * @param serviceSwitch: {@see InterceptorStubImpl} this is a high level implementation of the
     * interface stub.
     */
    fun makeServiceGraph(
        raccoonConfig: RaccoonConfig,
        serviceSwitch: InterceptorStubImpl
    ) {

        raccoonConfig.serviceClasses.forEach { service ->
            // Creating Runtime object of the Service class mentioned in the Config
            // Adds the Service instance to the serviceSwitch object.
            serviceSwitch.addService(createServiceObject(service))
        }

    }

    /**
     * Object creation of the service class
     *
     * @param service {@see RaccoonService} type.
     * @return Returns raccoon service class.
     */
    private fun createServiceObject(service: KClass<out RaccoonService>): RaccoonService {

        // Object creation.
        val serviceInstance = service.java.newInstance()

        // Initial value.
        ServiceGraph.serviceObjects[serviceInstance.serviceId] = mutableListOf()


        serviceInstance.javaClass.methods

            .filter { method ->
                // Filtering based on annotation.
                method.getAnnotation(RaccoonController::class.java) != null
            }

            .forEach { method ->
                @Suppress("UNCHECKED_CAST")
                // Controller object.
                val controller =
                    createControllerObject(
                        method.invoke(serviceInstance)
                                as KClass<me.iamjoseph.raccoon.controller.RaccoonController>
                    )

                // adding controller objects into service.
                ServiceGraph.serviceObjects[serviceInstance.serviceId.split(".")
                    .last()
                ]?.add(
                    controller
                )

                // Controller graph initialization entry point
                ControllerProcessor.makeControllerGraph(serviceInstance, controller)
            }
        return serviceInstance
    }

    /**
     * Controller object creation.
     */
    private fun createControllerObject(
        returnType:
        KClass<me.iamjoseph.raccoon.controller.RaccoonController>
    ):
            me.iamjoseph.raccoon.controller.RaccoonController {
        return returnType.java.newInstance()
                as me.iamjoseph.raccoon.controller.RaccoonController

    }
}