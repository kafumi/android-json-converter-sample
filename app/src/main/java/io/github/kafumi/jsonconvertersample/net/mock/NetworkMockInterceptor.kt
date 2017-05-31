package io.github.kafumi.jsonconvertersample.net.mock

import android.content.UriMatcher
import android.net.Uri
import okhttp3.Interceptor
import okhttp3.Response
import java.lang.IllegalArgumentException

private enum class Endpoint(val path: String, val body: String) {
    USERS_VALID("*/valid", """
        {
          "int": 123,
          "long": 2340000000,
          "float": 1.23,
          "double": 1.23,
          "string": "Lorem ipsum"
        }
    """),

    USERS_NULL_VALUES("*/null-values", """
        {
          "int": null,
          "long": null,
          "float": null,
          "double": null,
          "string": null
        }
    """),

    USERS_MISSING_PROPERTIES("*/missing-properties", """
        {
        }
    """),

    USERS_INVALID_TYPE_STRINGS("*/invalid-type-strings", """
        {
          "int": "123",
          "long": "2340000000",
          "float": "1.23",
          "double": "1.23",
          "string": "Lorem ipsum"
        }
    """),

    USERS_INVALID_TYPE_NUMBERS("*/invalid-type-numbers", """
        {
          "int": 1.23,
          "long": 1.23,
          "float": 1.23,
          "double": 1.23,
          "string": 1.23
        }
    """),
}

class NetworkMockInterceptor : Interceptor {
    companion object {
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            for (endpoint in Endpoint.values()) {
                uriMatcher.addURI("*", endpoint.path, endpoint.ordinal)
            }
        }
    }

    override fun intercept(chain: Interceptor.Chain?): Response {
        chain ?: throw IllegalArgumentException("chain must be non-null")

        val uri = Uri.parse(chain.request().url().toString())
        val ordinal = uriMatcher.match(uri)
        return when (ordinal) {
            UriMatcher.NO_MATCH ->
                NetworkMockUtils.response(chain, 404)
            else ->
                NetworkMockUtils.response(chain, 200, Endpoint.values()[ordinal].body)
        }
    }
}