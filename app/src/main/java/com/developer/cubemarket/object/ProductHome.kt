package com.developer.cubemarket.`object`

import android.graphics.Bitmap

data class ProductHome(
    var idProduct: Int,
    var idDirectory: Int,
    var brand: String,
    var amount: Int,
    var detail: String,
    var imgProduct: String,
    var nameProduct: String,
    var priceProduct: Int
)