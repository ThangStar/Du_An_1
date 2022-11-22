package com.developer.cubemarket.callback

interface CallBackDelProduct {
    fun onSuccess(rs: String)
    fun onFail(rs: String)
    fun onError(rs: String)
}