package io.iamjosephmj.raccoon.presentation.request

/**
 * Raccoon Request blue-print.
 *
 * @author Joseph James.
 */
data class RaccoonRequest(
    val headers: List<Pair<String, String>> = arrayListOf(),
    val requestBody: String?,
    val requestType: RaccoonRequestType,
    val endpoint: String
)