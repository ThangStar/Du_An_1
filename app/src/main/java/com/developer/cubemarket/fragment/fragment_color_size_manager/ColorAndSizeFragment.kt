package com.developer.cubemarket.fragment.fragment_color_size_manager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.cubemarket.R
import com.developer.cubemarket.adapter.manager.ItemColorAndSizeManagerAdapter
import com.developer.cubemarket.connection.MODEL.DAO.DaoKichThuoc
import com.developer.cubemarket.connection.MODEL.DAO.DaoMauSac
import com.developer.cubemarket.connection.MODEL.OOP.Kichthuoc
import com.developer.cubemarket.connection.MODEL.OOP.Mausac
import com.developer.cubemarket.connection.callback.CallBackColorProduct
import com.developer.cubemarket.connection.callback.CallBackGetCode
import com.developer.cubemarket.connection.callback.CallBackInsertSize
import com.developer.cubemarket.connection.callback.CallBackSizeProduct
import com.developer.cubemarket.databinding.FragmentColorAndSizeBinding
import es.dmoral.toasty.Toasty
import java.util.regex.Pattern

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class ColorAndSizeFragment : Fragment() {
    private val arrSize = arrayListOf<String>()
    val arrColor = arrayListOf<String>()
    lateinit var adapterSize: ItemColorAndSizeManagerAdapter
    lateinit var adapterColor: ItemColorAndSizeManagerAdapter
    lateinit var binding: FragmentColorAndSizeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentColorAndSizeBinding.inflate(layoutInflater)
        initRecyclerColor()
        initRecyclerSize()

        initAddColor()
        initAddSize()

        initBackStack()
        return binding.root
    }

    private fun initBackStack() {
        binding.imvBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initAddSize() {
        binding.btnAddSize.setOnClickListener {
            val size = binding.edtSize.text.toString().trim()

            val callBack = object : CallBackInsertSize{
                override fun onSuccess(rs: String) {
                    arrSize.add(size)
                    adapterSize.notifyItemInserted(arrSize.size)
                    binding.edtSize.setText("")
                }

                override fun onFail(rs: String) {
                    Toasty.warning(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                }

                override fun onError(rs: String) {
                    Toasty.error(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                }
            }
            if (Pattern.matches("^[\\S ]{1,14}$", size)){
                binding.tilSize.error = null
                DaoKichThuoc(requireContext()).insert_kichthuoc(callBack, Kichthuoc(0, size))
            }else{
                binding.tilSize.error = "Kích thước 1-14 kí tự"
            }
        }

    }

    private fun initAddColor() {
        binding.btnAddColor.setOnClickListener {
            val color = binding.edtColor.text.toString().trim()


            val callBackInsertColor = object : CallBackInsertSize{
                override fun onSuccess(rs: String) {
                    adapterColor.notifyItemInserted(arrColor.size)
                    arrColor.add(color)
                    binding.edtColor.setText("")
                }

                override fun onFail(rs: String) {
                    Toasty.warning(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                }

                override fun onError(rs: String) {
                    Toasty.error(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                }
            }

            if (Pattern.matches("^[\\S ]{1,14}$", color)){
                binding.tilColor.error = null
                DaoMauSac(requireContext()).insert_mausac(callBackInsertColor, Mausac(0, color))

            }else{
                binding.tilColor.error = "Màu sắc 1-14 kí tự"
            }
        }
    }

    private fun initRecyclerSize() {
        val initDataSize: () -> ArrayList<String> = {

            val callBackSizeProduct = object : CallBackSizeProduct{
                override fun onSuccess(kt: Kichthuoc) {
                    arrSize.add(kt.tenkichthuoc)
                    adapterSize.notifyItemInserted(arrSize.size)
                }

                override fun onFail(rs: String) {
                    Toasty.warning(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                }

                override fun onError(rs: String) {
                    Toasty.error(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                }
            }
            DaoKichThuoc(requireContext()).getdata_kichthuoc(callBackSizeProduct)
            arrSize
        }
        adapterSize = ItemColorAndSizeManagerAdapter(initDataSize())
        binding.rySize.adapter = adapterSize
    }

    private fun initRecyclerColor() {
        val initDataColor: () -> ArrayList<String> = {

            val callBackGetColor = object : CallBackColorProduct{
                override fun onSuccess(ms: Mausac) {
                    arrColor.add(ms.tenmausac)
                    adapterColor.notifyItemInserted(arrColor.size)
                }

                override fun onFail(rs: String) {
                    Toasty.warning(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                }

                override fun onError(rs: String) {
                    Toasty.error(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                }
            }
            DaoMauSac(requireContext()).getdata_mausac(callBackGetColor)
            arrColor
        }
        adapterColor = ItemColorAndSizeManagerAdapter(initDataColor())
        binding.ryColor.adapter = adapterColor
    }
}