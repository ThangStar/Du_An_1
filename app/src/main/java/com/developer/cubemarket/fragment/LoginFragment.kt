package com.developer.cubemarket.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.developer.cubemarket.R
import MODEL.DAO.DaoUser
import MODEL.OOP.User
import com.developer.cubemarket.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var ctx: Context

    var countLayout = 0;
    lateinit var animEdtOut: Animation
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        ctx = binding.root.context

        initEventLogin()
        initAnimation()
        return binding.root

    }

    private fun initAnimation() {
        animEdtOut = AnimationUtils.loadAnimation(ctx, R.anim.anim_edt_out)
        binding.lnClose.setOnClickListener {

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
            when(countLayout){
                1 -> {
                    val email = binding.edtA.text.toString().trim()
                    val numberPhone = binding.edtB.text.toString().trim()
                    val fullname = binding.edtC.text.toString().trim()
                    val pass = binding.edtD.text.toString().trim()
                    val user = User(0, fullname, pass, 0, numberPhone, email)
                    DaoUser(ctx).insert_user(user)
                }
                0 -> {
                    val email = binding.edtA.text.toString().trim()
                    val pass = binding.edtB.text.toString().trim()
                    DaoUser(ctx).dangnhap(email, pass)

                }
            }
            //Register success!
           findNavController().navigate(R.id.action_loginFragment_to_productFragment)
        }
    }
}