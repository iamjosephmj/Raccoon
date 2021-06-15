package me.iamjoseph.raccoon.core.stub.interceptor

import me.iamjoseph.raccoon.presentation.request.RaccoonRequest
import me.iamjoseph.raccoon.presentation.response.RaccoonResponse
import me.iamjoseph.raccoon.service.RaccoonService


/**
 * This Interface is used for interaction of Interceptor with the Service class.
 *
 * @author Joseph James.
 */
internal interface InterceptorStub {


    /**
     * This is the request-response handshake method.
     *
     * @param raccoonRequest packed from the interceptor
     * @return response from the controller layer.
     */
    fun execute(raccoonRequest: RaccoonRequest): RaccoonResponse

    fun addService(raccoonService: RaccoonService)
}