@file:Suppress("UNUSED_PARAMETER")

package me.iamjoseph.raccoon.helper

import me.iamjoseph.raccoon.annotations.ControllerModule
import me.iamjoseph.raccoon.annotations.Params
import me.iamjoseph.raccoon.annotations.RaccoonEndpoint
import me.iamjoseph.raccoon.controller.RaccoonController
import me.iamjoseph.raccoon.helper.request.MoshiRequestBody
import me.iamjoseph.raccoon.presentation.request.Parameters
import me.iamjoseph.raccoon.presentation.request.RaccoonRequestType
import me.iamjoseph.raccoon.presentation.response.RaccoonResponse


@ControllerModule(includeControllers = [GsonController::class])
class GsonControllerInclude : RaccoonController() {

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
        MoshiRequestBody: MoshiRequestBody,
        @Params headers: Parameters
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

    @RaccoonEndpoint(
        endpoint = "moshi-test-no-request-object",
        latency = 100,
        requestType = RaccoonRequestType.UPDATE
    )
    fun apiInvocationNoRequestObj(
        @Params headers: Parameters
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