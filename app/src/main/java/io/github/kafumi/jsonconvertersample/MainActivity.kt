package io.github.kafumi.jsonconvertersample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import io.github.kafumi.jsonconvertersample.data.NullableValuePack
import io.github.kafumi.jsonconvertersample.data.ValuePack
import io.github.kafumi.jsonconvertersample.net.NetworkServices
import retrofit2.Response

fun endpointType(viewId: Int): String = when (viewId) {
    R.id.fetch_valid -> "valid"
    R.id.fetch_null_values -> "null-values"
    R.id.fetch_missing_properties -> "missing-properties"
    R.id.fetch_invalid_type_strings -> "invalid-type-strings"
    R.id.fetch_invalid_type_numbers -> "invalid-type-numbers"
    else -> throw IllegalArgumentException("unknown view ID: 0x${Integer.toHexString(viewId)}")
}

class MainActivity : AppCompatActivity() {
    @BindView(R.id.nullable_check)
    lateinit var nullableCheck: CheckBox

    @BindView(R.id.fetch_result)
    lateinit var fetchResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
    }

    @Suppress("unused")
    @OnClick(R.id.fetch_valid,
            R.id.fetch_null_values,
            R.id.fetch_missing_properties,
            R.id.fetch_invalid_type_strings,
            R.id.fetch_invalid_type_numbers)
    fun fetchValidUsers(v: View) {
        val type = endpointType(v.id)

        if (nullableCheck.isChecked) {
            showNullableValuePack(NetworkServices.valueService.getNullableValues(type).execute())
        } else {
            showValuePack(NetworkServices.valueService.getValues(type).execute())
        }
    }

    fun showValuePack(response: Response<ValuePack>) {
        val value = response.body()

        if (response.isSuccessful && value != null) {
            fetchResult.text = "$value --> ${if (value.isValid) "valid" else "invalid"}"
        } else {
            fetchResult.text = "FAIL: ${response.code()}: ${response.message()}"
        }
    }

    fun showNullableValuePack(response: Response<NullableValuePack>) {
        val value = response.body()

        if (response.isSuccessful && value != null) {
            fetchResult.text = "$value --> ${if (value.isValid) "valid" else "invalid"}"
        } else {
            fetchResult.text = "FAIL: ${response.code()}: ${response.message()}"
        }
    }
}
