package io.iamjosephmj.raccoon.helper

import io.iamjosephmj.raccoon.annotations.Params
import io.iamjosephmj.raccoon.annotations.RaccoonEndpoint
import io.iamjosephmj.raccoon.controller.RaccoonController
import io.iamjosephmj.raccoon.helper.request.MoshiRequestBody
import io.iamjosephmj.raccoon.presentation.request.Parameters
import io.iamjosephmj.raccoon.presentation.request.RaccoonRequestType
import io.iamjosephmj.raccoon.presentation.response.RaccoonResponse

class MoshiController : RaccoonController() {

    override fun setup() {
        // Do the DI related stuff
    }

    @RaccoonEndpoint(
        endpoint = "moshi-test-get-request",
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
        endpoint = "moshi-test-post-no-body-request",
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
        endpoint = "moshi-test-query-params-request",
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
        endpoint = "moshi-test-header-request",
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
        endpoint = "moshi-test-post-request",
        latency = 100,
        RaccoonRequestType.POST
    )
    fun apiInvocationPost(
        @Params headers: Parameters,
        MoshiRequestBody: MoshiRequestBody
    ): RaccoonResponse {
        return RaccoonResponse(
            statusCode = 200,
            body = "{success}"
        )
    }


    @RaccoonEndpoint(
        endpoint = "moshi-test-put-request",
        latency = 100,
        RaccoonRequestType.PUT
    )
    fun apiInvocationPut(
        @Params headers: Parameters,
        MoshiRequestBody: MoshiRequestBody
    ): RaccoonResponse {
        return RaccoonResponse(
            statusCode = 200,
            body = "{success}"
        )
    }


    @RaccoonEndpoint(
        endpoint = "moshi-test-delete-request",
        latency = 100,
        RaccoonRequestType.DELETE
    )
    fun apiInvocationDelete(
        @Params headers: Parameters,
        MoshiRequestBody: MoshiRequestBody
    ): RaccoonResponse {
        return RaccoonResponse(
            statusCode = 200,
            body = "{success}"
        )
    }

    @RaccoonEndpoint(
        endpoint = "moshi-test-patch-request",
        latency = 100,
        RaccoonRequestType.PATCH
    )
    fun apiInvocationPatch(
        @Params headers: Parameters,
        MoshiRequestBody: MoshiRequestBody
    ): RaccoonResponse {
        return RaccoonResponse(
            statusCode = 200,
            body = "{success}"
        )
    }

    @RaccoonEndpoint(
        endpoint = "moshi-test-update-request",
        latency = 100,
        requestType = RaccoonRequestType.UPDATE
    )
    fun apiInvocationUpdate(
        @Params headers: Parameters,
        MoshiRequestBody: MoshiRequestBody
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