package io.iamjosephmj.raccoon.core.stub.config

import io.iamjosephmj.raccoon.service.RaccoonService
import kotlin.reflect.KClass

/**
 * Service configuration class
 *
 * @author Joseph James.
 */
class RaccoonConfig(
    val serviceClasses: List<KClass<out RaccoonService>>
) {

    private constructor(builder: Builder) : this(builder.serviceClasses)

    class Builder {
        val serviceClasses: MutableList<KClass<out RaccoonService>> = mutableListOf()

        fun addService(service: KClass<out RaccoonService>): Builder {
            serviceClasses.add(service)
            return this
        }

        fun build(): RaccoonConfig {
            return RaccoonConfig(this)
        }

    }
}