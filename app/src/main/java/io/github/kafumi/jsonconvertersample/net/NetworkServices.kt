package io.github.kafumi.jsonconvertersample.net

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import io.github.kafumi.jsonconvertersample.net.mock.NetworkMockInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkServices {
    private val retrofit: Retrofit by lazy {
        val client = OkHttpClient.Builder()
                .addInterceptor(NetworkMockInterceptor())
                .build()

        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        Retrofit.Builder()
                .client(client)
                .baseUrl("http://example.com/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
    }

    val valueService: ValueService by lazy {
        retrofit.create(ValueService::class.java)
    }
}
