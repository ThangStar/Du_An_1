package com.developer.cubemarket.connection.callback

interface CallBackInsertSize {
    fun onSuccess(rs: String)
    fun onFail(rs: String)
    fun onError(rs: String)
}