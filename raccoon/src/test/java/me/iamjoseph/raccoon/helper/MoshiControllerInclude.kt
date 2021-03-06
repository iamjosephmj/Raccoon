package me.iamjoseph.raccoon.helper

import me.iamjoseph.raccoon.annotations.ControllerModule
import me.iamjoseph.raccoon.annotations.Params
import me.iamjoseph.raccoon.annotations.RaccoonEndpoint
import me.iamjoseph.raccoon.controller.RaccoonController
import me.iamjoseph.raccoon.helper.request.GsonRequestBody
import me.iamjoseph.raccoon.presentation.request.Parameters
import me.iamjoseph.raccoon.presentation.request.RaccoonRequestType
import me.iamjoseph.raccoon.presentation.response.RaccoonResponse

@ControllerModule(includeControllers = [MoshiController::class])
class MoshiControllerInclude : RaccoonController() {

    override fun setup() {
        // Do the DI related stuff
    }

    @RaccoonEndpoint(
        endpoint = "test-get-request",
        responseTime = 100,
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
        responseTime = 100,
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
        responseTime = 100,
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
        responseTime = 100,
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
        responseTime = 100,
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
        responseTime = 100,
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
        responseTime = 100,
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
        responseTime = 100,
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
        responseTime = 100,
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

    @RaccoonEndpoint(
        endpoint = "test-header-request",
        responseTime = 100,
        requestType = RaccoonRequestType.POST
    )
    fun apiInvocationHeader(
        @Params headers: Parameters,
        gsonRequestBody: GsonRequestBody
    ): RaccoonResponse {
        return RaccoonResponse(
            statusCode = 200,
            body = "{success}",
            parameters = Parameters(
                headers = listOf(Pair("header-test", "{success}"))
            )
        )
    }


    override fun tearDown() {
        // clean up memory.
    }
}