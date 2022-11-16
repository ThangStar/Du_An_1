package com.developer.cubemarket.fragment.fragment_utils_user

import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.developer.cubemarket.R
import com.developer.cubemarket.config.user.DataUser
import com.developer.cubemarket.config.utils.Utils
import com.developer.cubemarket.connection.MODEL.DAO.DaoUser
import com.developer.cubemarket.connection.MODEL.KET_NOI_SEVER.Link
import com.developer.cubemarket.databinding.FragmentUpdateUserBinding
import com.developer.cubemarket.utils.CallBackUpdate
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.*
import java.util.regex.Pattern

class UpdateUserFragment : Fragment() {
    lateinit var binding: FragmentUpdateUserBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUpdateUserBinding.inflate(layoutInflater)
//        initDataSpinnerDirectory()
        initDataDefault()
        initEventUpdateUser()
        return binding.root
    }

    private fun initDataDefault() {
        binding.edtName.setText(DataUser.name)
        binding.edtPhone.setText(DataUser.numberPhone)
        binding.edtEmail.setText(DataUser.email)
    }

    private fun initEventUpdateUser() {
        binding.btnUpdate.setOnClickListener {
            var isCheck = true
            val name = binding.edtName.text.toString().trim()
            val number = binding.edtPhone.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()
                //update info basic user
            if(Pattern.matches(Utils.getRegexVietNam(), name)){
                binding.tilName.error = null
            }else{
                isCheck = false
                binding.tilName.error = "Tên không được để trống, không sử dụng kí tự đặc biệt"
            }

            if(Pattern.matches("^[0-9]{8,10}$", number)){
                binding.tilNumber.error = null
            }else{
                isCheck = false
                binding.tilNumber.error = "Số điện thoại không được để trống, cần 8-10 số"
            }

            if(Pattern.matches("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})\$", email)){
                binding.tilEmail.error = null
            }else{
                isCheck = false
                binding.tilEmail.error = "email định dạng @example@gmail.com"
            }
            val request = DaoUser(requireContext())
            request.update_gmail_user(DataUser.id, email)

            val callback = object: CallBackUpdate{
                override fun onSuccess(rs: String) {
                    Toasty.success(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                    DataUser.email = email
                    DataUser.name = name
                    DataUser.numberPhone = number
                }

                override fun onFail(rs: String) {
                    Toasty.warning(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                }

                override fun onError(rs: String) {
                    Toasty.error(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                }

            }
            request.update_user(
                callback,
                    DataUser.id,
                    name,
                    DataUser.occupation,
                    number
                )
                //update gmail user
        }
    }

//    private fun initDataSpinnerDirectory() {
//        val type = arrayListOf("Người dùng", "", "Người bán", "Admin")
//
//        val adapter = ArrayAdapter(
//            requireContext(),
//            R.layout.dropdown_directory_item,
//            type
//        )
//        binding.spnOccupation.adapter = adapter
//    }
}