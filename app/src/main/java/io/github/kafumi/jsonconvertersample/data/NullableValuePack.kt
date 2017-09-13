package io.github.kafumi.jsonconvertersample.data

import com.squareup.moshi.Json

data class NullableValuePack(
        @Json(name = "int") val intValue: Int?,
        @Json(name = "long") val longValue: Long?,
        @Json(name = "float") val floatValue: Float?,
        @Json(name = "double") val doubleValue: Double?,
        @Json(name = "string") val stringValue: String?): Validatable {

    override val isValid: Boolean
        get() = notNull(intValue, longValue, floatValue, doubleValue, stringValue)
}