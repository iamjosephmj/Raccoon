@file:Suppress("unused")

package me.iamjoseph.raccoonsample.retrofit

import com.google.gson.annotations.SerializedName

data class Request(
    @SerializedName("userId")
    val userId: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("completed")
    val completed: Boolean?
)