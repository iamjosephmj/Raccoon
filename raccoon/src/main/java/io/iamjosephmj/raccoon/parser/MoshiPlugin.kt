package io.iamjosephmj.raccoon.parser

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.iamjosephmj.raccoon.exception.ParseException
import kotlin.reflect.KClass

class MoshiPlugin : RaccoonParser {

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    override fun <T : Any> getFromString(data: String, classType: Class<T>): T {
        val adapter = moshi.adapter(classType)
        return adapter.fromJson(data) ?: throw ParseException()
    }

    override fun <T : Any> parseToJson(any: T, kClass: KClass<T>): String {
        return moshi.adapter(kClass.java).toJson(any) ?: throw ParseException()
    }
}