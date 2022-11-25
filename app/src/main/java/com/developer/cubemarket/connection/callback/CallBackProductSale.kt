package com.developer.cubemarket.connection.callback

import com.developer.cubemarket.connection.MODEL.OOP.Sanpham

interface CallBackProductSale {
    fun onSuccess(sp: Sanpham)
    fun onFail(err: String)
    fun onError(err: String)
}