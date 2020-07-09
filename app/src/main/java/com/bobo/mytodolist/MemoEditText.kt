package com.bobo.mytodolist

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import kotlin.jvm.internal.Intrinsics

class MemoEditText : AppCompatEditText, TextWatcher,
    View.OnFocusChangeListener {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private lateinit var clearDrawable: Drawable
    private lateinit var createDrawable: Drawable

    @SuppressLint("ClickableViewAccessibility")
    private fun init() {
        clearDrawable = ContextCompat.getDrawable(context, R.drawable.ic_clear)?.let { DrawableCompat.wrap(it) }!!
        createDrawable = ContextCompat.getDrawable(context, R.drawable.ic_create_task)?.let{DrawableCompat.wrap(it)}!!

        DrawableCompat.setTintList(clearDrawable, hintTextColors)
        clearDrawable.setBounds(0, 0, clearDrawable.intrinsicWidth, clearDrawable.intrinsicHeight)

        setClearIconVisible(false)

        super.setOnFocusChangeListener(this)
        addTextChangedListener(this)

    }

    private fun setClearIconVisible(visible: Boolean) {
        clearDrawable.setVisible(visible, false)
        if (visible) setCompoundDrawables(clearDrawable, null, null, null)
        else setCompoundDrawables(createDrawable, null, null, null)
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        if (isFocused) {
            setClearIconVisible(text?.length!!>0)
        }
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        TODO("Not yet implemented")
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
    }
}

