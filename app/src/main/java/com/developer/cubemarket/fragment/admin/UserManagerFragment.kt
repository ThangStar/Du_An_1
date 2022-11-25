package com.developer.cubemarket.fragment.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.cubemarket.adapter.manager.UserManagerAdapter
import com.developer.cubemarket.config.user.DataUser
import com.developer.cubemarket.connection.MODEL.DAO.DaoUser
import com.developer.cubemarket.connection.MODEL.OOP.User
import com.developer.cubemarket.connection.callback.CallBackGetDataUser
import com.developer.cubemarket.databinding.FragmentUserManagerBinding
import es.dmoral.toasty.Toasty

class UserManagerFragment : Fragment() {
    lateinit var binding: FragmentUserManagerBinding
    lateinit var adapterUserManager: UserManagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUserManagerBinding.inflate(layoutInflater)

        initRecyclerUser()
        return binding.root
    }

    private fun initRecyclerUser() {
        adapterUserManager = UserManagerAdapter(requireActivity(), initDataUser())
        binding.ryUser.adapter = adapterUserManager
    }

    private fun initDataUser(): ArrayList<User> {
        val arr = arrayListOf<User>()
        val callBackGetDataUser = object : CallBackGetDataUser {
            override fun onSuccess(sp: User) {
                arr.add(sp)
                adapterUserManager.notifyItemInserted(arr.size)
            }

            override fun onFail(err: String) {
                Toasty.warning(requireContext(), err, Toasty.LENGTH_SHORT).show()
            }

            override fun onError(err: String) {
                Toasty.error(requireContext(), err, Toasty.LENGTH_SHORT).show()
            }
        }
        DaoUser(requireContext()).get_all_user(callBackGetDataUser, DataUser.occupation)
        return arr
    }
}