package com.developer.cubemarket.connection.callback

interface CallBackDelDirectory {
    fun onUpdateScreen()
    fun onFail(rs: String)
    fun onError(rs: String)
}