package com.developer.cubemarket.`object`.checkout

import android.graphics.Bitmap

data class ProductCheckOut(
    var image: Bitmap,
    var name: String,
    var info: String,
    var price: Int,
    var amount: Int
)