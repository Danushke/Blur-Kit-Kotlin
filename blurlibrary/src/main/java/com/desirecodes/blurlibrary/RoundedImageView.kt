package com.desirecodes.blurlibrary

import android.content.Context
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class RoundedImageView : AppCompatImageView {
    var mCornerRadius = 0f
    private var rectF: RectF
    private var porterDuffXfermode: PorterDuffXfermode

    constructor(context: Context?) : super(context!!, null) {
        rectF = RectF()
        porterDuffXfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    }

    constructor(context: Context?, attributes: AttributeSet?) : super(
        context!!, attributes
    ) {
        rectF = RectF()
        porterDuffXfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    }

    override fun onDraw(canvas: Canvas) {
        val myDrawable = drawable
        if (myDrawable != null && myDrawable is BitmapDrawable && mCornerRadius > 0) {
            rectF.set(myDrawable.getBounds())
            val prevCount = canvas.saveLayer(rectF, null, Canvas.ALL_SAVE_FLAG)
            imageMatrix.mapRect(rectF)
            val paint = myDrawable.paint
            paint.isAntiAlias = true
            paint.color = DEFAULT_COLOR
            val prevMode = paint.xfermode
            canvas.drawARGB(DEFAULT_RGB, DEFAULT_RGB, DEFAULT_RGB, DEFAULT_RGB)
            canvas.drawRoundRect(rectF, mCornerRadius, mCornerRadius, paint)
            paint.xfermode = porterDuffXfermode
            super.onDraw(canvas)
            paint.xfermode = prevMode
            canvas.restoreToCount(prevCount)
        } else {
            super.onDraw(canvas)
        }
    }
    fun setCornerRadius(cornerRadius: Float) {
        this.mCornerRadius = cornerRadius
    }
    companion object {
        const val DEFAULT_COLOR = -0x1000000
        const val DEFAULT_RGB = 0
    }
}