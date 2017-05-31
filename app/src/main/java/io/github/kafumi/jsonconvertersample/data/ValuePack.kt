package io.github.kafumi.jsonconvertersample.data

import com.google.gson.annotations.SerializedName

data class ValuePack(
        @SerializedName("int") val intValue: Int,
        @SerializedName("long") val longValue: Long,
        @SerializedName("float") val floatValue: Float,
        @SerializedName("double") val doubleValue: Double,
        @SerializedName("string") val stringValue: String): Validatable {

    constructor() : this(
            intValue = Int.MIN_VALUE,
            longValue = Long.MIN_VALUE,
            floatValue = Float.NaN,
            doubleValue = Double.NaN,
            stringValue = "")

    override val isValid: Boolean
        get() = intValue != Int.MIN_VALUE &&
                longValue != Long.MIN_VALUE &&
                !floatValue.isNaN() &&
                !doubleValue.isNaN() &&
                !stringValue.isNullOrEmpty()
}