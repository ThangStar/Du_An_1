package com.developer.cubemarket.fragment.fragment_home_pager

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.developer.cubemarket.R
import com.developer.cubemarket.activity.VIEW.LayoutHoadonActivity
import com.developer.cubemarket.config.user.DataUser
import com.developer.cubemarket.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater)

        initBtnPost()
        initDataDefault()
        initEventGoEditUser()
        initEventInsertDirectory()
        initGoChangePass()
        initEventLogout()
        initEventVoidcherManager()
        initEventGoSaled()
        initEventGoUserManager()
        initEventGoBill()
        return binding.root
    }

    private fun initEventGoBill() {
        binding.lnGoBill.setOnClickListener {
            startActivity(Intent(requireContext(), LayoutHoadonActivity::class.java))
        }
    }

    private fun initEventVoidcherManager() {
        binding.lnGoVoicherManager.setOnClickListener{

            findNavController().navigate(R.id.action_productFragment_to_voicherManagerFragment)
        }
    }

    private fun initEventGoUserManager() {
        binding.lnGoUserManager.setOnClickListener {
            findNavController().navigate(R.id.action_productFragment_to_userManagerFragment)
        }
    }

    private fun initEventGoSaled() {
        binding.lnGoProductSale.setOnClickListener {
            findNavController().navigate(R.id.action_productFragment_to_productSaleFragment)
        }
    }

    private fun initEventLogout() {
        binding.tvLogout.setOnClickListener{
            findNavController().navigate(R.id.action_productFragment_to_loginFragment)
        }
    }

    private fun initGoChangePass() {
        binding.lnGoChangePass.setOnClickListener {
            findNavController().navigate(R.id.action_productFragment_to_changePassFragment)
        }
    }

    private fun initEventInsertDirectory() {
        binding.lnInsertDirectory.setOnClickListener {
            findNavController().navigate(R.id.action_productFragment_to_insertDirectoryFragment)
        }
    }

    private fun initEventGoEditUser() {
        binding.lnGoEditUser.setOnClickListener {
            findNavController().navigate(R.id.action_productFragment_to_updateUserFragment)
        }
    }

    private fun initDataDefault() {
        binding.tvName.text = DataUser.name
        binding.tvEmail.text = DataUser.email

    }

    private fun initBtnPost() {
        binding.lnPost.setOnClickListener {
            findNavController().navigate(R.id.action_productFragment_to_postProductFragment)
        }
    }


}