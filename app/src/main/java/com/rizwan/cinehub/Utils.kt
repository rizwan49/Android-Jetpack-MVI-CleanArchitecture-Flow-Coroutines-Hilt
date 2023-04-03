package com.rizwan.cinehub

import android.content.Context
import android.util.Log
import android.widget.Toast

object Utils {

    const val JPG = ".jpg"
    const val DRAWABLE = "drawable"
    const val TAG = "Utils"


    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    /**
     *this method returns either the drawable matched resource or placeholder missing resource.
     * this will eliminate the extension, such as .jpg to match the json banner name with stored drawable file name.
     */
    fun getResourceId(bannerName: String, applicationContext: Context): Int {
        val resId = applicationContext.resources.getIdentifier(
            bannerName.removeSuffix(JPG),
            DRAWABLE,
            applicationContext.packageName
        )
        Log.d(TAG, "bannerName: $bannerName and $resId")
        return if (resId == 0) R.drawable.placeholder_for_missing_posters else resId
    }
}