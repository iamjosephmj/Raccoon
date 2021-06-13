package io.iamjosephmj.raccoon.util

import com.google.gson.Gson
import io.iamjosephmj.raccoon.presentation.request.RaccoonRequest
import io.iamjosephmj.raccoon.presentation.request.RaccoonRequestType
import io.iamjosephmj.raccoon.presentation.response.RaccoonResponse
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import okio.BufferedSink

object GsonUtils {

    fun Interceptor.Chain.createRequest(): RaccoonRequest {
        val request = request()

        val sink: BufferedSink = Buffer()
        request.body?.writeTo(sink)

        return RaccoonRequest(
            requestBody = sink.toString()
                .trimDownToRequestBody(),
            headers = request.headers.toPair(),
            requestType = request.fetchRequestType(),
            endpoint = request.url.toString()
        ).apply {
            sink.close()
        }
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

    private fun String.trimDownToRequestBody(): String {
        return replace("text=", "")
            .replace("[", "")
            .replace("]", "")
    }


    private fun Headers.toPair(): List<Pair<String, String>> {
        return map { header ->
            header
        }
    }

    private fun Request.fetchRequestType(): RaccoonRequestType {
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

    fun Any.buildRaccoonResponse(
        statusCode: Int
    ): RaccoonResponse {
        val resp = Gson().toJson(this)
        return RaccoonResponse(
            statusCode = statusCode,
            body = resp
        )
    }
}