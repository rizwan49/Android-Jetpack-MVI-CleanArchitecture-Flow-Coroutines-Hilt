package com.rizwan.cinehub.domain

/**
 * this sealed class will help to identify the process or request status.
 */


sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: String) : Result<Nothing>()
}