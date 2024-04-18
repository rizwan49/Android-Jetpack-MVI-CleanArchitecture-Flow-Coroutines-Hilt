package com.rizwan.cinehub.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> String.fromJson(): T {
    return Gson().fromJson(this, object : TypeToken<T>() {}.type)
}