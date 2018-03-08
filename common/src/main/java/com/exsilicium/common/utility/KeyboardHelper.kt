package com.exsilicium.common.utility

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.exsilicium.common.extension.safeLet

object KeyboardHelper {
    fun hideKeyboard(activity: AppCompatActivity) {
        hideKeyboard(activity.currentFocus, activity)
    }

    private fun hideKeyboard(view: View?, context: Context? = view?.context) {
        safeLet(context, view) { safeContext, safeView ->
            val inputMethodManager = safeContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(safeView.windowToken, 0)
        }
    }
}