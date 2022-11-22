package com.developer.cubemarket.callback

interface CallBackUpdateSize {
    fun onSuccess(rs: String)
    fun onFail(rs: String)
    fun onError(rs: String)
}