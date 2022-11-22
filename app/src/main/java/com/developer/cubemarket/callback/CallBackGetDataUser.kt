package com.developer.cubemarket.callback

import com.developer.cubemarket.connection.MODEL.OOP.Sanpham
import com.developer.cubemarket.connection.MODEL.OOP.User

interface CallBackGetDataUser {
    fun onSuccess(sp: User)
    fun onFail(err: String)
    fun onError(err: String)
}