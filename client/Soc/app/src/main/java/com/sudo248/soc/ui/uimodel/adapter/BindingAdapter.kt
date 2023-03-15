package com.sudo248.soc.ui.uimodel.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 14:20 - 12/03/2023
 */

@BindingAdapter("imageUrl")
fun loadImage(image: ImageView, url: String) {
    Glide.with(image.context).load(url).into(image)
}