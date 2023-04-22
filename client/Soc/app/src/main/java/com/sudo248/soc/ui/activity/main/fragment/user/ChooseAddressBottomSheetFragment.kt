package com.sudo248.soc.ui.activity.main.fragment.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sudo248.soc.databinding.FragmentChooseAddressBinding
import com.sudo248.soc.ui.activity.main.adapter.AddressSuggestionAdapter
import com.sudo248.soc.ui.uimodel.StepChooseAddress

class ChooseAddressBottomSheetFragment private constructor(
    private val viewModel: UserViewModel
) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentChooseAddressBinding
    private val adapter: AddressSuggestionAdapter by lazy {
        AddressSuggestionAdapter{
            viewModel.onChooseAddress(address = it)
        }
    }

    companion object {
        const val TAG = "ChooseAddressBottomSheetFragment"
        fun newInstance(viewModel: UserViewModel): ChooseAddressBottomSheetFragment {
            return ChooseAddressBottomSheetFragment(viewModel)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseAddressBinding.inflate(inflater)
        setupUi()
        observer()
        return binding.root
    }

    private fun setupUi() {
        binding.rcvAddress.adapter = adapter
        binding.imgClose.setOnClickListener {
            dismiss()
        }
    }

    private fun observer() {
        viewModel.stepChooseAddress.observe(viewLifecycleOwner) {
            if (it == StepChooseAddress.CLOSE) dismiss()
            binding.txtTitle.text = getString(it.value)
        }

        viewModel.suggestionAddress.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

}