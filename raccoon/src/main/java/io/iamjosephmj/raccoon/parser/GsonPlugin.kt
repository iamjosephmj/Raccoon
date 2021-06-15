package io.iamjosephmj.raccoon.parser

import com.google.gson.Gson
import kotlin.reflect.KClass

class GsonPlugin : RaccoonParser {

    private val gson by lazy {
        Gson()
    }

    override fun <T : Any> getFromString(data: String, classType: Class<T>): T {
        return gson.fromJson(data, classType)
    }

    override fun <T : Any> parseToJson(any: T, kClass: KClass<T>): String {
        return gson.toJson(any)
    }
}