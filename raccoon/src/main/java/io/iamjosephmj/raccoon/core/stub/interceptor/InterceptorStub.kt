package io.iamjosephmj.raccoon.core.stub.interceptor

import io.iamjosephmj.raccoon.presentation.request.RaccoonRequest
import io.iamjosephmj.raccoon.presentation.response.RaccoonResponse

/**
 * This Interface is used for interaction of Interceptor with the Service class.
 *
 * @author Joseph James.
 */
interface InterceptorStub {

    /**
     * This is to notify the service class that the http request has started.
     *
     */
    fun preRequestNotification(raccoonRequest: RaccoonRequest)

    /**
     * This is the request-response handshake method.
     *
     * @param raccoonRequest packed from the interceptor
     * @return response from the controller layer.
     */
    fun request(raccoonRequest: RaccoonRequest): RaccoonResponse

    /**
     * This is to notify the service class that the http request has ended.
     *
     */
    fun postRequestNotification(raccoonRequest: RaccoonRequest)
}