package com.developer.cubemarket.connection.callback

interface CallBackGetCode {
    fun onSuccess(code: String)
    fun onFail(err: String)
    fun onError(error: String)
}