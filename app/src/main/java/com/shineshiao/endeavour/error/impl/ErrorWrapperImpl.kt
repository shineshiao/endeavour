package com.shineshiao.endeavour.error.impl

import com.shineshiao.endeavour.error.ErrorWrapper
import javax.inject.Inject

class ErrorWrapperImpl @Inject constructor() : ErrorWrapper {
    override fun handleError(e: Throwable): Throwable {
        return e
    }
}
