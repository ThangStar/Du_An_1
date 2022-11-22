package com.developer.cubemarket.callback

interface CallBackDeleteSize {
    fun onSuccess(rs: String)
    fun onFail(rs: String)
    fun onError(rs: String)
}