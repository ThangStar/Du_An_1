package com.developer.cubemarket.connection.callback

interface CallBackChangePass {
    fun onSuccess(rs: String)
    fun onFail(err: String)
    fun onError(error: String)
}