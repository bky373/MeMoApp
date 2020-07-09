package com.bobo.mytodolist.utilities

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


// class or object?
object FunctionsUtils {
    fun hideKeyboard(context: Context?, view: View?) {
        view?.apply {
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
    fun showKeyboard(context: Context?, view: View?) {
        view?.apply {
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
        }
    }
}