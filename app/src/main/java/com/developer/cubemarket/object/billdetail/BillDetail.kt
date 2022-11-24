package com.developer.cubemarket.`object`.billdetail

import android.graphics.Bitmap

data class BillDetail(
    var img: Bitmap,
    var name: String,
    var sl: Int,
    var size: String,
    var color: String,
    var price: Int
)