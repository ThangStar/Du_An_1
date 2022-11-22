package com.developer.cubemarket.callback

interface CallBackDeleteColor {
    fun onSuccess(rs: String)
    fun onFail(rs: String)
    fun onError(rs: String)
}