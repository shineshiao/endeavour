package com.shineshiao.endeavour.base

/**
 * Created by thach.nguyen on 17,08,2021
 * Using to parse respone of coroutines
 */
sealed class BaseNetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : BaseNetworkResult<T>(data)
    class Error<T>(message: String, data: T? = null) : BaseNetworkResult<T>(data, message)
    class Loading<T> : BaseNetworkResult<T>()
}
