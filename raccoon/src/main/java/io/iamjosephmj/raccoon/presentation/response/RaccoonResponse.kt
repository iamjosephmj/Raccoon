package io.iamjosephmj.raccoon.presentation.response

/**
 * Raccoon Response blueprint.
 *
 * @author Joseph James.
 */
data class RaccoonResponse(
    val statusCode: Int,
    val body: String,
)