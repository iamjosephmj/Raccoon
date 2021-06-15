package io.iamjosephmj.raccoon.helper

import io.iamjosephmj.raccoon.annotations.Params
import io.iamjosephmj.raccoon.annotations.RaccoonEndpoint
import io.iamjosephmj.raccoon.controller.RaccoonController
import io.iamjosephmj.raccoon.helper.request.GsonRequestBody
import io.iamjosephmj.raccoon.presentation.request.Parameters
import io.iamjosephmj.raccoon.presentation.request.RaccoonRequestType
import io.iamjosephmj.raccoon.presentation.response.RaccoonResponse

class GsonController : RaccoonController() {

    override fun setup() {
        // Do the DI related stuff
    }

    @RaccoonEndpoint(
        endpoint = "test-get-request",
        latency = 100,
        RaccoonRequestType.GET
    )
    fun apiInvocationGet(@Params headers: Parameters): RaccoonResponse {
        return RaccoonResponse(
            statusCode = 200,
            body = "{success}"
        )
    }

    @RaccoonEndpoint(
        endpoint = "test-post-no-body-request",
        latency = 100,
        RaccoonRequestType.POST
    )
    fun apiInvocationPostNoBody(@Params headers: Parameters): RaccoonResponse {
        return RaccoonResponse(
            statusCode = 200,
            body = "{success}"
        )
    }


    @RaccoonEndpoint(
        endpoint = "test-query-params-request",
        latency = 100,
        RaccoonRequestType.GET
    )
    fun apiInvocationQueryParams(@Params headers: Parameters): RaccoonResponse {
        return RaccoonResponse(
            statusCode = 200,
            body = "${headers.queryParameters["query"]}".trim()
        )
    }

    @RaccoonEndpoint(
        endpoint = "test-header-request",
        latency = 100,
        RaccoonRequestType.GET
    )
    fun apiInvocationHeader(@Params headers: Parameters): RaccoonResponse {
        return RaccoonResponse(
            statusCode = 200,
            body = headers.headers[0].second.trim()
        )
    }

    @RaccoonEndpoint(
        endpoint = "test-post-request",
        latency = 100,
        RaccoonRequestType.POST
    )
    fun apiInvocationPost(
        @Params headers: Parameters,
        gsonRequestBody: GsonRequestBody
    ): RaccoonResponse {
        return RaccoonResponse(
            statusCode = 200,
            body = "{success}"
        )
    }


    @RaccoonEndpoint(
        endpoint = "test-put-request",
        latency = 100,
        RaccoonRequestType.PUT
    )
    fun apiInvocationPut(
        @Params headers: Parameters,
        gsonRequestBody: GsonRequestBody
    ): RaccoonResponse {
        return RaccoonResponse(
            statusCode = 200,
            body = "{success}"
        )
    }


    @RaccoonEndpoint(
        endpoint = "test-delete-request",
        latency = 100,
        RaccoonRequestType.DELETE
    )
    fun apiInvocationDelete(
        @Params headers: Parameters,
        gsonRequestBody: GsonRequestBody
    ): RaccoonResponse {
        return RaccoonResponse(
            statusCode = 200,
            body = "{success}"
        )
    }

    @RaccoonEndpoint(
        endpoint = "test-patch-request",
        latency = 100,
        RaccoonRequestType.PATCH
    )
    fun apiInvocationPatch(
        @Params headers: Parameters,
        gsonRequestBody: GsonRequestBody
    ): RaccoonResponse {
        return RaccoonResponse(
            statusCode = 200,
            body = "{success}"
        )
    }

    @RaccoonEndpoint(
        endpoint = "test-update-request",
        latency = 100,
        requestType = RaccoonRequestType.UPDATE
    )
    fun apiInvocationUpdate(
        @Params headers: Parameters,
        gsonRequestBody: GsonRequestBody
    ): RaccoonResponse {
        return RaccoonResponse(
            statusCode = 200,
            body = "{success}"
        )
    }


    override fun tearDown() {
        // clean up memory.
    }
}