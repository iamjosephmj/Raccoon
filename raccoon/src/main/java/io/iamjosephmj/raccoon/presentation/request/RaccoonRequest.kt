package io.iamjosephmj.raccoon.presentation.request

import okhttp3.RequestBody

data class RaccoonRequest(
    val headers: List<Pair<String, String>> = arrayListOf(),
    val requestBody: String?,
    val requestType: RaccoonRequestType,
    val endpoint: String
)