package io.iamjosephmj.raccoon.parser

import com.squareup.moshi.Moshi
import io.iamjosephmj.raccoon.exception.ParseException
import kotlin.reflect.KClass

class MoshiPlugin : RaccoonParser {

    private val moshi: Moshi = Moshi.Builder().build()

    override fun getFromString(data: String, classType: Class<*>): Any {
        val adapter = moshi.adapter(classType)
        return adapter.fromJson(data) ?: throw ParseException()
    }

    override fun parseToJson(any: Any, kClass: KClass<Any>): String {
        return moshi.adapter(kClass.java).toJson(any) ?: throw ParseException()
    }

}