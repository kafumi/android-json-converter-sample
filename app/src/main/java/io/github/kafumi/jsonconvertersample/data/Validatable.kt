package io.github.kafumi.jsonconvertersample.data

interface Validatable {
    val isValid: Boolean
}

fun notNull(vararg vs: Any?): Boolean {
    for (v in vs) {
        if (v === null) {
            return false
        }
    }
    return true
}