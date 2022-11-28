package com.developer.cubemarket.connection.callback

import com.developer.cubemarket.connection.MODEL.OOP.Diachi

interface CallBackGetAddress {
    fun onSuccess(dc: Diachi)
    fun onFail(rs: String)
    fun onError(rs: String)
}