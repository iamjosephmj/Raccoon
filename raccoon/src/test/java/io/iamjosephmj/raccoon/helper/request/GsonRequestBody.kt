package io.iamjosephmj.raccoon.helper.request

import com.google.gson.annotations.SerializedName

data class GsonRequestBody(
    @SerializedName("id")
    val id: Int
)