package com.developer.cubemarket.fragment.fragment_home_pager

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.developer.cubemarket.R
import com.developer.cubemarket.activity.VIEW.LayoutHoadonActivity
import com.developer.cubemarket.activity.VIEW.ThongkeActivity
import com.developer.cubemarket.config.share_references.DataShareReferences.Companion.putEmailAndPass
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

        initUiPermission()
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
        initEventGoRank()
        initEventGoColorAndSizeManager()
        return binding.root
    }

    private fun initEventGoColorAndSizeManager() {
        binding.lnGoColorSizeManager.setOnClickListener {
            findNavController().navigate(R.id.action_productFragment_to_colorAndSizeFragment)
        }
    }

    private fun initUiPermission() {
        if(DataUser.occupation == 0){
            binding.lnGoColorSizeManager.visibility = View.GONE
            binding.lnGoRank.visibility = View.GONE
            binding.lnGoUserManager.visibility = View.GONE
            binding.lnGoVoicherManager.visibility = View.GONE
            binding.lnInsertDirectory.visibility = View.GONE
            binding.lnGoProductSale.visibility = View.GONE
            binding.lnPost.visibility = View.GONE
        }else if(DataUser.occupation == 1){
            binding.lnGoColorSizeManager.visibility = View.GONE
            binding.lnInsertDirectory.visibility = View.GONE
            binding.lnGoUserManager.visibility = View.GONE
            binding.lnGoVoicherManager.visibility = View.GONE
        }
    }

    private fun initEventGoRank() {
        binding.lnGoRank.setOnClickListener {
            startActivity(Intent(requireContext(), ThongkeActivity::class.java))
        }
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
            //put data in share references
            putEmailAndPass(requireContext(), "", "")
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