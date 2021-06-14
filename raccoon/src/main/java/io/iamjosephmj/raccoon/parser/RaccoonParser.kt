package io.iamjosephmj.raccoon.parser

import kotlin.reflect.KClass

interface RaccoonParser {

    fun getFromString(data: String, classType: Class<*>):Any

    fun parseToJson(any: Any, kClass: KClass<Any>): String

}