package com.developer.cubemarket.utils

interface CallBackUpdate {
    fun onSuccess(rs: String)
    fun onFail(rs: String)
    fun onError(rs: String)
}