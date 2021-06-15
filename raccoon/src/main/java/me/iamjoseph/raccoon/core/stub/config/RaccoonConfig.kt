package me.iamjoseph.raccoon.core.stub.config

import me.iamjoseph.raccoon.parser.GsonPlugin
import me.iamjoseph.raccoon.parser.RaccoonParser
import me.iamjoseph.raccoon.service.RaccoonService
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
            return me.iamjoseph.raccoon.core.stub.config.RaccoonConfig(
                serviceClasses,
                raccoonParserType
            )
        }

    }
}