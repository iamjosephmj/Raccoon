package io.iamjosephmj.raccoon.util

import io.iamjosephmj.raccoon.presentation.request.RaccoonRequest
import io.iamjosephmj.raccoon.presentation.request.RaccoonRequestType
import io.iamjosephmj.raccoon.presentation.response.RaccoonResponse
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody

object GsonUtils {

    fun Interceptor.Chain.createRequest(): RaccoonRequest {
        val request = request()
        return RaccoonRequest(
            requestBody = request.body,
            headers = request.headers.toPair(),
            requestType = request.fetchRequestType(),
            endpoint = request.url.toString()
        )
    }

    fun RaccoonResponse.createResponse(request: Request): Response {
        return Response.Builder()
            .code(this.statusCode)
            .request(request)
            .message(this.body)
            .protocol(Protocol.HTTP_2)
            .addHeader("content-type", "application/json")
            .body(
                this.body.toByteArray()
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
            .build()
    }


    fun Headers.toPair(): List<Pair<String, String>> {
        return map { header ->
            header
        }
    }

    fun Request.fetchRequestType(): RaccoonRequestType {
        return when (method) {
            "GET" -> {
                RaccoonRequestType.GET
            }
            "POST" -> {
                RaccoonRequestType.POST
            }

            "PUT" -> {
                RaccoonRequestType.PUT
            }

            "DELETE" -> {
                RaccoonRequestType.DELETE
            }

            "PATCH" -> {
                RaccoonRequestType.PATCH
            }
            else -> {
                RaccoonRequestType.GET
            }
        }
    }
}