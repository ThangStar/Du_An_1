package com.developer.cubemarket.connection.callback

interface CallBackInsertUser {
    fun onSuccess(rs: String)
    fun onFail(err: String)
    fun onError(error: String)
}