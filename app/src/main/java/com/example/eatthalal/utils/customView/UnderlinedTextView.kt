package com.example.eatthalal.utils.customView

import android.content.Context
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.AttributeSet

class UnderlinedTextView : androidx.appcompat.widget.AppCompatTextView {

    constructor(context: Context) : super(context) {
        underlineText()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        underlineText()
    }


    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        underlineText()
    }

    /**
     * This function is used to
     * underlined the text in this TextView**/
    private fun underlineText() {
        val content = SpannableString(text)
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        text = content
    }
}