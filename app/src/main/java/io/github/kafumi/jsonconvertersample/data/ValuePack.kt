package io.github.kafumi.jsonconvertersample.data

import com.google.gson.annotations.SerializedName

data class ValuePack(
        @SerializedName("int") val intValue: Int,
        @SerializedName("long") val longValue: Long,
        @SerializedName("float") val floatValue: Float,
        @SerializedName("double") val doubleValue: Double,
        @SerializedName("string") val stringValue: String)