package com.developer.cubemarket.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.cubemarket.R
import com.developer.cubemarket.databinding.FragmentNewPassBinding
import java.util.regex.Pattern

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewPassFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewPassFragment : Fragment() {
    lateinit var binding: FragmentNewPassBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNewPassBinding.inflate(layoutInflater)

        initEventNewPass()
        return binding.root
    }

    private fun initEventNewPass() {
        val pass = binding.edtPass.text.toString().trim()
        val passAgain = binding.edtPassAgain.text.toString().trim()

        if(pass != passAgain){
            binding.tilPassAgain.error = "Mật khẩu không giống nhau"
        }else if(Pattern.matches("^[\\S ]{2,10}$", pass)){
            binding.tilPassAgain.error = null
            //up pass new
        }else{
            binding.tilPassAgain.error = "Mật khẩu phải 2-10 kí tự"
        }
    }
}