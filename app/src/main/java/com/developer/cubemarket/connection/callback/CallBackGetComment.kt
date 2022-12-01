package com.developer.cubemarket.connection.callback

import com.developer.cubemarket.connection.MODEL.OOP.CommentProduct
import com.developer.cubemarket.connection.MODEL.OOP.Diachi

interface CallBackGetComment {
    fun onFinish()
    fun onSuccess(cmt: CommentProduct)
    fun onFail(rs: String)
    fun onError(rs: String)
}