package com.globalrescue.core.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.globalrescue.R


class ProgressDialog {


    companion object {

        var dialog: Dialog? = null

        fun showProgressDialog(context: Context) {
            dialog = Dialog(context)
            val inflate = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null)
            dialog?.setContentView(inflate)
            dialog?.setCancelable(false)
            dialog?.window!!.setBackgroundDrawable(
                ColorDrawable(Color.TRANSPARENT)
            )

            dialog?.show()
        }

        fun hideProgressBar() {
            dialog?.hide()
        }
    }


}