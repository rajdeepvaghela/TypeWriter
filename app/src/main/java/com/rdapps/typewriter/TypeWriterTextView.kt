package com.rdapps.typewriter

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class TypeWriterTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs), TypeWriter by TypeWriterImpl() {

    override var text: String
        get() = getText().toString()
        set(value) {
            setText(value)
        }
}