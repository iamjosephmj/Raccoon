package me.iamjoseph.raccoon.parser

import kotlin.reflect.KClass

interface RaccoonParser {

    fun <T : Any> getFromString(data: String, classType: Class<T>): T

    fun <T : Any> parseToJson(any: T, kClass: KClass<T>): String

}