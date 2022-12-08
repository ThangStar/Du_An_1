package com.developer.cubemarket.adapter.manager

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.developer.cubemarket.R
import com.developer.cubemarket.config.user.DataUser
import com.developer.cubemarket.connection.MODEL.DAO.DaoUser
import com.developer.cubemarket.connection.MODEL.OOP.User
import com.developer.cubemarket.connection.callback.CallBackLockAccount
import com.developer.cubemarket.databinding.UserManagerItemBinding
import com.developer.cubemarket.fragment.bottom_sheet.BtsChangePermissionUserFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import es.dmoral.toasty.Toasty

class UserManagerAdapter(
    var fr: FragmentActivity,
    var arr: ArrayList<User>
): RecyclerView.Adapter<UserManagerAdapter.UserManagerViewHolder>() {
    class UserManagerViewHolder(
        v: View,
        private var fr: FragmentActivity,
        var arr: ArrayList<User>
    ): RecyclerView.ViewHolder(v), View.OnClickListener {
        val binding = UserManagerItemBinding.bind(v)
        init {
            binding.btnChangePermission.setOnClickListener(this)
            binding.btnLock.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            when(p0!!){
                binding.btnChangePermission -> {
                    val bottomSheet = BtsChangePermissionUserFragment(arr[adapterPosition].id, arr[adapterPosition].chucvu)
                    bottomSheet.show(fr.supportFragmentManager, BtsChangePermissionUserFragment.TAG)
                }
                binding.btnLock -> {
                    if (arr[adapterPosition].tinhtrang == 0){
                        val buider = MaterialAlertDialogBuilder(fr)
                            .setTitle("KHÓA ${arr[adapterPosition].ten}?")
                            .setMessage("Bạn có muốn khóa tài khoản ${arr[adapterPosition].ten}")
                            .setNegativeButton("Khóa", object : DialogInterface.OnClickListener{
                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                    val callBack = object : CallBackLockAccount{
                                        override fun onSuccess(rs: String) {
                                            arr[adapterPosition].tinhtrang = 1
                                            binding.btnLock.setBackgroundColor(fr.resources.getColor(R.color.green, null))
                                            binding.btnLock.text = "MỞ KHÓA"
                                            Toasty.success(fr.baseContext, rs, Toasty.LENGTH_SHORT).show()
                                        }

                                        override fun onFail(rs: String) {
                                            Toasty.warning(fr.baseContext, rs, Toasty.LENGTH_SHORT).show()
                                        }

                                        override fun onError(rs: String) {
                                            Toasty.error(fr.baseContext, rs, Toasty.LENGTH_SHORT).show()
                                        }
                                    }
                                    DaoUser(fr.baseContext).khoa_taikhoan(callBack, arr[adapterPosition].id, DataUser.occupation)
                                }
                            })
                            .setPositiveButton("Hủy", object : DialogInterface.OnClickListener{
                                override fun onClick(p0: DialogInterface?, p1: Int) {

                                }
                            })
                            .show()
                    }else{
                        val buider = MaterialAlertDialogBuilder(fr)
                            .setTitle("MỞ KHÓA ${arr[adapterPosition].ten}?")
                            .setMessage("Bạn có muốn mở khóa tài khoản ${arr[adapterPosition].ten}")
                            .setNegativeButton("Mở khóa", object : DialogInterface.OnClickListener{
                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                    val callBack = object : CallBackLockAccount{
                                        override fun onSuccess(rs: String) {
                                            arr[adapterPosition].tinhtrang = 0
                                            binding.btnLock.text = "KHÓA"
                                            binding.btnLock.setBackgroundColor(fr.resources.getColor(R.color.item_color_secondary, null))
                                            Toasty.success(fr.baseContext, rs, Toasty.LENGTH_SHORT).show()
                                        }

                                        override fun onFail(rs: String) {
                                            Toasty.warning(fr.baseContext, rs, Toasty.LENGTH_SHORT).show()
                                        }

                                        override fun onError(rs: String) {
                                            Toasty.error(fr.baseContext, rs, Toasty.LENGTH_SHORT).show()
                                        }
                                    }
                                    DaoUser(fr.baseContext).mo_taikhoan(callBack, arr[adapterPosition].id, DataUser.occupation)
                                }
                            })
                            .setPositiveButton("Hủy", object : DialogInterface.OnClickListener{
                                override fun onClick(p0: DialogInterface?, p1: Int) {

                                }
                            })
                            .show()
                    }

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserManagerViewHolder {
        val bind = LayoutInflater.from(parent.context).inflate(R.layout.user_manager_item, parent, false)
        return UserManagerViewHolder(bind, fr, arr)
    }

    override fun onBindViewHolder(holder: UserManagerViewHolder, position: Int) {
        val us = arr[position]
        holder.binding.tvUsername.text = us.ten
        holder.binding.tvEmail.text = us.gmail
        if (us.tinhtrang == 0){
            holder.binding.btnLock.text = "KHÓA"
            holder.binding.btnLock.setBackgroundColor(fr.resources.getColor(R.color.item_color_secondary, null))
        }else{
            holder.binding.btnLock.setBackgroundColor(fr.resources.getColor(R.color.green, null))
            holder.binding.btnLock.text = "MỞ KHÓA"
        }
        var cv = "Không xác định!"
        when(us.chucvu){
            0 -> cv = "Người dùng"
            1 -> cv = "Người bán"
            2 -> cv = "Quản trị viên"
        }
        holder.binding.tvPermission.text = "Chức vụ: $cv"
    }

    override fun getItemCount(): Int {
        return arr.size
    }
}