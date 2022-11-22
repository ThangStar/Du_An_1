package com.developer.cubemarket.callback

interface CallBackInsertProduct {
    fun onSuccess(rs: String)
    fun onFail(rs: String)
    fun onError(rs: String)
}