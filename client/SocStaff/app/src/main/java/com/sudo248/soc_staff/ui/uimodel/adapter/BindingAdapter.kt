package com.sudo248.soc_staff.ui.uimodel.adapter

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sudo248.base_android.ktx.setHorizontalViewPort
import com.sudo248.soc_staff.R
import com.sudo248.soc_staff.ui.util.Utils


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 14:20 - 12/03/2023
 */

@BindingAdapter("imageUrl")
fun loadImage(image: ImageView, url: String) {
    if (url.isEmpty()) return
    var imageUrl = url
    if (!imageUrl.startsWith("http")) {
        imageUrl = Utils.getImageUrl(imageUrl)
    }
    Glide
        .with(image.context)
        .load(imageUrl)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .error(R.drawable.ic_error)
        .into(image)
}

@BindingAdapter("imageUri")
fun loadImage(image: ImageView, uri: Uri) {
    Glide
        .with(image.context)
        .load(uri)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .error(R.drawable.ic_error)
        .into(image)
}

@BindingAdapter("horizontalViewPort")
fun horizontalViewPort(recycleView: RecyclerView, viewPort: Float) {
    recycleView.setHorizontalViewPort(viewPort)
}