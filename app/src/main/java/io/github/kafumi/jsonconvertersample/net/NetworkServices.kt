package io.github.kafumi.jsonconvertersample.net

import io.github.kafumi.jsonconvertersample.net.mock.NetworkMockInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkServices {
    private val retrofit: Retrofit by lazy {
        val client = OkHttpClient.Builder()
                .addInterceptor(NetworkMockInterceptor())
                .build()

        Retrofit.Builder()
                .client(client)
                .baseUrl("http://example.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    val valueService: ValueService by lazy {
        retrofit.create(ValueService::class.java)
    }
}
