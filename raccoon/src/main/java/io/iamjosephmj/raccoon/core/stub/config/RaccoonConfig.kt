package io.iamjosephmj.raccoon.core.stub.config

import io.iamjosephmj.raccoon.service.RaccoonService
import kotlin.reflect.KClass


data class RaccoonConfig(
    val serviceClasses: List<KClass<out RaccoonService>>
)