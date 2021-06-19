package me.iamjoseph.raccoon.annotations

import me.iamjoseph.raccoon.presentation.request.RaccoonRequestType

/**
 * Annotation class for Controller-Endpoints.
 *
 * @author Joseph James.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class RaccoonEndpoint(
    val endpoint: String,
    val responseTime: Long = 100,
    val requestType: RaccoonRequestType
)
