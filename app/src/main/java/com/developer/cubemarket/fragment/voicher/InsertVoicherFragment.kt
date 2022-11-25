package com.developer.cubemarket.fragment.voicher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.developer.cubemarket.connection.MODEL.DAO.DaoKhuyenMai
import com.developer.cubemarket.connection.MODEL.OOP.KhuyenMai
import com.developer.cubemarket.connection.callback.CallBackInsertVoicher
import com.developer.cubemarket.databinding.FragmentInsertVoicherBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_CLOCK
import com.google.android.material.timepicker.TimeFormat
import es.dmoral.toasty.Toasty
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class InsertVoicherFragment : Fragment() {
    lateinit var binding: FragmentInsertVoicherBinding
    var dateFirstFormatter = "0000-00-00"
    var dateSecondFormatter = "0000-00-00"

    var timeFirstFormatter = "00:00"
    var timeSecondFormatter = "00:00"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsertVoicherBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        initEventBack()
        initDataDefaultTime()
        initEventDateAndTimePicker()
        initEventInsert()
        return binding.root
    }

    private fun initEventInsert() {
        binding.btnInsert.setOnClickListener {
            val code = binding.edtCode.text.toString()
            val percent = binding.edtPercent.text.toString()
            val minPrice = binding.edtMin.text.toString()
            var isCheck = true

            if(Pattern.matches("^[a-zA-Z0-9]{1,14}$", code)){
                binding.tilCode.error = null
            }else{
                isCheck = false
                binding.tilCode.error = "mã giảm giá 1-14 kí tự, không có kí tự đặc biệt"
            }
            if(Pattern.matches("^[0-9]{1,2}$", percent)){
                binding.tilPercent.error = null
            }else{
                isCheck = false
                binding.tilPercent.error = "phần trăm giảm giá 1-2 kí tự, phải là số"
            }
            if(Pattern.matches("^[0-9]{5,10}$", minPrice)){
                binding.tilMinPrice.error = null
            }else{
                isCheck = false
                binding.tilMinPrice.error = "giá sản phẩm áp dụng 5-10 kí tự, phải là số"
            }
            if(isCheck){
                val callBackInsert = object: CallBackInsertVoicher{
                    override fun onSuccess(rs: String) {
                        Toasty.success(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                    }

                    override fun onFail(rs: String) {
                        Toasty.warning(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                    }

                    override fun onError(rs: String) {
                        Toasty.error(requireContext(), rs, Toasty.LENGTH_SHORT).show()
                    }
                }
                DaoKhuyenMai(requireContext()).insert_khuyenmai(
                    callBackInsert,
                    KhuyenMai(code,
                    percent.toInt(),
                    "$dateFirstFormatter $timeFirstFormatter",
                    "$dateSecondFormatter $timeSecondFormatter",
                    minPrice.toInt())
                )
            }
        }
    }

    private fun initEventBack() {
        binding.imvBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initDataDefaultTime() {
        binding.tvTimeFirst.text = "Từ: $dateFirstFormatter $timeFirstFormatter"
        binding.tvTimeSecond.text = "Đến: $dateSecondFormatter $timeSecondFormatter"
    }

    private fun initEventDateAndTimePicker() {
        binding.btnDatePicker.setOnClickListener {

            val dateRangePicker =
                MaterialDatePicker.Builder.dateRangePicker()
                    .setTitleText("Select dates")
                    .build()

            dateRangePicker.show(requireActivity().supportFragmentManager, "DATE_PICKER")
            dateRangePicker.addOnPositiveButtonClickListener {
                val calendar: Calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                calendar.timeInMillis = it.first
                val format = SimpleDateFormat("yyyy-MM-dd ")
                val formattedFirst: String = format.format(calendar.getTime())
                calendar.timeInMillis = it.second
                val formattedSecond: String = format.format(calendar.getTime())
                dateFirstFormatter = formattedFirst
                dateSecondFormatter = formattedSecond
                initDataDefaultTime()
            }
        }
        binding.btnTimePicker.setOnClickListener {
            val pickerFirst =
                MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(12)
                    .setMinute(10)
                    .setInputMode(INPUT_MODE_CLOCK)
                    .setTitleText("Chọn giờ bắt đầu")
                    .build()
            pickerFirst.show(requireActivity().supportFragmentManager, "TIME_PICKER_FIRST");
            pickerFirst.addOnPositiveButtonClickListener {
                val pickerSecond =
                    MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_24H)
                        .setHour(12)
                        .setMinute(10)
                        .setInputMode(INPUT_MODE_CLOCK)
                        .setTitleText("Chọn giờ kết thúc")
                        .build()
                pickerSecond.show(requireActivity().supportFragmentManager, "TIME_PICKER_SECOND")
                pickerSecond.addOnPositiveButtonClickListener {
                    timeFirstFormatter = "${pickerFirst.hour}:${pickerFirst.minute}:00"
                    timeSecondFormatter = "${pickerSecond.hour}:${pickerSecond.minute}:00"
                    initDataDefaultTime()
                }
            }
        }
    }
}