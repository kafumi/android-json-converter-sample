package io.github.kafumi.jsonconvertersample.net

import io.github.kafumi.jsonconvertersample.data.NullableValuePack
import io.github.kafumi.jsonconvertersample.data.ValuePack
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ValueService {
    @GET("values/{type}")
    fun getValues(@Path("type") type: String): Call<ValuePack>

    @GET("nullable-values/{type}")
    fun getNullableValues(@Path("type") type: String): Call<NullableValuePack>
}