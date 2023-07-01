package com.example.radiusassignment.ui.adapter

import android.R.attr.name
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.radiusassignment.R

@BindingAdapter("setImage")
fun setImageResource(imageView: ImageView, name: String) {
    val resources: Resources = imageView.context.getResources()
    val resourceId = resources.getIdentifier(
        name, "drawable",
        imageView.context.getPackageName()
    )
    imageView.setImageResource(resourceId)
}
