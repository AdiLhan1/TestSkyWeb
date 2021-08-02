package com.jacob.testskyweb.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.jacob.testskyweb.R
import java.util.regex.Matcher


fun EditText.text(
    message: String?
) {
    if (!message.isNullOrEmpty()) {
        this.setText(message)
    }
}

fun TextView.text(
    message: String?
) {
    if (!message.isNullOrEmpty()) this.text = message
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

@SuppressLint("ServiceCast")
fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}


fun ImageView.setImage(url: String?) {
    if (url != null) {
        Glide.with(this.context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.image_ph)
            .into(this)
    }
}

fun TextInputLayout.isValid(password: String?): Boolean {
    val matcher: Matcher = Constants.pattern.matcher(password)
    return matcher.matches()
}

fun Fragment.showSnackbar(
    text: String,
    @BaseTransientBottomBar.Duration length: Int = Snackbar.LENGTH_SHORT
) {
    view?.run { Snackbar.make(this, text, length).show() }
}