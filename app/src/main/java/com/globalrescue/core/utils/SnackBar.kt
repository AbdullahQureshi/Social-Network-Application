package com.globalrescue.core.utils

import androidx.fragment.app.FragmentActivity
import com.globalrescue.R
import com.google.android.material.snackbar.Snackbar


class SnackBar {
    companion object {
        fun showSnackBar(activity: FragmentActivity, message: Int) {
            Snackbar.make(activity.findViewById(R.id.root), message, Snackbar.LENGTH_LONG).show() } }

}