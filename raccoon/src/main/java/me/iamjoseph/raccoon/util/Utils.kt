package me.iamjoseph.raccoon.util

import me.iamjoseph.raccoon.core.stub.RaccoonStub
import me.iamjoseph.raccoon.presentation.request.Parameters
import me.iamjoseph.raccoon.presentation.request.RaccoonRequest
import me.iamjoseph.raccoon.presentation.request.RaccoonRequestType
import me.iamjoseph.raccoon.presentation.response.RaccoonResponse
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import okio.BufferedSink
import kotlin.reflect.KClass

object Utils {

    fun Interceptor.Chain.createRequest(): RaccoonRequest {
        val request = request()

        val urlDetails = request.url.toString().split("?")

        val queryParams = mutableMapOf<String, String>()

        urlDetails.subList(1, urlDetails.size).forEach { params ->
            val data = params.split("=")
            queryParams[data[0]] = data[1]
        }

        val parameters = Parameters(
            headers = request.headers.toPair(),
            queryParameters = queryParams
        )

        val sink: BufferedSink = Buffer()
        request.body?.writeTo(sink)

        return RaccoonRequest(
            requestBody = sink.toString()
                .trimDownToRequestBody(),
            parameters = parameters,
            requestType = request.fetchRequestType(),
            endpoint = urlDetails.first()
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
            "UPDATE" -> {
                RaccoonRequestType.UPDATE
            }
            else -> {
                RaccoonRequestType.GET
            }
        }
    }

    fun Any.buildRaccoonResponse(
        statusCode: Int,
    ): RaccoonResponse {

        val resp = RaccoonStub.raccoonParser.parseToJson(this, this::class as KClass<Any>)

        return RaccoonResponse(
            statusCode = statusCode,
            body = resp
        )
    }
}