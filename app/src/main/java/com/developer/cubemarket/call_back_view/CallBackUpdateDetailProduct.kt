package com.developer.cubemarket.call_back_view

import com.developer.cubemarket.connection.MODEL.OOP.Option

interface CallBackUpdateDetailProduct {
    fun onChangeOption(op: Option)
}