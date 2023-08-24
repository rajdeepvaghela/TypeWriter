package com.rdapps.typewriter

import android.os.Handler
import android.os.Looper

interface TypeWriter {

    var speed: Long
    var autoAppendText: Boolean

    var appendStringAtEnd: String?

    fun setupTextCallback(setText: (String) -> Unit, getText: () -> String)
    fun animateText(txt: CharSequence, startDelay: Long = 0, onComplete: () -> Unit = {})
    fun animateLoadingDots(startDelay: Long = 0)
    fun stopLoadingAnimation()
    fun stopAnimation()
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
    override var appendStringAtEnd: String? = null
    private var onComplete: () -> Unit = {}

    override fun setupTextCallback(setText: (String) -> Unit, getText: () -> String) {
        this.setText = setText
        this.getText = getText
    }

    private val mHandler: Handler = Handler(Looper.getMainLooper())
    private val characterAdder = object : Runnable {
        override fun run() {
            if (index < bufferText.length) {
                text = if (appendStringAtEnd == null)
                    text.plus(bufferText[index++])
                else {
                    val currentText = text.removeSuffix(appendStringAtEnd.toString())
                    currentText.plus(bufferText[index++]).plus(appendStringAtEnd)
                }
                mHandler.postDelayed(this, speed)
            } else {
                mHandler.removeCallbacks(this)
                onComplete()
            }
        }
    }

    private val dotAnimator = object : Runnable {
        override fun run() {
            text = if (text.contains("...")) {
                text.replace("...", "")
            } else {
                text.plus(".")
            }
            mHandler.postDelayed(this, speed)
        }
    }

    override fun animateText(txt: CharSequence, startDelay: Long, onComplete: () -> Unit) {
        if (!autoAppendText) {
            text = ""
        }
        bufferText = txt
        index = 0
        mHandler.removeCallbacks(dotAnimator)
        mHandler.removeCallbacks(characterAdder)
        mHandler.postDelayed(characterAdder, startDelay)
        this.onComplete = onComplete
    }

    override fun animateLoadingDots(startDelay: Long) {
        mHandler.postDelayed(dotAnimator, startDelay)
    }

    override fun stopLoadingAnimation() {
        mHandler.removeCallbacks(dotAnimator)
    }

    override fun stopAnimation() {
        mHandler.removeCallbacks(dotAnimator)
        mHandler.removeCallbacks(characterAdder)
    }
}