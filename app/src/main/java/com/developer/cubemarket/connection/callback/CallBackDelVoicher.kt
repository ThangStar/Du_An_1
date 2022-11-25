package com.developer.cubemarket.connection.callback

interface CallBackDelVoicher {
    fun onSuccess(rs: String)
    fun onFail(rs: String)
    fun onError(rs: String)
}