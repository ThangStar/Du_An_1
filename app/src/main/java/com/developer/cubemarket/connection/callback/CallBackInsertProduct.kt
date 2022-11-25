package com.developer.cubemarket.connection.callback

interface CallBackInsertProduct {
    fun onSuccess(rs: String)
    fun onFail(rs: String)
    fun onError(rs: String)
}