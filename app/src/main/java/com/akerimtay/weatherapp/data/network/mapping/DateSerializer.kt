package com.akerimtay.weatherapp.data.network.mapping

import com.google.gson.*
import java.lang.reflect.Type
import java.util.*

class DateSerializer : JsonSerializer<Date>, JsonDeserializer<Date> {
    override fun serialize(src: Date?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(src?.time)
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Date? {
        val timestamp = json!!.asLong
        return if (timestamp > 0) Date(timestamp * 1000) else null
    }
}