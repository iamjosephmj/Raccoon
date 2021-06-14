package io.iamjosephmj.raccoon.presentation.request

/**
 * Raccoon Request blue-print.
 *
 * @author Joseph James.
 */
data class RaccoonRequest(
    // Request params.
    val parameters: Parameters,

    // Body of the request that can be used under the hood by the library for parsing.
    val requestBody: String?,

    // Type of request.
    val requestType: RaccoonRequestType,

    // Endpoint of the request.
    val endpoint: String
)