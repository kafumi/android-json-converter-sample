package io.github.kafumi.jsonconvertersample.data

import com.google.gson.annotations.SerializedName

data class NullableValuePack(
        @SerializedName("int") val intValue: Int?,
        @SerializedName("long") val longValue: Long?,
        @SerializedName("float") val floatValue: Float?,
        @SerializedName("double") val doubleValue: Double?,
        @SerializedName("string") val stringValue: String?): Validatable {

    override val isValid: Boolean
        get() = notNull(intValue, longValue, floatValue, doubleValue, stringValue)
}