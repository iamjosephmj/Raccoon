package io.iamjosephmj.raccoon.core.stub.config

import io.iamjosephmj.raccoon.parser.GsonPlugin
import io.iamjosephmj.raccoon.parser.RaccoonParser
import io.iamjosephmj.raccoon.service.RaccoonService
import kotlin.reflect.KClass

/**
 * Service configuration class
 *
 * @author Joseph James.
 */
class RaccoonConfig(
    val serviceClasses: List<KClass<out RaccoonService>>,
    val raccoonParserPlugin: RaccoonParser = GsonPlugin()
) {

    class Builder {
        private val serviceClasses: MutableList<KClass<out RaccoonService>> = mutableListOf()

        private var raccoonParserType: RaccoonParser = GsonPlugin()

        fun addService(service: KClass<out RaccoonService>): Builder {
            serviceClasses.add(service)
            return this
        }

        fun setParserType(raccoonParserType: RaccoonParser): Builder {
            this.raccoonParserType = raccoonParserType
            return this
        }

        fun build(): RaccoonConfig {
            return RaccoonConfig(serviceClasses, raccoonParserType)
        }

    }
}