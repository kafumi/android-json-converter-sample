package io.github.kafumi.jsonconvertersample.net.mock

import okhttp3.*
import java.text.SimpleDateFormat
import java.util.*

private const val CONTENT_TYPE_JSON = "application/json"

private fun message(code: Int): String = when (code) {
    200 -> "OK"
    202 -> "Accepted"
    204 -> "No Content"
    400 -> "Bad Request"
    403 -> "Forbidden"
    404 -> "Not Found"
    500 -> "Internal Server Error"
    else -> throw IllegalArgumentException("status code $code is not supported")
}

object NetworkMockUtils {
    private val dateFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US)

    init {
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")
    }

    fun response(chain: Interceptor.Chain, code: Int, body: String = "") = Response.Builder()
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .code(code)
                .message(message(code))
                .header("Content-Type", CONTENT_TYPE_JSON)
                .header("Date", dateFormat.format(Date()))
                .body(ResponseBody.create(MediaType.parse(CONTENT_TYPE_JSON), body))
                .build()
}