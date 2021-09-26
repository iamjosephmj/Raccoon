package me.iamjoseph.raccoon.core.processor

import me.iamjoseph.raccoon.annotations.ControllerModule
import me.iamjoseph.raccoon.annotations.RaccoonController
import me.iamjoseph.raccoon.core.stub.RaccoonStub
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
class ServiceProcessor {

    lateinit var controllerProcessor:ControllerProcessor

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
        serviceSwitch: InterceptorStubImpl,
        raccoonStub: RaccoonStub
    ) {
        controllerProcessor = ControllerProcessor(raccoonStub)
        raccoonConfig.serviceClasses.forEach { service ->
            // Creating Runtime object of the Service class mentioned in the Config
            // Adds the Service instance to the serviceSwitch object.
            serviceSwitch.addService(createServiceObject(service, raccoonStub))
        }

    }

    /**
     * Object creation of the service class
     *
     * @param service {@see RaccoonService} type.
     * @return Returns raccoon service class.
     */
    private fun createServiceObject(
        service: KClass<out RaccoonService>,
        raccoonStub: RaccoonStub
    ): RaccoonService {

        // Object creation.
        val serviceInstance = service.java.newInstance()

        serviceInstance.raccoonStub = raccoonStub

        // Initial value.
        raccoonStub.serviceGraph.serviceObjects[serviceInstance.serviceId] = mutableListOf()


        serviceInstance.javaClass.methods

            .filter { method ->
                // Filtering based on annotation.
                method.getAnnotation(RaccoonController::class.java) != null
            }

            .forEach { method ->
                @Suppress("UNCHECKED_CAST")
                val firstControllerObject = createControllerObject(
                    method.invoke(serviceInstance)
                            as KClass<me.iamjoseph.raccoon.controller.RaccoonController>
                )

                // Controller object.
                val controllers: MutableList<me.iamjoseph.raccoon.controller.RaccoonController> =
                    mutableListOf()

                firstControllerObject.javaClass.getAnnotation(ControllerModule::class.java)
                    ?.let { controllerList ->
                        controllerList.includeControllers.forEach { controllerClass ->
                            controllers.add(
                                createControllerObject(
                                    controllerClass
                                )
                            )
                        }

                    }

                controllers.add(firstControllerObject)

                // adding controller objects into service.
                raccoonStub.serviceGraph.serviceObjects[serviceInstance.serviceId.split(".")
                    .last()
                ]?.add(
                    controllers
                )

                // Controller graph initialization entry point
                controllerProcessor.makeControllerGraph(serviceInstance, controllers)
            }
        return serviceInstance
    }

    /**
     * Controller object creation.
     */
    private fun createControllerObject(
        returnType:
        KClass<out me.iamjoseph.raccoon.controller.RaccoonController>
    ):
            me.iamjoseph.raccoon.controller.RaccoonController {
        return returnType.java.newInstance()
                as me.iamjoseph.raccoon.controller.RaccoonController

    }
}