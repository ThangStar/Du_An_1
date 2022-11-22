package com.developer.cubemarket.callback

interface CallBackUpdateProduct {
    fun onSuccess(rs: String)
    fun onFail(rs: String)
    fun onError(rs: String)
}