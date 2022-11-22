package com.developer.cubemarket.callback

interface CallBackInsertSize {
    fun onSuccess(rs: String)
    fun onFail(rs: String)
    fun onError(rs: String)
}