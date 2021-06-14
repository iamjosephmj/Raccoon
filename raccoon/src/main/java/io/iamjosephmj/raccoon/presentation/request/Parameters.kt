package io.iamjosephmj.raccoon.presentation.request

/**
 * This class holds the headers and query-params of the request
 *
 * @author Joseph James.
 */
data class Parameters(
    // Headers passed in the request.
    val headers: List<Pair<String, String>> = arrayListOf(),
    // Query params passed in the request.
    val queryParameters: Map<String, String> = mapOf()
)