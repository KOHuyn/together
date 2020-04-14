package com.kohuyn.together.ui.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.kohuyn.together.R
import com.kohuyn.together.TogetherApplication
import com.utils.UIHelper
import de.hdodenhof.circleimageview.CircleImageView
import org.greenrobot.eventbus.EventBus
import java.io.File

fun ImageView.show(url: String?) {
    val requestOptions = RequestOptions()
        .error(R.drawable.ic_launcher_background)
        .centerCrop()
    Glide.with(TogetherApplication.getInstance())
        .load(url).apply(requestOptions)
        .transition(DrawableTransitionOptions.withCrossFade())
        .override(UIHelper.getScreenWidth(TogetherApplication.getInstance()), 300)
        .into(this)
}

fun ImageView.showCenterInside(url: String?) {
    val requestOptions = RequestOptions()
        .placeholder(R.drawable.ic_launcher_background)
        .error(R.drawable.ic_launcher_background)
        .centerInside()
    Glide.with(TogetherApplication.getInstance())
        .load(url).apply(requestOptions)
        .transition(DrawableTransitionOptions.withCrossFade())
        .override(UIHelper.getScreenWidth(TogetherApplication.getInstance()), 1000)
        .into(this)
}

fun ImageView.showCenterInsideFullHeight(url: String?) {
    val requestOptions = RequestOptions()
        .error(R.drawable.ic_launcher_background)
        .centerInside()
    Glide.with(TogetherApplication.getInstance())
        .load(url).apply(requestOptions)
        .override(UIHelper.getScreenWidth(TogetherApplication.getInstance()), UIHelper.getScreenHeight(TogetherApplication.getInstance()))
        .into(this)
}

fun CircleImageView.show(url: String?) {
    val requestOptions = RequestOptions()
        .error(R.drawable.ic_launcher_background)
        .centerCrop()
    Glide.with(TogetherApplication.getInstance())
        .load(url).apply(requestOptions)
        //            .transition(withCrossFade())
//            .override(400, 300)
        .into(this)
}

fun ImageView.show(url: Int) {
    val requestOptions = RequestOptions()
        .error(R.drawable.ic_launcher_background)
        .centerCrop()
    Glide.with(TogetherApplication.getInstance())
        .load(url).apply(requestOptions)
//            .override(400, 300)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

fun ImageView.setDrawableImage(@DrawableRes resource: Int, applyCircle: Boolean = false) {
    val glide = Glide.with(this).load(resource)
    if (applyCircle) {
        glide.apply(RequestOptions.circleCropTransform()).into(this)
    } else {
        glide.into(this)
    }
}

fun ImageView.setLocalImage(file: File, applyCircle: Boolean = false) {
    val glide = Glide.with(this).load(file)
    if (applyCircle) {
        glide.apply(RequestOptions.circleCropTransform()).into(this)
    } else {
        glide.into(this)
    }
}

fun ImageView.setPathLocalImage(path:String, applyCircle: Boolean = false) {
    val file = File(path)
    val glide = Glide.with(this).load(file)
    if (applyCircle) {
        glide.apply(RequestOptions.circleCropTransform()).into(this)
    } else {
        glide.into(this)
    }
}


fun postNormal(event: Any) {
    EventBus.getDefault().post(event)
}

fun register(subscriber: Any) {
    EventBus.getDefault().register(subscriber)
}

fun unregister(subscriber: Any) {
    EventBus.getDefault().unregister(subscriber)
}