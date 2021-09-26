package me.iamjoseph.raccoon.util

import me.iamjoseph.raccoon.controller.RaccoonController
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
        val response = Response.Builder()
            .code(this.statusCode)
            .request(request)
            .message(this.body)
            .protocol(Protocol.HTTP_2)
            .body(
                this.body.toByteArray()
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
        parameters.headers.forEach { header ->
            response.addHeader(header.first, header.second)
        }
        return response.build()
    }

    private fun String.trimDownToRequestBody(): String {
        return replace("text=", "")
            .replace("[", "")
            .replace("]", "")
    }


    private fun Headers.toPair(): List<Pair<String, String>> {
        val pairs = mutableListOf<Pair<String, String>>()

        val newPair = names().map { name ->
            Pair(name, get(name) ?: "")
        }.toMutableList()

        pairs.addAll(newPair)
        return pairs

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
        parameters: Parameters = Parameters(),
        raccoonController: RaccoonController
    ): RaccoonResponse {

        val resp = raccoonController.raccoonStub.raccoonParser.parseToJson(
            this,
            this::class as KClass<Any>
        )

        return RaccoonResponse(
            statusCode = statusCode,
            body = resp,
            parameters = parameters
        )
    }
}