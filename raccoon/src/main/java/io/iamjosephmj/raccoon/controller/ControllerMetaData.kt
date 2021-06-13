package io.iamjosephmj.raccoon.controller

import io.iamjosephmj.raccoon.presentation.request.RaccoonRequestType

data class ControllerMetaData(
    val endpoint: String,
    val latency: Long = 100,
    val requestType: RaccoonRequestType
)