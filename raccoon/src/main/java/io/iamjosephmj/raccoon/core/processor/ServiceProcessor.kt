package io.iamjosephmj.raccoon.core.processor

import io.iamjosephmj.raccoon.annotations.RaccoonController
import io.iamjosephmj.raccoon.core.stub.config.RaccoonConfig
import io.iamjosephmj.raccoon.core.stub.graph.ServiceGraph
import io.iamjosephmj.raccoon.core.stub.interceptor.InterceptorStubImpl
import io.iamjosephmj.raccoon.service.RaccoonService
import kotlin.reflect.KClass

object ServiceProcessor {

    fun makeServiceGraph(
        raccoonConfig: RaccoonConfig,
        serviceSwitch: InterceptorStubImpl
    ) {

        raccoonConfig.serviceClasses.forEach { service ->
            serviceSwitch.addService(createServiceObject(service))
        }

    }

    private fun createServiceObject(service: KClass<out RaccoonService>): RaccoonService {

        val serviceInstance = service.java.newInstance()

        // Initial value.
        ServiceGraph.serviceObjects[serviceInstance.serviceId] = mutableListOf()


        serviceInstance.javaClass.methods

            .filter { method ->
                method.getAnnotation(RaccoonController::class.java) != null
            }

            .forEach { method ->

                @Suppress("UNCHECKED_CAST")
                val controller =
                    createControllerObject(
                        method.invoke(serviceInstance)
                                as KClass<io.iamjosephmj.raccoon.controller.RaccoonController>
                    )

                ServiceGraph.serviceObjects[serviceInstance.serviceId.split(".")
                    .last()
                ]?.add(
                    controller
                )

                ControllerProcessor.makeControllerGraph(serviceInstance, controller)
            }
        return serviceInstance
    }

    private fun createControllerObject(
        returnType:
        KClass<io.iamjosephmj.raccoon.controller.RaccoonController>
    ):
            io.iamjosephmj.raccoon.controller.RaccoonController {
        return returnType.java.newInstance()
                as io.iamjosephmj.raccoon.controller.RaccoonController

    }
}