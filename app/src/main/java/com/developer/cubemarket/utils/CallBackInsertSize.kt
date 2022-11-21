package com.developer.cubemarket.utils

interface CallBackInsertSize {
    fun onSuccess(rs: String)
    fun onFail(rs: String)
    fun onError(rs: String)
}