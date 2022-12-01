package com.developer.cubemarket.connection.callback

interface CallBackInsertCmt {
    fun onSuccess()
    fun onFail(rs: String)
    fun onError(rs: String)
}