package com.developer.cubemarket.config.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import com.bumptech.glide.request.RequestOptions
import com.developer.cubemarket.R
import com.developer.cubemarket.call_back_view.CallBackNotifiDataUi
import com.developer.cubemarket.connection.MODEL.DAO.DaoDanhMuc
import com.developer.cubemarket.connection.MODEL.DAO.DaoKhuyenMai
import com.developer.cubemarket.connection.MODEL.DAO.DaoSanPham
import com.developer.cubemarket.connection.callback.CallBackDelDirectory
import com.developer.cubemarket.connection.callback.CallBackDelProduct
import com.developer.cubemarket.connection.callback.CallBackDelVoicher
import com.developer.cubemarket.fragment.fragment_home_pager.HomeFragment
import com.developer.cubemarket.fragment.fragment_utils_product.UpdateProductFragment
import dev.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog
import es.dmoral.toasty.Toasty
import java.io.ByteArrayOutputStream
import java.text.DecimalFormat


class Utils {
    companion object{
        var alert: AlertDialog? = null
        fun formaterVND(price: Int?): String{
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
                    Log.d("SIZE: ", pos.toString())
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
                ) { dialogInterface, _ ->
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

                    val callBack = object: CallBackDelProduct {
                        override fun onSuccess(rs: String) {
                            Toasty.success(activity, rs, Toasty.LENGTH_SHORT).show()
                        }

                        override fun onFail(rs: String) {
                            Toasty.warning(activity, rs, Toasty.LENGTH_SHORT).show()
                        }

                        override fun onError(rs: String) {
                            Toasty.error(activity, rs, Toasty.LENGTH_SHORT).show()
                        }

                    }
                    DaoSanPham(activity).delete_sanpham(callBack, id)
                    dialogInterface?.dismiss()
                }
                .setNegativeButton("Hủy", R.drawable.cancel_del
                ) { dialogInterface, which ->
                    dialogInterface?.dismiss()
                }
                .build()
            mBottomSheetDialog.show()
        }
        fun getEncoded64ImageStringFromBitmap(bitmap: Bitmap): String? {
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream)
            val byteFormat = stream.toByteArray()
            // get the base 64 string
            return Base64.encodeToString(byteFormat, Base64.NO_WRAP)
        }
        fun base64ToBitMap(strBase64: String): Bitmap {
            val decodedString: ByteArray = Base64.decode(strBase64, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        }
        fun getOptionLoadImgDirectoryFromUrl(): RequestOptions {
            val options: RequestOptions = RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.load)
                .error(R.drawable.load)
            return options
        }
        fun dialogDelDirectory(callBackOnSuccess: CallBackDelDirectory, activity: Activity, title: String, message: String, id: Int, pos: Int){
            val mBottomSheetDialog: BottomSheetMaterialDialog = BottomSheetMaterialDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .setAnimation(R.raw.delete)
                .setPositiveButton("Xóa",R.drawable.delete
                ) { dialogInterface, _ ->
                    //Do something delete here
                    DaoDanhMuc(activity).delete_danhmuc(id)
                    callBackOnSuccess.onUpdateScreen()

                    dialogInterface?.dismiss()
                }
                .setNegativeButton("Hủy", R.drawable.cancel_del
                ) { dialogInterface, which ->
                    dialogInterface?.dismiss()
                }
                .build()
            mBottomSheetDialog.show()
        }
        fun getRegexVietNam(): String {
            return "^[ a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]{1,18}\$"
        }
        fun getRegexVietNam2(): String {
            return " a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s"
        }

        fun dialogDelVoicher(callbackUi: CallBackNotifiDataUi, activity: Activity, title: String, message: String, id: String?, pos: Int){
            val mBottomSheetDialog: BottomSheetMaterialDialog = BottomSheetMaterialDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .setAnimation(R.raw.delete)
                .setPositiveButton("Xóa",R.drawable.delete
                ) { dialogInterface, _ ->
                    //Do something delete here


                    val callBack = object: CallBackDelVoicher {
                        override fun onSuccess(rs: String) {
                            Toasty.success(activity, rs, Toasty.LENGTH_SHORT).show()
                            callbackUi.onSuccess()
                        }

                        override fun onFail(rs: String) {
                            Toasty.warning(activity, rs, Toasty.LENGTH_SHORT).show()
                        }

                        override fun onError(rs: String) {
                            Toasty.error(activity, rs, Toasty.LENGTH_SHORT).show()
                        }

                    }
                    DaoKhuyenMai(activity).delete_khuyenmai(id)
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