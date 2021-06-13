package io.iamjosephmj.raccoon.core.stub.interceptor

import io.iamjosephmj.raccoon.presentation.request.RaccoonRequest
import io.iamjosephmj.raccoon.presentation.response.RaccoonResponse
import io.iamjosephmj.raccoon.service.RaccoonService

/**
 * This Interface is used for interaction of Interceptor with the Service class.
 *
 * @author Joseph James.
 */
interface InterceptorStub {


    /**
     * This is the request-response handshake method.
     *
     * @param raccoonRequest packed from the interceptor
     * @return response from the controller layer.
     */
    fun execute(raccoonRequest: RaccoonRequest): RaccoonResponse

    fun addService(raccoonService: RaccoonService)
}