package com.shalan.base.services

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import java.io.File

/**
 * Created by Mohamed Shalan on 4/20/20.
 */

class ImageLoadingServiceImp : ImageLoadingService {

    override fun loadCenterCropImageWithPlaceholder(
        context: Context,
        imageUrl: String,
        placeholder: Drawable,
        view: ImageView
    ) {
        Glide.with(context).load(imageUrl).placeholder(placeholder).centerCrop().into(view)
    }

    override fun loadFitCenterCropImageWithPlaceholder(
        context: Context,
        imageUrl: String,
        placeholder: Drawable,
        view: ImageView
    ) {
        Glide.with(context).load(imageUrl).placeholder(placeholder).fitCenter().into(view)
    }

    override fun loadImageWithPlaceholder(
        context: Context,
        imageUrl: String,
        placeholder: Drawable,
        view: ImageView
    ) {
        Glide.with(context).load(imageUrl).placeholder(placeholder).into(view)
    }

    override fun loadImageWithPlaceholderCenterCropRoundedCorner(
        context: Context,
        imageUrl: String,
        placeholder: Drawable,
        radius: Int,
        view: ImageView
    ) {
        Glide.with(context).load(imageUrl).placeholder(placeholder).apply(RequestOptions().apply {
            transform(CenterCrop(), RoundedCorners(radius))
        }).into(view)
    }

    override fun loadCircleImageWithPlaceholder(
        context: Context,
        imageUrl: String,
        placeholder: Drawable,
        view: ImageView
    ) {
        Glide.with(context).load(imageUrl).placeholder(placeholder)
            .apply(RequestOptions.circleCropTransform()).into(view)
    }

    override fun loadCircleImageWithPlaceholderFromFile(
        context: Context,
        imageUrl: String,
        placeholder: Drawable,
        view: ImageView
    ) {
        Glide.with(context).load(File(imageUrl)).placeholder(placeholder)
            .apply(RequestOptions.circleCropTransform()).into(view)
    }

    override fun loadCircleImageWithPlaceholderFromUri(
        context: Context,
        imageUrl: Uri,
        placeholder: Drawable,
        view: ImageView
    ) {
        Glide.with(context).load(imageUrl).placeholder(placeholder)
            .apply(RequestOptions.circleCropTransform()).into(view)
    }

    override fun loadImageWithPlaceholderAndTopCorners(
        context: Context,
        imageUrl: String,
        placeholder: Drawable,
        radius: Float,
        view: ImageView
    ) {
        Glide.with(context).load(imageUrl).placeholder(placeholder).apply(RequestOptions().apply {
            transform(CenterCrop(), GranularRoundedCorners(radius, radius, 0f, 0f))
        }).into(view)
    }

    override fun loadImageWithPlaceHolderStartTopBottomRadius(
        context: Context,
        imageUrl: String,
        placeholder: Drawable,
        startTopRadius: Float,
        startBottomRadius: Float,
        view: ImageView
    ) {
        Glide.with(context).load(imageUrl).placeholder(placeholder).apply(RequestOptions().apply {
            transform(
                CenterCrop(),
                GranularRoundedCorners(startTopRadius, 0f, 0f, startBottomRadius)
            )
        }).into(view)
    }

    override fun loadImageWithPlaceholderCorners(
        context: Context,
        imageUrl: String,
        placeholder: Drawable,
        topLeftRadius: Float,
        topRightRadius: Float,
        bottomLeftRadius: Float,
        bottomRightRadius: Float,
        view: ImageView
    ) {
        Glide.with(context).load(imageUrl).placeholder(placeholder).apply(RequestOptions().apply {
            transform(
                CenterCrop(),
                GranularRoundedCorners(
                    topLeftRadius,
                    topRightRadius,
                    bottomRightRadius,
                    bottomLeftRadius
                )
            )
        }).into(view)
    }

    override fun loadImage(drawable: Int, view: ImageView) {
        Glide.with(view).load(drawable).into(view)
    }
}
