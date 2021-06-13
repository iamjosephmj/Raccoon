package io.iamjosephmj.raccoon.presentation.request

/**
 * Raccoon Request blue-print.
 *
 * @author Joseph James.
 */
data class RaccoonRequest(
    val parameters: Parameters,
    val requestBody: String?,
    val requestType: RaccoonRequestType,
    val endpoint: String
)