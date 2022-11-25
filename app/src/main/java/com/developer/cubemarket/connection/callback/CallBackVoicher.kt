package com.developer.cubemarket.connection.callback

import com.developer.cubemarket.connection.MODEL.OOP.KhuyenMai

interface CallBackVoicher {
    fun onSuccess(km: KhuyenMai)
    fun onFail(rs: String)
    fun onError(rs: String)
}