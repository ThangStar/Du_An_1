package com.developer.cubemarket.connection.callback

import com.developer.cubemarket.connection.MODEL.OOP.Kichthuoc

interface CallBackSizeProduct {
    fun onSuccess(kt: Kichthuoc)
    fun onFail(rs: String)
    fun onError(rs: String)
}