package com.shineshiao.endeavour.base

import retrofit2.Response

/**
 * Created by thach.nguyen on 13,10,2022
 */

abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): BaseNetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return BaseNetworkResult.Success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorMessage: String): BaseNetworkResult<T> =
        BaseNetworkResult.Error("Api call failed $errorMessage")
}
