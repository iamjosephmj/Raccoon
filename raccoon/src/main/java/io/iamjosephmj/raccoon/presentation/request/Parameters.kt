package io.iamjosephmj.raccoon.presentation.request

/**
 * This class holds the headers and query-params of the request
 *
 * @author Joseph James.
 */
data class Parameters(
    val headers: List<Pair<String, String>> = arrayListOf(),
    val queryParameters: Map<String, String> = mapOf()
)