package com.developer.cubemarket.utils

interface CallBackChangePass {
    fun onSuccess(rs: String)
    fun onFail(err: String)
    fun onError(error: String)
}