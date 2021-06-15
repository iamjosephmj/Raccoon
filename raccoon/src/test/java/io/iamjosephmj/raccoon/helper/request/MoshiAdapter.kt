package io.iamjosephmj.raccoon.helper.request

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class MoshiAdapter {
    @ToJson
    fun moshiReqToString(req: MoshiRequestBody): String {
        return req.id.toString()
    }

    @FromJson
    fun moshiReqFromString(req: String): MoshiRequestBody {
        return MoshiRequestBody(req.toInt())
    }

}