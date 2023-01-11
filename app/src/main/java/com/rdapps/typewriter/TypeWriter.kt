package com.rdapps.typewriter

import android.os.Handler
import android.os.Looper

interface TypeWriter {

    var speed: Long
    var autoAppendText: Boolean

    var appendCharAtEnd: Char

    fun setupTextCallback(setText: (String) -> Unit, getText: () -> String)
    fun animateText(txt: CharSequence, startDelay: Long = 0)
    fun animateLoadingDots(startDelay: Long = 0)
    fun stopLoadingAnimation()
}

class TypeWriterImpl : TypeWriter {

    private var bufferText: CharSequence = ""
    private var index = 0
    private var setText: (String) -> Unit = {}
    private var getText: () -> String = { "" }
    private var text
        get() = getText()
        set(value) = setText(value)

    override var speed: Long = 40 // in ms
    override var autoAppendText: Boolean = false
    override var appendCharAtEnd: Char = ' '

    override fun setupTextCallback(setText: (String) -> Unit, getText: () -> String) {
        this.setText = setText
        this.getText = getText
    }

    private val mHandler: Handler = Handler(Looper.getMainLooper())
    private val characterAdder = object : Runnable {
        override fun run() {
            if (index < bufferText.length) {
                text = text.plus(bufferText[index++])
                mHandler.postDelayed(this, speed)
            }
        }
    }

    private val dotAnimator = object : Runnable {
        override fun run() {
            if (text.contains("...")) {
                text = text.replace("...", "")
            } else {
                text = text.plus(".")
            }
            mHandler.postDelayed(this, speed)
        }
    }

    override fun animateText(txt: CharSequence, startDelay: Long) {
        if (!autoAppendText) {
            text = ""
        }
        bufferText = txt
        index = 0
        mHandler.removeCallbacks(dotAnimator)
        mHandler.removeCallbacks(characterAdder)
        mHandler.postDelayed(characterAdder, startDelay)
    }

    override fun animateLoadingDots(startDelay: Long) {
        mHandler.postDelayed(dotAnimator, startDelay)
    }

    override fun stopLoadingAnimation() {
        mHandler.removeCallbacks(dotAnimator)
    }
}