package com.developer.cubemarket.fragment


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.developer.cubemarket.R
import com.developer.cubemarket.databinding.FragmentLogoBinding
import kotlinx.coroutines.*

class LogoFragment : Fragment() {
    lateinit var binding: FragmentLogoBinding
    lateinit var ctx: Context
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLogoBinding.inflate(layoutInflater)
        ctx = binding.root.context
        val coroutine = CoroutineScope(CoroutineName("logo") + Dispatchers.IO)
        coroutine.launch {
            Thread.sleep(1000)
            withContext(Dispatchers.Main){
                findNavController().navigate(R.id.action_logoFragment_to_loginFragment)
            }
        }
        return binding.root
    }
}