package com.kohuyn.together.ui.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import com.github.dhaval2404.imagepicker.R
import java.io.File

/**
 * Intent Utility
 *
 * @author Dhaval Patel
 * @version 1.6
 * @since 05 January 2019
 */
object IntentUtil {

    /**
     * View Image in 3rd party apps
     *
     * @param activity Activity Instance
     * @param file Image File
     *
     */
    fun showImage(activity: Activity, file: File) {
        val intent = Intent(Intent.ACTION_VIEW)
        val authority = activity.packageName + activity.getString(R.string.image_picker_provider_authority_suffix)
        val uri = FileProvider.getUriForFile(activity, authority, file)
        intent.setDataAndType(uri, "image/*")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        activity.startActivity(intent)
    }

}
