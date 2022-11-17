package com.developer.cubemarket.utils

interface CallBackInsertProduct {
    fun onSuccess(rs: String)
    fun onFail(rs: String)
    fun onError(rs: String)
}