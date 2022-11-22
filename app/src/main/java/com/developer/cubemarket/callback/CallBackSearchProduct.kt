package com.developer.cubemarket.callback

import com.developer.cubemarket.connection.MODEL.OOP.Sanpham

interface CallBackSearchProduct {
    fun onSuccess(sp: Sanpham)
    fun onFail(rs: String)
    fun onError(rs: String)
    fun onFinish()
}