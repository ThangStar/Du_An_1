package com.developer.cubemarket.connection.callback

import com.developer.cubemarket.connection.MODEL.OOP.Mausac
import com.developer.cubemarket.connection.MODEL.OOP.Option

interface CallBackDataOption {
    fun onSuccess(op: Option)
    fun onFail(rs: String)
    fun onError(rs: String)
    fun onFinish(rs: String)
}