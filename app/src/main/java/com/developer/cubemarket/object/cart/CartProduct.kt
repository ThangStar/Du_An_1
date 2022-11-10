package com.developer.cubemarket.`object`.cart

import android.graphics.Bitmap

data class CartProduct(
     var img: Bitmap,
     var name: String,
     var info: String,
     var price: Int
)