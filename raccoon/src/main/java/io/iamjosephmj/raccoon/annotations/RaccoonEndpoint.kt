package io.iamjosephmj.raccoon.annotations

import io.iamjosephmj.raccoon.presentation.request.RaccoonRequestType

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class RaccoonEndpoint(
    val endpoint: String,
    val latency: Long = 100,
    val requestType: RaccoonRequestType
)
