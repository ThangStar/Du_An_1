package com.developer.cubemarket.connection.callback

import com.developer.cubemarket.connection.MODEL.OOP.User

interface CallBackInsertAddress {
    fun onSuccess(rs: String)
    fun onFail(err: String)
    fun onError(err: String)
}