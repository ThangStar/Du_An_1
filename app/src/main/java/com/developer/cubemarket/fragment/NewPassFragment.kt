package com.developer.cubemarket.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.developer.cubemarket.R
import com.developer.cubemarket.config.share_references.DataShareReferences.Companion.putEmailAndPass
import com.developer.cubemarket.connection.MODEL.DAO.DaoUser
import com.developer.cubemarket.connection.callback.CallBackUpdatePass
import com.developer.cubemarket.databinding.FragmentNewPassBinding
import es.dmoral.toasty.Toasty
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
        binding.btnAccept.setOnClickListener {
            val pass = binding.edtPass.text.toString().trim()
            val passAgain = binding.edtPassAgain.text.toString().trim()

            if(pass != passAgain){
                binding.tilPassAgain.error = "Mật khẩu không giống nhau"
            }else if(Pattern.matches("^[\\S ]{2,10}$", pass)){
                binding.tilPassAgain.error = null
                //up pass new

                val code = arguments?.getString("CODE")
                val email = arguments?.getString("EMAIL")

                val callback = object : CallBackUpdatePass{
                    override fun onSuccess(rs: String) {
                        Toasty.success(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                        putEmailAndPass(requireContext(), email!!, pass)
                        findNavController().navigate(R.id.action_newPassFragment_to_loginFragment)

                        //put data in share references

                    }

                    override fun onFail(rs: String) {
                        Toasty.warning(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                    }

                    override fun onError(rs: String) {
                        Toasty.error(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                    }
                }
                DaoUser(requireContext()).nhapma(callback, email, code!!.toInt(), pass)
            }else{
                binding.tilPassAgain.error = "Mật khẩu phải 2-10 kí tự"
            }
        }
    }
}