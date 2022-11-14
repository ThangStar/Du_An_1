package com.developer.cubemarket.config.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.developer.cubemarket.R
import com.developer.cubemarket.fragment.fragment_home_pager.HomeFragment
import com.developer.cubemarket.fragment.fragment_utils_product.UpdateProductFragment
import dev.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog
import es.dmoral.toasty.Toasty
import java.text.DecimalFormat

class Utils {

    companion object{
        var alert: AlertDialog? = null
        fun formaterVND(price: Int): String{
            val formatter = DecimalFormat("###,###,###")
            return "đ ${formatter.format(price)}"
        }
        fun resourceToBitmap(resource: Resources, res: Int): Bitmap{
            val bm = BitmapFactory.decodeResource(resource, res)
            return bm
        }
        fun dialogDelSize(activity: Activity, title: String, message: String, id: Int, pos: Int){
            val mBottomSheetDialog: BottomSheetMaterialDialog = BottomSheetMaterialDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .setAnimation(R.raw.delete)
                .setPositiveButton("Xóa",R.drawable.delete
                ) { dialogInterface, _ ->
                    //Do something delete here
                    UpdateProductFragment.arrSize.removeAt(pos)
                    UpdateProductFragment.sizeAdapter.notifyItemRemoved(pos)
                    dialogInterface?.dismiss()
                }
                .setNegativeButton("Hủy", R.drawable.cancel_del
                ) { dialogInterface, which ->
                    dialogInterface?.dismiss()
                }
                .build()
            mBottomSheetDialog.show()
        }
        fun dialogDelColor(activity: Activity, title: String, message: String, id: Int, pos: Int){
            val mBottomSheetDialog: BottomSheetMaterialDialog = BottomSheetMaterialDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .setAnimation(R.raw.delete)
                .setPositiveButton("Xóa",R.drawable.delete
                ) { dialogInterface, _ ->
                    //Do something delete here
                    UpdateProductFragment.arrColor.removeAt(pos)
                    UpdateProductFragment.colorAdapter.notifyItemRemoved(pos)
                    dialogInterface?.dismiss()
                }
                .setNegativeButton("Hủy", R.drawable.cancel_del
                ) { dialogInterface, which ->
                    dialogInterface?.dismiss()
                }
                .build()
            mBottomSheetDialog.show()
        }
        fun dialogDelProduct(activity: Activity, title: String, message: String, id: Int, pos: Int){
            val mBottomSheetDialog: BottomSheetMaterialDialog = BottomSheetMaterialDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .setAnimation(R.raw.delete)
                .setPositiveButton("Xóa",R.drawable.delete
                ) { dialogInterface, _ ->
                    //Do something delete here
                    HomeFragment.arrHomeProduct.removeAt(pos)
                    HomeFragment.productHomeAdapter.notifyItemRemoved(pos)
                    dialogInterface?.dismiss()
                }
                .setNegativeButton("Hủy", R.drawable.cancel_del
                ) { dialogInterface, which ->
                    dialogInterface?.dismiss()
                }
                .build()
            mBottomSheetDialog.show()
        }
    }
}