package me.iamjoseph.raccoon.helper.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoshiRequestBody(
    val id: Int
)