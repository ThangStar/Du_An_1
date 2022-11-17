package com.developer.cubemarket.utils

import com.developer.cubemarket.`object`.ProductHome
import com.developer.cubemarket.connection.MODEL.OOP.Sanpham

interface CallBackProduct {
    fun onSuccess(sp: Sanpham)
    fun onFail(err: String)
    fun onError(err: String)

}