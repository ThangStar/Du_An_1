package com.developer.cubemarket.connection.callback

import com.developer.cubemarket.connection.MODEL.OOP.GioHang
import com.developer.cubemarket.connection.MODEL.OOP.Sanpham

interface CallBackSelectCart {
    fun onSuccess(gh: GioHang)
    fun onFail(rs: String)
    fun onError(rs: String)
}