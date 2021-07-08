package me.iamjoseph.raccoon.presentation.plugins

import me.iamjoseph.raccoon.core.stub.RaccoonStub
import me.iamjoseph.raccoon.exception.EndpointNotFoundException
import me.iamjoseph.raccoon.exception.RaccoonException
import me.iamjoseph.raccoon.exception.UrlNotFoundException
import me.iamjoseph.raccoon.util.Utils.createRequest
import me.iamjoseph.raccoon.util.Utils.createResponse
import okhttp3.Interceptor
import okhttp3.Response

/**
 * This is the interceptor plugin for Retrofit.
 *
 * @author Joseph James.
 */
class RaccoonOkHttpPlugin : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.createRequest()
        return try {
            val resp = RaccoonStub.getServiceSwitch().execute(request)
            resp.createResponse(chain.request())
        } catch (urlExp: RaccoonException) {
            chain.proceed(chain.request())
        }
    }

}