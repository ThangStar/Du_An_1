package com.developer.cubemarket.utils

interface CallBackUpdateSize {
    fun onSuccess(rs: String)
    fun onFail(rs: String)
    fun onError(rs: String)
}