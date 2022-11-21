package com.developer.cubemarket.utils

interface CallBackUpdateUser {
    fun onSuccess(rs: String)
    fun onFail(rs: String)
    fun onError(rs: String)
}