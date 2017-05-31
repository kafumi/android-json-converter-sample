package io.github.kafumi.jsonconvertersample.data

import com.squareup.moshi.Json

data class ValuePack(
        @field:Json(name = "int") val intValue: Int,
        @field:Json(name = "long") val longValue: Long,
        @field:Json(name = "float") val floatValue: Float,
        @field:Json(name = "double") val doubleValue: Double,
        @field:Json(name = "string") val stringValue: String)