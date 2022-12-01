package com.developer.cubemarket.fragment.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.developer.cubemarket.R
import com.developer.cubemarket.connection.MODEL.DAO.DaoUser
import com.developer.cubemarket.connection.callback.CallBackUpdatePermissionUser
import com.developer.cubemarket.databinding.FragmentBtsChangePermissionUserBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import es.dmoral.toasty.Toasty

class BtsChangePermissionUserFragment(
    private var idCurrentUser: Int,
    private var occupationCurrentUser: Int
) : BottomSheetDialogFragment(), View.OnClickListener {
    lateinit var binding : FragmentBtsChangePermissionUserBinding
    val items = listOf("Người dùng", "Người bán", "Quản trị viên")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = FragmentBtsChangePermissionUserBinding.inflate(layoutInflater)
        initDataPermission()
        initEvent()
        return binding.root
    }

    private fun initEvent() {
        binding.btnUpdate.setOnClickListener(this)
        binding.btnDismiss.setOnClickListener(this)

    }

    private fun initDataPermission() {
        val adapter = ArrayAdapter(requireContext(), R.layout.list_permission_item, items)
        (binding.atcPermission as? AutoCompleteTextView)?.setAdapter(adapter)
        when(occupationCurrentUser){
            0 -> binding.atcPermission.setText(items[0], false);
            1 -> binding.atcPermission.setText(items[1], false);
            2 -> binding.atcPermission.setText(items[2], false);

        }
    }

    companion object {
        const val TAG = "BtsChangePermissionUser"
    }

    override fun onClick(p0: View?) {
        var index = 0
        when(p0){
            binding.btnUpdate -> {
                for (i in items.indices){
                    if(binding.atcPermission.text.toString() == items[i]){
                        index = i
                        break
                    }
                }
                val callBackUpdate = object : CallBackUpdatePermissionUser {
                    override fun onSuccess(context: BtsChangePermissionUserFragment, rs: String) {
                        Toasty.success(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                        context.dismiss()
                    }

                    override fun onFail(rs: String) {
                        Toasty.warning(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                    }

                    override fun onError(rs: String) {
                        Toasty.error(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                    }
                }
                DaoUser(requireContext()).update_chucvu_user(this, callBackUpdate, idCurrentUser, index)
            }
            binding.btnDismiss -> {
                this.dismiss()
            }
        }
    }
}