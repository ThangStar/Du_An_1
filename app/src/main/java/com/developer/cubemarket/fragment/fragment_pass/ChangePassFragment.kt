package com.developer.cubemarket.fragment.fragment_pass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.cubemarket.config.user.DataUser
import com.developer.cubemarket.connection.MODEL.DAO.DaoUser
import com.developer.cubemarket.connection.callback.CallBackChangePass
import com.developer.cubemarket.databinding.FragmentChangePassBinding
import es.dmoral.toasty.Toasty
import java.util.regex.Pattern

class ChangePassFragment : Fragment() {

    lateinit var binding: FragmentChangePassBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChangePassBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        initEventUpChangePass()
        return binding.root
    }

    private fun initEventUpChangePass() {
        binding.btnChangePass.setOnClickListener {
            var isCheck = true
            val passOld = binding.edtPassOld.text.toString().trim()
            val passNew = binding.edtPassNew.text.toString().trim()
            val passNewAgain = binding.edtPassNewAgain.text.toString().trim()


            if (Pattern.matches("^[a-zA-Z0-9]{5,10}$", passOld)){
                binding.tilPassOld.error = null
            }else{
                isCheck = false
                binding.tilPassOld.error = "Mật khẩu cũ cần 5-10 kí tự, không có kí tự đặc biệt"
            }

            if (Pattern.matches("^[a-zA-Z0-9]{5,10}$", passNew)){
                binding.tilPassNew.error = null
            }else{
                isCheck = false
                binding.tilPassNew.error = "Mật khẩu mới cần 5-10 kí tự, không có kí tự đặc biệt"
            }

            if (Pattern.matches("^[a-zA-Z0-9]{5,10}$", passNewAgain)){
                binding.tilPassNewAgain.error = null
            }else{
                isCheck = false
                binding.tilPassNewAgain.error = "Nhập lại mật khẩu mới cần 5-10 kí tự, không có kí tự đặc biệt"
            }
            if (passNew != passNewAgain){
                isCheck = false
                binding.tilPassNewAgain.error = "mật khẩu mới không giống nhau"
            }else{
                binding.tilPassNewAgain.error = null
            }
            if (passOld != DataUser.pass){
                binding.tilPassOld.error = "mật khẩu cũ không chính xác"
                isCheck = false
            }else{
                binding.tilPassOld.error = null
            }
            if(isCheck){
                val callback = object: CallBackChangePass {
                    override fun onSuccess(rs: String) {
                        Toasty.success(requireContext(),
                            rs, Toasty.LENGTH_SHORT).show()
                    }

                    override fun onFail(err: String) {
                        Toasty.warning(requireContext(),
                            err, Toasty.LENGTH_SHORT).show()
                    }

                    override fun onError(error: String) {
                        Toasty.error(requireContext(),
                            error, Toasty.LENGTH_SHORT).show()
                    }

                }
                DaoUser(requireContext()).update_pass_user(callback, DataUser.id, passOld, passNew)
            }

        }

    }
}