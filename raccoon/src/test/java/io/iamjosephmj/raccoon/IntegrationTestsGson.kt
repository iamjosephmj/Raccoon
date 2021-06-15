package io.iamjosephmj.raccoon

import com.google.gson.Gson
import io.iamjosephmj.raccoon.core.stub.RaccoonStub
import io.iamjosephmj.raccoon.core.stub.config.RaccoonConfig
import io.iamjosephmj.raccoon.exception.EndpointNotFoundException
import io.iamjosephmj.raccoon.helper.MockService
import io.iamjosephmj.raccoon.helper.request.GsonRequestBody
import io.iamjosephmj.raccoon.parser.GsonPlugin
import io.iamjosephmj.raccoon.presentation.request.Parameters
import io.iamjosephmj.raccoon.presentation.request.RaccoonRequest
import io.iamjosephmj.raccoon.presentation.request.RaccoonRequestType
import org.junit.After
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class IntegrationTestsGson {

    @Before
    fun setupStub() {
        RaccoonStub.setUp(
            RaccoonConfig.Builder()
                .addService(MockService::class)
                .setParserType(GsonPlugin())
                .build()
        )
    }

    @Test
    fun testGetRequestSuccess() {
        val request = RaccoonRequest(
            requestBody = null,
            requestType = RaccoonRequestType.GET,
            endpoint = "test-get-request",
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
            endpoint = "test-post-no-body-request",
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
            endpoint = "test-query-params-request",
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
            endpoint = "test-header-request",
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
            requestBody = Gson().toJson(GsonRequestBody(id = 10)),
            requestType = RaccoonRequestType.POST,
            endpoint = "test-post-request",
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
            requestBody = Gson().toJson(GsonRequestBody(id = 10)),
            requestType = RaccoonRequestType.PUT,
            endpoint = "test-put-request",
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
            requestBody = Gson().toJson(GsonRequestBody(id = 10)),
            requestType = RaccoonRequestType.DELETE,
            endpoint = "test-delete-request",
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
            requestBody = Gson().toJson(GsonRequestBody(id = 10)),
            requestType = RaccoonRequestType.PATCH,
            endpoint = "test-patch-request",
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
            requestBody = Gson().toJson(GsonRequestBody(id = 10)),
            requestType = RaccoonRequestType.UPDATE,
            endpoint = "test-update-request",
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


    @After
    fun cleanUp() {
        RaccoonStub.teatDown()
    }

}