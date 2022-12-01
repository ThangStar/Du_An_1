package com.developer.cubemarket.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.developer.cubemarket.R
import com.developer.cubemarket.config.user.DataUser
import com.developer.cubemarket.config.utils.Utils
import com.developer.cubemarket.connection.MODEL.DAO.DaoGioHang
import com.developer.cubemarket.connection.MODEL.OOP.Sanpham
import com.developer.cubemarket.connection.callback.CallBackAddCart
import com.developer.cubemarket.databinding.ProductHomeItemBinding
import com.google.android.material.transition.MaterialContainerTransform
import es.dmoral.toasty.Toasty

class ProductHomeAdapter(
    var fr: Fragment,
    var arr: ArrayList<Sanpham>
): RecyclerView.Adapter<ProductHomeAdapter.ProducHomeViewHolder>() {
    class ProducHomeViewHolder(
        var arr: ArrayList<Sanpham>,
        var fr: Fragment,
        v: View): RecyclerView.ViewHolder(v),
        View.OnLongClickListener, View.OnClickListener {
        val binding = ProductHomeItemBinding.bind(v)
        init {
            binding.lnProduct.setOnLongClickListener(this)
            binding.imvAddCart.setOnClickListener(this)

            binding.lnProduct.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("id_product",
                    arr[adapterPosition].masanpham)
                bundle.putString("img", arr[adapterPosition].img)
                bundle.putString("name", arr[adapterPosition].tensanpham)
                bundle.putString("detail", arr[adapterPosition].chitiet)
                bundle.putInt("price", arr[adapterPosition].giaban)
                bundle.putString("brand", arr[adapterPosition].nhasanxuat)
                bundle.putInt("amount", arr[adapterPosition].soluong)
                bundle.putString("directory", arr[adapterPosition].danhmuc.tendanhmuc)
                bundle.putString("rating", arr[adapterPosition].sao)
                val extra = FragmentNavigatorExtras(
                    binding.imvProduct to "IMAGE_PRODUCT",
                )
                findNavController(fr).navigate(
                    R.id.action_productFragment_to_detailProductFragment, bundle, null, extra)
            }

        }
        override fun onLongClick(p0: View?): Boolean {
            if (p0 != null) {
                when(p0.id){
                    binding.lnProduct.id -> {
                        if(DataUser.occupation == 2){
                            showMenu(fr.requireContext(), binding.imvProduct, R.menu.menu_utils)
                        }
                    }
                }
            }
            return true
        }
        fun showMenu(ctx: Context, v: View, @MenuRes menuRes: Int) {
            val popup = PopupMenu(ctx, v)
            popup.menuInflater.inflate(menuRes, popup.menu)
            popup.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.it_del ->{
                        Utils.dialogDelProduct(fr.requireActivity(), "Xóa ${arr[adapterPosition].tensanpham}?",
                        "Bạn có muốn xóa sản phẩm ${arr[adapterPosition].tensanpham}", arr[adapterPosition].masanpham, adapterPosition)
                    }
                    R.id.it_edit ->{
                        val bundle = Bundle()
                        bundle.putInt("id_product",
                            arr[adapterPosition].masanpham)
                        bundle.putString("name_product",
                            arr[adapterPosition].tensanpham)
                        bundle.putString("name_directory",
                        arr[adapterPosition].danhmuc.tendanhmuc)
                        bundle.putString("img_product",
                            arr[adapterPosition].img)
                        bundle.putInt("price",
                            arr[adapterPosition].giaban)
                        bundle.putInt("amount",
                            arr[adapterPosition].soluong)
                        bundle.putString("brand",
                            arr[adapterPosition].nhasanxuat)
                        bundle.putString("detail",
                            arr[adapterPosition].chitiet)
                        bundle.putInt("id_directory",
                            arr[adapterPosition].danhmuc.madanhmuc)
                        Log.d("MADANHMUC", arr[adapterPosition].danhmuc.madanhmuc.toString())
                        bundle.putString("location_directory",
                            arr[adapterPosition].danhmuc.khuvuc)
                        bundle.putString("img_directory",
                            arr[adapterPosition].danhmuc.img)
                        //start edit product
                        fr.findNavController().
                        navigate(R.id.action_productFragment_to_updateProductFragment,
                        bundle)
                    }
                }
                true
            }
            // Show the popup menu.
            popup.show()
        }

        override fun onClick(p0: View?) {
            when(p0){
                binding.imvAddCart ->{
                    val callBackAddCart = object : CallBackAddCart{
                        override fun onSuccess(rs: String) {
                            Toasty.success(fr.requireContext(), "Đã thêm vào giỏ", Toasty.LENGTH_SHORT).show()
                        }

                        override fun onFail(err: String) {
                            Toasty.warning(fr.requireContext(), err, Toasty.LENGTH_SHORT).show()
                        }

                        override fun onError(error: String) {
                            Toasty.error(fr.requireContext(), error, Toasty.LENGTH_SHORT).show()
                        }
                    }
                    DaoGioHang(fr.requireContext()).insert_giohang(callBackAddCart,
                        DataUser.id,
                        5
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProducHomeViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.product_home_item, parent, false);
        return ProducHomeViewHolder(arr, fr, v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProducHomeViewHolder, position: Int) {
        val pr = arr[position]
        try{
            val option = Utils.getOptionLoadImgDirectoryFromUrl()
            Glide.with(fr.requireContext()).load(pr.img).apply(option).into(holder.binding.imvProduct);
        }catch (_: java.lang.Exception){

        }

        holder.binding.tvPrice.text = Utils.formaterVND(pr.giaban)
        holder.binding.tvTitle.text = pr.tensanpham
        holder.binding.imvProduct.transitionName = "IMAGE_PRODUCT_${pr.masanpham}"
        holder.binding.rbProduct.rating = pr.sao.toFloat()
        holder.binding.ratingText.text = "${pr.sao.toFloat()}/5"
        holder.binding.tvAmountSold.text = "Đã bán ${pr.soluongban}"
    }

    override fun getItemCount(): Int {
        return arr.size
    }

}