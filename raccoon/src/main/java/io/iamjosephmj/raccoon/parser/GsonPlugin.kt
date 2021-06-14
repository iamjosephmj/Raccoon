package io.iamjosephmj.raccoon.parser

import com.google.gson.Gson
import kotlin.reflect.KClass

class GsonPlugin : RaccoonParser {

    private val gson by lazy {
        Gson()
    }

    override fun getFromString(data: String, classType: Class<*>): Any {
        return gson.fromJson(data, classType)
    }

    override fun parseToJson(any: Any, kClass: KClass<Any>): String {
        return gson.toJson(any)
    }
}