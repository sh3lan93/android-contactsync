package com.shalan.base.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shalan.base.adapter.BaseAdapter
import com.shalan.base.services.ImageLoadingService

/**
 * Created by Mohamed Shalan on 4/29/21
 */

@BindingAdapter("app:adapter", requireAll = true)
fun setRecyclerAdapter(rv: RecyclerView, adapter: BaseAdapter<*, *>) {
    rv.adapter = adapter
}

@BindingAdapter("app:imgSrc", "app:imageLoadingService", requireAll = true)
fun loadImageDrawable(
    iv: ImageView,
    @DrawableRes source: Int,
    imageLoadingService: ImageLoadingService
) {
    imageLoadingService.loadImage(source, iv)
}

@BindingAdapter("app:tvDrawableStart", requireAll = true)
fun loadDrawableStart(tv: TextView, @DrawableRes source: Int) {
    tv.setCompoundDrawables(ContextCompat.getDrawable(tv.context, source), null, null, null)
}

@BindingAdapter(
    "app:imageUrl",
    "app:imageLoadingService",
    "app:imagePlaceholder",
    "app:imageTopCornersRadius",
    requireAll = true
)
fun loadImageWithTopCornerRadius(
    iv: ImageView,
    url: String,
    imageLoadingService: ImageLoadingService,
    placeholder: Drawable,
    topRadius: Float
) {
    imageLoadingService.loadImageWithPlaceholderAndTopCorners(
        context = iv.context,
        imageUrl = url,
        placeholder = placeholder,
        radius = topRadius,
        view = iv
    )
}

@BindingAdapter(
    "app:imageUrl",
    "app:imageLoadingService",
    "app:imagePlaceholder",
    "app:imageLeftCornerRadius",
    requireAll = true
)
fun loadImageWithLeftCornerRadius(
    iv: ImageView,
    url: String,
    imageLoadingService: ImageLoadingService,
    placeholder: Drawable,
    leftRadius: Float
) {
    imageLoadingService.loadImageWithPlaceholderCorners(
        context = iv.context,
        imageUrl = url,
        placeholder = placeholder,
        topLeftRadius = leftRadius,
        bottomLeftRadius = leftRadius,
        topRightRadius = 0f,
        bottomRightRadius = 0f,
        view = iv
    )
}
