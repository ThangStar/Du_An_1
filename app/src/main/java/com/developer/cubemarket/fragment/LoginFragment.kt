package com.developer.cubemarket.fragment

import android.content.Context
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.OnBackPressedCallback
import com.developer.cubemarket.R
import com.developer.cubemarket.config.share_references.DataShareReferences
import com.developer.cubemarket.connection.MODEL.DAO.DaoUser
import com.developer.cubemarket.connection.MODEL.OOP.User
import com.developer.cubemarket.databinding.FragmentLoginBinding
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var ctx: Context
    var isExit = false
    private var countLayout = 0;
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
        initAnimation()
        return binding.root

    }


    private fun initDataDefault() {
        val auth = DataShareReferences.getEmailAndPass(requireContext())
        binding.edtA.setText(auth.email)
        binding.edtB.setText(auth.pass)
    }

    private fun initAnimation() {
        animEdtOut = AnimationUtils.loadAnimation(ctx, R.anim.anim_edt_out)
        binding.lnClose.setOnClickListener {


            binding.edtA.error = null
            binding.edtB.error = null
            binding.edtC.error = null
            binding.edtD.error = null

            if (countLayout == 2){
                countLayout = -1
            }
            ++countLayout

            binding.iplA.startAnimation(animEdtOut)
            binding.iplB.startAnimation(animEdtOut)
            binding.iplC.startAnimation(animEdtOut)
            binding.iplD.startAnimation(animEdtOut)
            if (countLayout == 0){
                //this is login
                binding.btnOk.text = "Đăng nhập"
                binding.tvTitle.text = "Đăng Nhập"
                binding.edtB.hint = "Mật khẩu"
                binding.iplB.visibility = View.VISIBLE
                binding.iplC.visibility = View.INVISIBLE
                binding.iplD.visibility = View.INVISIBLE

                binding.lnGoLogin.visibility = View.GONE

                binding.rlInLogin.visibility = View.VISIBLE
            }else if(countLayout == 1){
                //this is register
                binding.tvTitle.text = "Đăng Kí"
                binding.btnOk.text = "Đăng kí"

                binding.edtB.inputType = InputType.TYPE_CLASS_TEXT
                binding.iplB.startIconDrawable
                binding.edtB.hint = "Số điện thoại"
                binding.iplC.visibility = View.VISIBLE
                binding.iplD.visibility = View.VISIBLE
                binding.lnGoLogin.visibility = View.VISIBLE
                binding.rlInLogin.visibility = View.GONE


            }else{
                //this is forgot pass
                binding.tvTitle.text = "Quên Mật Khẩu"
                binding.btnOk.text = "Gửi mã"

                binding.edtA.hint = "Email"
                binding.iplB.visibility = View.INVISIBLE
                binding.iplC.visibility = View.INVISIBLE
                binding.iplD.visibility = View.INVISIBLE
                binding.lnGoLogin.visibility = View.GONE
                binding.rlInLogin.visibility = View.GONE
            }
        }
    }

    private fun initEventLogin() {
        binding.btnOk.setOnClickListener {
            var isCheck = true
            when(countLayout){
                1 -> {
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
                        val user = User(0, fullname, pass, 0, numberPhone, email)
                        DaoUser(ctx).insert_user(user)
                    }

                }
                0 -> {
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
                        DaoUser(ctx).dangnhap(binding.root, email, pass)
                    }

                }
            }
            //Register success!
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        class blockGoBack : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Toasty.warning(ctx, "Quay lại lần nữa để thoát", Toasty.LENGTH_SHORT).show()
                val job1 = GlobalScope.launch {
                    isExit = true
                    Thread.sleep(2000)
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