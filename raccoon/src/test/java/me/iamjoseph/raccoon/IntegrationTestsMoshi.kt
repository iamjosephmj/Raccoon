package me.iamjoseph.raccoon

import me.iamjoseph.raccoon.core.stub.RaccoonStub
import me.iamjoseph.raccoon.core.stub.config.RaccoonConfig
import me.iamjoseph.raccoon.exception.EndpointNotFoundException
import me.iamjoseph.raccoon.helper.MockService
import me.iamjoseph.raccoon.helper.request.MoshiRequestBody
import me.iamjoseph.raccoon.parser.MoshiPlugin
import me.iamjoseph.raccoon.presentation.request.Parameters
import me.iamjoseph.raccoon.presentation.request.RaccoonRequest
import me.iamjoseph.raccoon.presentation.request.RaccoonRequestType
import org.junit.After
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class IntegrationTestsMoshi {

    private val moshiPlugin by lazy {
        MoshiPlugin()
    }

    @Before
    fun setupStub() {
        RaccoonStub.setUp(
            RaccoonConfig.Builder()
                .addService(MockService::class)
                .setParserType(MoshiPlugin())
                .build()
        )
    }

    @Test
    fun testGetRequestSuccess() {
        val request = RaccoonRequest(
            requestBody = null,
            requestType = RaccoonRequestType.GET,
            endpoint = "moshi-test-get-request",
            parameters = Parameters()
        )

        val response = RaccoonStub.getServiceSwitch().execute(
            request
        )
        assertSame(response.body, "{success}")
    }

    @Test
    fun testPostNoWithBodyRequestSuccess() {
        val request = RaccoonRequest(
            requestBody = null,
            requestType = RaccoonRequestType.POST,
            endpoint = "moshi-test-post-no-body-request",
            parameters = Parameters()
        )

        val response = RaccoonStub.getServiceSwitch().execute(
            request
        )
        assertSame(response.body, "{success}")
    }

    @Test
    fun testQueryParamsRequestSuccess() {
        val request = RaccoonRequest(
            requestBody = null,
            requestType = RaccoonRequestType.GET,
            endpoint = "moshi-test-query-params-request",
            parameters = Parameters(
                queryParameters = mapOf(
                    Pair("query", "123")
                )
            )
        )

        val response = RaccoonStub.getServiceSwitch().execute(
            request
        )
        assertSame(response.body, "123")
    }

    @Test
    fun testHeaderRequestSuccess() {
        val request = RaccoonRequest(
            requestBody = null,
            requestType = RaccoonRequestType.GET,
            endpoint = "moshi-test-header-request",
            parameters = Parameters(
                headers = listOf(
                    Pair("user", "Android")
                )
            )
        )

        val response = RaccoonStub.getServiceSwitch().execute(
            request
        )
        assertSame(response.body, "Android")
    }


    @Test
    fun testPostRequestSuccess() {
        val request = RaccoonRequest(
            requestBody = moshiPlugin.parseToJson(
                MoshiRequestBody(id = 10),
                MoshiRequestBody::class
            ),
            requestType = RaccoonRequestType.POST,
            endpoint = "moshi-test-post-request",
            parameters = Parameters()
        )

        val response = RaccoonStub.getServiceSwitch().execute(
            request
        )
        assertSame(response.body, "{success}")
    }

    @Test
    fun testPutRequestSuccess() {
        val request = RaccoonRequest(
            requestBody = moshiPlugin.parseToJson(
                MoshiRequestBody(id = 10),
                MoshiRequestBody::class
            ),
            requestType = RaccoonRequestType.PUT,
            endpoint = "moshi-test-put-request",
            parameters = Parameters()
        )

        val response = RaccoonStub.getServiceSwitch().execute(
            request
        )
        assertSame(response.body, "{success}")
    }

    @Test
    fun testDeleteRequestSuccess() {
        val request = RaccoonRequest(
            requestBody = moshiPlugin.parseToJson(
                MoshiRequestBody(id = 10),
                MoshiRequestBody::class
            ),
            requestType = RaccoonRequestType.DELETE,
            endpoint = "moshi-test-delete-request",
            parameters = Parameters()
        )

        val response = RaccoonStub.getServiceSwitch().execute(
            request
        )
        assertSame(response.body, "{success}")
    }

    @Test
    fun testPatchRequestSuccess() {
        val request = RaccoonRequest(
            requestBody = moshiPlugin.parseToJson(
                MoshiRequestBody(id = 10),
                MoshiRequestBody::class
            ),
            requestType = RaccoonRequestType.PATCH,
            endpoint = "moshi-test-patch-request",
            parameters = Parameters()
        )

        val response = RaccoonStub.getServiceSwitch().execute(
            request
        )
        assertSame(response.body, "{success}")
    }

    @Test
    fun testUpdateRequestSuccess() {
        val request = RaccoonRequest(
            requestBody = moshiPlugin.parseToJson(
                MoshiRequestBody(id = 10),
                MoshiRequestBody::class
            ),
            requestType = RaccoonRequestType.UPDATE,
            endpoint = "moshi-test-update-request",
            parameters = Parameters()
        )

        val response = RaccoonStub.getServiceSwitch().execute(
            request
        )
        assertSame(response.body, "{success}")
    }

    @Test
    fun invalidEndpoint() {
        val request = RaccoonRequest(
            requestBody = null,
            requestType = RaccoonRequestType.GET,
            endpoint = "no-a-valid-request",
            parameters = Parameters()
        )

        val isExceptionOccurred = try {
            RaccoonStub.getServiceSwitch().execute(
                request
            )
            false
        } catch (ex: EndpointNotFoundException) {
            true
        }

        assertTrue(isExceptionOccurred)

    }


    @Test
    fun noRequestObject() {
        val request = RaccoonRequest(
            requestBody = moshiPlugin.parseToJson(
                MoshiRequestBody(id = 10),
                MoshiRequestBody::class
            ),
            requestType = RaccoonRequestType.POST,
            endpoint = "moshi-test-no-request-object",
            parameters = Parameters()
        )

        val isExceptionOccurred = try {
            RaccoonStub.getServiceSwitch().execute(
                request
            )
            false
        } catch (ex: EndpointNotFoundException) {
            true
        }

        assertTrue(isExceptionOccurred)
    }


    @After
    fun cleanUp() {
        RaccoonStub.tearDown()
    }

}