package com.shineshiao.endeavour.error

interface ErrorWrapper {
    fun handleError(e: Throwable): Throwable
}
