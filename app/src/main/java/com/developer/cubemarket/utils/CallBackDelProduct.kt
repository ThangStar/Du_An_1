package com.developer.cubemarket.utils

interface CallBackDelProduct {
    fun onSuccess(rs: String)
    fun onFail(rs: String)
    fun onError(rs: String)
}