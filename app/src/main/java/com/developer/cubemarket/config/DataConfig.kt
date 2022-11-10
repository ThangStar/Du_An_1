package com.developer.cubemarket.config

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.developer.cubemarket.R
import java.text.DecimalFormat

class DataConfig {
    companion object{
        fun formaterVND(price: Int): String{
            val formatter = DecimalFormat("###,###,###")
            return "đ ${formatter.format(price)}"
        }
        fun resourceToBitmap(resource: Resources, res: Int): Bitmap{
            val bm = BitmapFactory.decodeResource(resource, res)
            return bm
        }
    }
}