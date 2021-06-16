package me.iamjoseph.raccoon.presentation.response

import me.iamjoseph.raccoon.presentation.request.Parameters

/**
 * Raccoon Response blueprint.
 *
 * @author Joseph James.
 */
data class RaccoonResponse(
    val statusCode: Int,
    val body: String,
    val parameters: Parameters = Parameters()
)