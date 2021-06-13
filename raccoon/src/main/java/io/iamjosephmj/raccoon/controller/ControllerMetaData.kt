package io.iamjosephmj.raccoon.controller

import io.iamjosephmj.raccoon.presentation.request.RaccoonRequestType

/**
 * This class is used for storing the metadata for each controller endpoint functions.
 *
 * @author Joseph James.
 */
data class ControllerMetaData(
    val endpoint: String,
    val latency: Long = 100,
    val requestType: RaccoonRequestType
)