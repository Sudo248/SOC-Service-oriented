package com.sudo248.soc_staff.ui.activity.main.fragment.user

import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.material.datepicker.MaterialDatePicker
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.soc.R
import com.sudo248.soc.databinding.FragmentEditUserBinding
import com.sudo248.soc.domain.entity.user.Gender
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class UserEditFragment : BaseFragment<FragmentEditUserBinding, UserViewModel>(), ViewController {
    override val viewModel: UserViewModel by activityViewModels()

    override fun initView() {
        binding.viewModel = viewModel
        viewModel.setViewController(this)
        initGender()
    }

    private fun initGender() {
        val adapter = ArrayAdapter(requireContext(), R.layout.item_textview, Gender.values().map { it.value })
        val autoCompleteTextView = binding.edtDob.editText as? AutoCompleteTextView
        autoCompleteTextView?.setAdapter(adapter)
    }

    override fun showDialogDatePicker(now: Date?){
        val selectDate = now?.time ?: MaterialDatePicker.todayInUtcMilliseconds()
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Your Birthday")
                .setSelection(selectDate)
                .build()

        activity?.supportFragmentManager?.let { datePicker.show(it, "Show date picker") }

        datePicker.addOnPositiveButtonClickListener {
            viewModel.selectDate(datePicker.selection)
        }
    }

    override fun showBottomSheetChooseAddress() {
        val bottomSheetFragment = ChooseAddressBottomSheetFragment.newInstance(viewModel)
        bottomSheetFragment.show(childFragmentManager, ChooseAddressBottomSheetFragment.TAG)
    }

    override fun toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}