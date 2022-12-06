package com.developer.cubemarket.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.developer.cubemarket.R
import com.developer.cubemarket.config.share_references.DataShareReferences
import com.developer.cubemarket.connection.MODEL.DAO.DaoUser
import com.developer.cubemarket.connection.MODEL.OOP.User
import com.developer.cubemarket.connection.callback.CallBackGetCode
import com.developer.cubemarket.connection.callback.CallBackInsertUser
import com.developer.cubemarket.connection.callback.CallBackLogin
import com.developer.cubemarket.databinding.FragmentLoginBinding
import com.developer.cubemarket.service.SendMailService
import com.google.android.material.textfield.TextInputLayout
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.*
import java.util.*
import java.util.regex.Pattern

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var ctx: Context
    var isExit = false
    private var isLogin = true
    private var isForgotPass = false
    private lateinit var animEdtOut: Animation
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        ctx = binding.root.context

        initDataDefault()
        initEventLogin()
        initGoLogin()

        initForgotPass()


        initAnimation(binding.tvGoRegister)
        initAnimation(binding.lnClose)

        return binding.root
    }

    private fun initForgotPass() {
        binding.tvGoForgot.setOnClickListener {
            isForgotPass = true
            binding.tvTitle.text = "Quên Mật Khẩu"
            binding.btnOk.text = "Gửi mã"

            binding.edtA.hint = "Email"
            binding.iplB.visibility = View.INVISIBLE
            binding.iplC.visibility = View.INVISIBLE
            binding.iplD.visibility = View.INVISIBLE

            binding.rlInLogin.visibility = View.GONE

        }
    }

    private fun initGoLogin() {
        binding.tvGoLogin.setOnClickListener {
            isForgotPass = false
            binding.btnOk.text = "Đăng nhập"
            binding.tvTitle.text = "Đăng Nhập"
            binding.edtB.hint = "Mật khẩu"
            binding.iplB.visibility = View.VISIBLE
            binding.iplC.visibility = View.INVISIBLE
            binding.iplD.visibility = View.INVISIBLE

            binding.lnGoLogin.visibility = View.GONE

            binding.rlInLogin.visibility = View.VISIBLE
            animEdtOut = AnimationUtils.loadAnimation(ctx, R.anim.anim_edt_out)

            binding.iplA.startAnimation(animEdtOut)
            binding.iplB.startAnimation(animEdtOut)
            binding.iplC.startAnimation(animEdtOut)
            binding.iplD.startAnimation(animEdtOut)
        }
    }


    private fun initDataDefault() {
        val auth = DataShareReferences.getEmailAndPass(requireContext())
        binding.edtA.setText(auth.email)
        binding.edtB.setText(auth.pass)
    }

    private fun initAnimation(v: View) {
        animEdtOut = AnimationUtils.loadAnimation(ctx, R.anim.anim_edt_out)
        v.setOnClickListener {
            isForgotPass = false
            binding.edtA.error = null
            binding.edtB.error = null
            binding.edtC.error = null
            binding.edtD.error = null
            isLogin = !isLogin

            binding.iplA.startAnimation(animEdtOut)
            binding.iplB.startAnimation(animEdtOut)
            binding.iplC.startAnimation(animEdtOut)
            binding.iplD.startAnimation(animEdtOut)

            binding.edtA.setText("")
            binding.edtB.setText("")
            binding.edtC.setText("")
            binding.edtD.setText("")

            if (isLogin){
                initDataDefault()

                //this is login
                binding.edtB.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.iplB.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
                binding.iplB.setStartIconDrawable(R.drawable.ic_change_pass)
                binding.btnOk.text = "Đăng nhập"
                binding.tvTitle.text = "Đăng Nhập"
                binding.edtB.hint = "Mật khẩu"
                binding.iplB.visibility = View.VISIBLE
                binding.iplC.visibility = View.INVISIBLE
                binding.iplD.visibility = View.INVISIBLE
                binding.edtB.inputType = InputType.TYPE_CLASS_TEXT

                binding.lnGoLogin.visibility = View.GONE

                binding.rlInLogin.visibility = View.VISIBLE
            }else{
                //this is register
                binding.iplB.visibility = View.VISIBLE
                binding.tvTitle.text = "Đăng Kí"
                binding.btnOk.text = "Đăng kí"
                binding.iplB.endIconMode = TextInputLayout.END_ICON_CLEAR_TEXT
                binding.iplB.setStartIconDrawable(R.drawable.phone)

                binding.edtB.inputType = InputType.TYPE_CLASS_NUMBER
                binding.iplB.startIconDrawable
                binding.edtB.hint = "Số điện thoại"
                binding.iplC.visibility = View.VISIBLE
                binding.iplD.visibility = View.VISIBLE
                binding.lnGoLogin.visibility = View.VISIBLE
                binding.rlInLogin.visibility = View.GONE
            }
        }
    }

    private fun initEventLogin() {
        binding.btnOk.setOnClickListener {
            var isCheck = true
            when(isLogin){
                false -> {
                    if (isForgotPass){
                        initGoForgotPass()
                    }else{
                        val email = binding.edtA.text.toString().trim()
                        val numberPhone = binding.edtB.text.toString().trim()
                        val fullname = binding.edtC.text.toString().trim()
                        val pass = binding.edtD.text.toString().trim()
                        if (email.isBlank()){
                            isCheck = false
                            binding.edtA.error = "Email không được để trống"
                        }else{
                            binding.edtA.error = null

                        }
                        if (numberPhone.isBlank()){
                            isCheck = false
                            binding.edtB.error = "Số điện thoại không được để trống"
                        }else{
                            binding.edtB.error = null

                        }


                        if (fullname.isBlank()){
                            isCheck = false
                            binding.edtC.error = "Họ tên không được để trống"
                        }else{
                            binding.edtC.error = null

                        }


                        if (pass.isBlank()){
                            isCheck = false
                            binding.edtD.error = "Mật khẩu không được để trống"
                        }else{
                            binding.edtD.error = null

                        }

                        if(isCheck){
                            val callBack = object : CallBackInsertUser{
                                override fun onSuccess(rs: String) {
                                    Toasty.success(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                                }

                                override fun onFail(err: String) {
                                    Toasty.warning(requireContext(), err, Toasty.LENGTH_SHORT).show()
                                }

                                override fun onError(error: String) {
                                    Toasty.error(requireContext(), error, Toasty.LENGTH_SHORT).show()
                                }
                            }
                            val user = User(0, fullname, pass, 0, numberPhone, email)
                            DaoUser(ctx).insert_user(callBack, user)
                        }
                    }
                }
                true -> {
                    if (isForgotPass){
                        initGoForgotPass()
                    }else{
                        var isCheck = true
                        val email = binding.edtA.text.toString().trim()
                        val pass = binding.edtB.text.toString().trim()
                        if(email.isBlank()){
                            isCheck = false
                            binding.edtA.error = "Địa chỉ email không được để trống"
                        } else{
                            binding.edtA.error = null
                        }

                        if(pass.isBlank()){
                            isCheck = false
                            binding.edtB.error = "Mật khẩu không được để trống"
                        } else{
                            binding.edtB.error = null
                        }

                        if (isCheck){
                            val callBackLogin = object : CallBackLogin {
                                override fun onSuccess(rs: String) {
//                                Utils.diaLogProgress(requireContext(), "Đang đăng nhập", R.raw.loading)
                                    findNavController().navigate(R.id.action_loginFragment_to_productFragment)
                                    Toasty.success(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                                }

                                override fun onFail(rs: String) {
                                    Toasty.warning(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                                }

                                override fun onError(rs: String) {
                                    Toasty.error(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                                }
                            }
                            DaoUser(ctx).dangnhap(callBackLogin, email, pass)
                        }
                    }
                }
            }
        }
    }

    private fun initGoForgotPass() {
        val email = binding.edtA.text.toString()
        if (isValidEmail(email)){
            val callback = object : CallBackGetCode{
                override fun onSuccess(code: String) {

                    //SEND EMAIL
                    val javaMailAPI = SendMailService(
                        requireContext(), email,
                        "KHÔI PHỤC MẬT KHẨU", "$code Là mã xác minh CubeApp của bạn."
                    )
                    javaMailAPI.execute()

                    //GO CHECK OUT CODE
                    val bd = Bundle()
                    bd.putString("CODE", code)
                    bd.putString("EMAIL", email)
                    findNavController().navigate(R.id.action_loginFragment_to_forgotPassFragment,
                        bd)
                }

                override fun onFail(err: String) {
                    Toasty.warning(requireContext(), err, Toasty.LENGTH_SHORT).show()
                }

                override fun onError(error: String) {
                    Toasty.error(requireContext(), error, Toasty.LENGTH_SHORT).show()
                }
            }
            DaoUser(requireContext()).layma(callback, email)


        }
    }

    fun isValidEmail(email: String): Boolean {
        val regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$"
        return Pattern.matches(regex, email)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        class blockGoBack : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Toasty.warning(ctx, "Quay lại lần nữa để thoát", Toasty.LENGTH_SHORT).show()
                val job1 = GlobalScope.launch {
                    isExit = true
                    withContext(Dispatchers.IO) {
                        Thread.sleep(2000)
                    }
                    isExit = false
                    cancel()
                }
                if (isExit){
                    System.exit(0)
                }

            }
        }
        requireActivity().getOnBackPressedDispatcher().addCallback(
            this, blockGoBack());
    }
}