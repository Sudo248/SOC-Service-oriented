package com.sudo248.soc.ui.uimodel.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sudo248.base_android.ktx.setHorizontalViewPort
import com.sudo248.soc.R


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 14:20 - 12/03/2023
 */

@BindingAdapter("imageUrl")
fun loadImage(image: ImageView, url: String) {
    Glide
        .with(image.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .error(R.drawable.ic_error)
        .into(image)
}

@BindingAdapter("horizontalViewPort")
fun horizontalViewPort(recycleView: RecyclerView, viewPort: Float) {
    recycleView.setHorizontalViewPort(viewPort)
}