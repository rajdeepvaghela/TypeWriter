package com.rdapps.typewriter

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class TypeWriterEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs), TypeWriter by TypeWriterImpl() {

    override var text: String
        get() = getText()?.toString() ?: ""
        set(value) {
            setText(value)
        }
}