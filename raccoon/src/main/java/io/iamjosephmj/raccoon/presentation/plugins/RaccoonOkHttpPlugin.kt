package io.iamjosephmj.raccoon.presentation.plugins

import io.iamjosephmj.raccoon.core.stub.RaccoonStub
import io.iamjosephmj.raccoon.exception.UrlNotFoundException
import io.iamjosephmj.raccoon.util.GsonUtils.createRequest
import io.iamjosephmj.raccoon.util.GsonUtils.createResponse
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
        } catch (urlExp: UrlNotFoundException) {
            chain.proceed(chain.request())
        }
    }

}