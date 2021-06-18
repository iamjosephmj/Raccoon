package me.iamjoseph.raccoon.annotations

import me.iamjoseph.raccoon.controller.RaccoonController
import kotlin.reflect.KClass

/**
 * Annotation class for MockServices
 *
 * @author Joseph James.
 */
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class Params


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ControllerModule(
    vararg val includeControllers: KClass<out RaccoonController>
)