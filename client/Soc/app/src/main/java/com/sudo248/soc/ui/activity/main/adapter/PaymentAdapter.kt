package com.sudo248.soc.ui.activity.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sudo248.base_android.base.BaseListAdapter
import com.sudo248.base_android.base.BaseViewHolder
import com.sudo248.soc.R
import com.sudo248.soc.databinding.ItemPaymentBinding
import com.sudo248.soc.domain.entity.cart.CartSupplierProduct
import com.sudo248.soc.ui.uimodel.adapter.loadImage
import com.sudo248.soc.ui.util.Utils

class PaymentAdapter : BaseListAdapter<CartSupplierProduct, PaymentAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemPaymentBinding) :
        BaseViewHolder<CartSupplierProduct, ItemPaymentBinding>(binding) {
        override fun onBind(item: CartSupplierProduct) {
            binding.apply {
                loadImage(imageProductPayment, item.supplierProduct.product.images[0])
                txtNameProduct.text = item.supplierProduct.product.name
                txtCountProduct.text = "X${item.amount}"
                txtPricesProduct.text = Utils.formatVnCurrency(item.supplierProduct.price)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemPaymentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}